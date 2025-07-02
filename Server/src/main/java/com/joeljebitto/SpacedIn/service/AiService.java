package com.joeljebitto.SpacedIn.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AiService {
  private final ObjectMapper mapper = new ObjectMapper();
  private final HttpClient httpClient = HttpClient.newHttpClient();
  private final ExecutorService executor = Executors.newCachedThreadPool();

  private String shortPrompt(String q) {
    return q + "\nRespond concisely.";
  }

  public String explain(String question) {
    return "AI explanation for: " + question;
  }

  public String helpCard(Long cardId) {
    return "AI help for card " + cardId;
  }

  public String generateCards(String topic) {
    return "Generated cards for " + topic;
  }

  private String loadApiKey() {
    String envKey = System.getenv("OPENAI_API_KEY");
    if (envKey != null && !envKey.isBlank()) {
      return envKey;
    }
    try {
      return Files.readString(Path.of("openai_api_key.txt")).trim();
    } catch (Exception e) {
      return null;
    }
  }

  public String generateAnswer(String question, String engine) {
    String apiKey = loadApiKey();
    String prompt = shortPrompt(question);

    String mode = engine == null ? "auto" : engine.toLowerCase();

    try {
      if ("deepseek".equals(mode)) {
        return callLocalModel(prompt, false);
      }

      if ("chatgpt".equals(mode) || "auto".equals(mode)) {
        if (apiKey != null && !apiKey.isBlank()) {
          try {
            return callOpenAi(prompt, apiKey);
          } catch (Exception e) {
            if (!"auto".equals(mode)) {
              throw e;
            }
          }
        }
      }
      // fallback to local model when auto and openai fails or api key missing
      return callLocalModel(prompt, false);
    } catch (Exception e) {
      e.printStackTrace();
      return "AI error: could not generate an answer.";
    }
  }

  public SseEmitter streamAnswer(String question, String engine) {
    SseEmitter emitter = new SseEmitter();
    executor.submit(() -> {
      try {
        String apiKey = loadApiKey();
        String prompt = shortPrompt(question);
        String mode = engine == null ? "auto" : engine.toLowerCase();

        if ("deepseek".equals(mode)) {
          callLocalModelStream(prompt, emitter);
        } else if ("chatgpt".equals(mode)) {
          callOpenAiStream(prompt, emitter, apiKey);
        } else {
          try {
            callOpenAiStream(prompt, emitter, apiKey);
          } catch (Exception e) {
            callLocalModelStream(prompt, emitter);
          }
        }
      } catch (Exception e) {
        emitter.completeWithError(e);
      }
    });
    return emitter;
  }

  private String callOpenAi(String prompt, String apiKey) throws Exception {
    Map<String, Object> msg = new HashMap<>();
    msg.put("role", "user");
    msg.put("content", prompt);
    Map<String, Object> body = new HashMap<>();
    body.put("model", "gpt-3.5-turbo");
    body.put("messages", java.util.List.of(msg));
    String json = mapper.writeValueAsString(body);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.openai.com/v1/chat/completions"))
        .header("Authorization", "Bearer " + apiKey)
        .header("Content-Type", "application/json")
        .timeout(Duration.ofSeconds(30))
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .build();
    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    JsonNode node = mapper.readTree(response.body());
    return node.get("choices").get(0).get("message").get("content").asText().trim();
  }

  private String callLocalModel(String prompt, boolean stream) throws Exception {
    Map<String, Object> body = new HashMap<>();
    body.put("model", "deepseek-r1:8b");
    body.put("prompt", prompt);
    body.put("stream", stream);
    String json = mapper.writeValueAsString(body);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:11434/api/generate"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .build();
    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    JsonNode node = mapper.readTree(response.body());
    return node.path("response").asText().trim();
  }

  private void callLocalModelStream(String prompt, SseEmitter emitter) throws Exception {
    System.out.println("hi");
    Map<String, Object> body = new HashMap<>();
    body.put("model", "deepseek-r1:8b");
    body.put("prompt", prompt);
    body.put("stream", true);
    String json = mapper.writeValueAsString(body);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:11434/api/generate"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .build();
    HttpResponse<java.io.InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.body(), StandardCharsets.UTF_8))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.isBlank())
          continue;
        JsonNode node = mapper.readTree(line);
        String token = node.path("response").asText();
        emitter.send(token);
      }
    }
    emitter.complete();
  }

  private void callOpenAiStream(String prompt, SseEmitter emitter, String apiKey) throws Exception {
    Map<String, Object> msg = new HashMap<>();
    msg.put("role", "user");
    msg.put("content", prompt);
    Map<String, Object> body = new HashMap<>();
    body.put("model", "gpt-3.5-turbo");
    body.put("messages", java.util.List.of(msg));
    body.put("stream", true);
    String json = mapper.writeValueAsString(body);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.openai.com/v1/chat/completions"))
        .header("Authorization", "Bearer " + apiKey)
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .build();
    HttpResponse<java.io.InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.body(), StandardCharsets.UTF_8))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (!line.startsWith("data:"))
          continue;
        String data = line.substring(5).trim();
        if (data.equals("[DONE]"))
          break;
        JsonNode node = mapper.readTree(data);
        String token = node.path("choices").get(0).path("delta").path("content").asText();
        if (!token.isEmpty()) {
          emitter.send(token);
        }
      }
    }
    emitter.complete();
  }
}
