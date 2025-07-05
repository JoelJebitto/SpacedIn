import { useState, useRef, useCallback } from "react";

// Hook to parse DeepSeek-R1 streamed output.
// The model streams text in the format:
// <think>reasoning...</think>final answer
// reasoningText holds the text between the tags
// answerText holds everything after the closing tag
// liveRef keeps the raw accumulated stream
export default function useDeepSeekStream() {
  const [reasoningText, setReasoningText] = useState("");
  const [answerText, setAnswerText] = useState("");
  const liveRef = useRef("");

  const parse = useCallback(() => {
    const text = liveRef.current;
    const start = text.indexOf("<think>");
    const end = text.indexOf("</think>");
    // </think>
    let reasoning = "";
    let answer = "";

    if (start !== -1) {
      reasoning = text.slice(start + 7, end === -1 ? undefined : end);

      // Remove any trailing partial closing tag tokens
      if (end === -1) {
        const closing = " \u003c/think\u003e";
        for (let i = 1; i < closing.length; i++) {
          if (reasoning.endsWith(closing.slice(0, i))) {
            reasoning = reasoning.slice(0, -i);
            break;
          }
        }
      }

      if (end !== -1) {
        answer = text.slice(end + 8);
      }
    } else {
      answer = text;
    }

    setReasoningText(reasoning.trim());
    setAnswerText(answer.trim());
    console.log(reasoning);
    console.log(answer);
    return { reasoning, answer };
  }, []);
  //
  const onStreamChunk = useCallback(
    (chunk) => {
      liveRef.current += chunk;
      return parse();
    },
    [parse],
  );

  const onStreamEnd = useCallback(() => {
    const result = parse();
    liveRef.current = "";
    return result;
  }, [parse]);

  const reset = useCallback(() => {
    liveRef.current = "";
    setReasoningText("");
    setAnswerText("");
  }, []);

  return {
    reasoningText,
    answerText,
    onStreamChunk,
    onStreamEnd,
    liveRef,
    reset,
  };
}
