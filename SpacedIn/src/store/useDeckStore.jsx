import { create } from "zustand";

const useDeckStore = create((set, get) => ({
  // state
  decks: [],

  // fetch all decks for the current user
  getMyDecks: async () => {
    try {
      const token = localStorage.getItem("token");
      if (!token) throw new Error("No auth token found");

      const res = await fetch("http://localhost:8080/api/v1/decks", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });

      if (!res.ok) {
        const err = await res.text();
        throw new Error(`Error fetching decks: ${err}`);
      }

      const decks = await res.json();
      set({ decks });
    } catch (error) {
      console.error(error);
      // you could also set an `error` slice of state here
    }
  },

  // optionally, you can add create/update/delete actions:
  createDeck: async (title, description) => {
    try {
      const token = localStorage.getItem("token");
      const res = await fetch("http://localhost:8080/api/v1/decks", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ title, description }),
      });
      if (!res.ok) throw new Error("Failed to create deck");
      const newDeck = await res.json();
      set((state) => ({ decks: [...state.decks, newDeck] }));
      return null;
    } catch (error) {
      return error;
    }
  },

  updateDeck: async (id, title, description) => {
    try {
      const token = localStorage.getItem("token");
      const res = await fetch(`http://localhost:8080/api/v1/decks/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ title, description }),
      });
      if (!res.ok) throw new Error("Failed to update deck");
      const updated = await res.json();
      set((state) => ({
        decks: state.decks.map((d) => (d.id === id ? updated : d)),
      }));
    } catch (error) {
      console.error(error);
    }
  },

  deleteDeck: async (id) => {
    try {
      const token = localStorage.getItem("token");
      const res = await fetch(`http://localhost:8080/api/v1/decks/${id}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      if (!res.ok) throw new Error("Failed to delete deck");
      set((state) => ({
        decks: state.decks.filter((d) => d.id !== id),
      }));
    } catch (error) {
      console.error(error);
    }
  },
}));

export default useDeckStore;
