const BASE = "http://localhost:8080";

async function request(path, options = {}) {
  const token = localStorage.getItem("token");
  const headers = {
    "Content-Type": "application/json",
    ...(options.headers || {}),
  };
  if (token) headers.Authorization = `Bearer ${token}`;

  const res = await fetch(BASE + path, { ...options, headers });

  if (!res.ok) {
    const errorText = await res.text();
    throw new Error(`API error: ${res.status} - ${errorText}`);
  }

  const contentType = res.headers.get("content-type");
  if (contentType && contentType.includes("application/json")) {
    return res.json();
  } else {
    return null; // return null or custom response for empty body
  }
}

export const api = {
  login: (data) =>
    request("/api/auth/login", { method: "POST", body: JSON.stringify(data) }),
  register: (data) =>
    request("/api/auth/register", {
      method: "POST",
      body: JSON.stringify(data),
    }),
  getDecks: (userId) => request(`/api/decks/user/${userId}`),
  createDeck: (userId, data) =>
    request(`/api/decks/${userId}`, {
      method: "POST",
      body: JSON.stringify(data),
    }),
  updateDeck: (id, data) =>
    request(`/api/decks/${id}`, { method: "PUT", body: JSON.stringify(data) }),
  deleteDeck: (id) => request(`/api/decks/${id}`, { method: "DELETE" }),
  getCards: (deckId) => request(`/api/decks/${deckId}/cards`),
  createCard: (data) =>
    request("/api/cards", { method: "POST", body: JSON.stringify(data) }),
  updateCard: (id, data) =>
    request(`/api/cards/${id}`, { method: "PUT", body: JSON.stringify(data) }),
  deleteCard: (id) => request(`/api/cards/${id}`, { method: "DELETE" }),
  getDueCards: (deckId, userId) =>
    request(`/api/decks/${deckId}/due-cards?userId=${userId}`),
  reviewCard: (cardId, userId, quality) =>
    request(`/api/cards/${cardId}/review?userId=${userId}&quality=${quality}`, {
      method: "POST",
    }),
  getUserStats: (userId) => request(`/api/stats/user/${userId}`),
  getDeckStats: (deckId, userId) =>
    request(`/api/stats/deck/${deckId}?userId=${userId}`),
  generateAnswer: (question, engine = "auto") =>
    request(`/api/ai/answer?engine=${engine}`, {
      method: "POST",
      body: JSON.stringify(question),
    }),
  streamAnswer: (question, onMessage, onDone, engine = "auto") => {
    const es = new EventSource(
      `${BASE}/api/ai/answer-stream?question=${encodeURIComponent(
        question,
      )}&engine=${engine}`,
    );
    es.onmessage = (e) => onMessage(e.data);
    es.onerror = () => {
      es.close();
      onDone && onDone();
    };
    return () => es.close();
  },
};
