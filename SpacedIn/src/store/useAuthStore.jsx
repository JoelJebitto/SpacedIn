// src/store/useAuthStore.js
import { create } from "zustand";

import { jwtDecode } from "jwt-decode";

const useAuthStore = create((set, get) => ({
  token: localStorage.getItem("token") || null,
  setToken: (newToken) => {
    localStorage.setItem("token", newToken);
    set({ token: newToken });
  },
  isTokenValid: () => {
    try {
      const decoded = jwtDecode(get().token);
      const now = Date.now() / 1000; // current time in seconds
      const r = decoded.exp && decoded.exp > now;
      !r && get().logout();
      return r;
    } catch {
      return false;
    }
  },
  logout: () => {
    localStorage.removeItem("token");
    set({ token: null });
  },
}));
export default useAuthStore;
