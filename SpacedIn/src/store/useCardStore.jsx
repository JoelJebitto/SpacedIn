import { create } from "zustand";

const useCardStore = create((set) => ({
  cards: [],
  getCards: async (deckId) => {
    try {
      const token = localStorage.getItem("token");
      const res = await fetch(
        `http://localhost:8080/api/v1/decks/${deckId}/cards`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (!res.ok) throw new Error("Failed to fetch cards");
      const data = await res.json();
      set({ cards: data });
    } catch (error) {
      console.error(error);
    }
  },
  createCard: async (deckId, front, back) => {
    try {
      const token = localStorage.getItem("token");
      const res = await fetch(
        `http://localhost:8080/api/v1/decks/${deckId}/cards`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
          body: JSON.stringify({ front, back }),
        }
      );
      if (!res.ok) throw new Error("Failed to create card");
      const card = await res.json();
      set((state) => ({ cards: [...state.cards, card] }));
    } catch (error) {
      console.error(error);
    }
  },
}));

export default useCardStore;
