import { create } from "zustand";

const useDeckProgressStore = create((set) => ({
  progressMap: {},

  fetchProgress: async (deckId) => {
    try {
      const token = localStorage.getItem("token");
      if (!token) throw new Error("No auth token found");
      const res = await fetch(
        `http://localhost:8080/api/v1/decks/${deckId}/progress`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        },
      );

      if (res.status === 404 || res.status === 403) {
        set((state) => ({
          progressMap: { ...state.progressMap, [deckId]: 0 },
        }));
        return;
      }

      if (!res.ok) throw new Error("Failed to fetch progress");
      const data = await res.json();
      set((state) => ({
        progressMap: { ...state.progressMap, [deckId]: Number(data.progress) },
      }));
    } catch (error) {
      console.error(error);
    }
  },
}));

export default useDeckProgressStore;
