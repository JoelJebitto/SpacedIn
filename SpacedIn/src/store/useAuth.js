import { create } from 'zustand'
import jwtDecode from 'jwt-decode'

const parse = (token) => {
  try {
    const d = jwtDecode(token)
    return { id: d.uid, username: d.sub }
  } catch {
    return null
  }
}

const stored = localStorage.getItem('token')

const useAuth = create((set) => ({
  token: stored || null,
  user: stored ? parse(stored) : null,
  setToken: (token) => {
    localStorage.setItem('token', token)
    set({ token, user: parse(token) })
  },
  logout: () => {
    localStorage.removeItem('token')
    set({ token: null, user: null })
  }
}))

export default useAuth
