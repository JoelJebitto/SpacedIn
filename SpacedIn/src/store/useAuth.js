import { create } from 'zustand'
import jwtDecode from 'jwt-decode'

const useAuth = create((set) => ({
  token: localStorage.getItem('token') || null,
  user: null,
  setToken: (token) => {
    localStorage.setItem('token', token)
    const user = jwtDecode(token)
    set({ token, user })
  },
  logout: () => {
    localStorage.removeItem('token')
    set({ token: null, user: null })
  }
}))

export default useAuth
