import { create } from "zustand";

const useCurrentDeckStore = create((set, get) => ({
  title: "Science",
  description: "Consepts of Class 11",
  id: null,
}));
export default useCurrentDeckStore;
