import { useState } from 'react'
import useAuth from '../store/useAuth'
import { api } from '../services/api'
import { Navigate } from 'react-router-dom'

export default function Login() {
  const { token, setToken } = useAuth()
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')

  const handleSubmit = async (e) => {
    e.preventDefault()
    const data = await api.login({ username, password })
    setToken(data.token)
  }

  if (token) return <Navigate to="/dashboard" replace />

  return (
    <form
      onSubmit={handleSubmit}
      className="p-6 bg-white shadow rounded flex flex-col gap-4 max-w-sm mx-auto"
    >
      <input
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        placeholder="Username"
        className="border rounded p-2"
      />
      <input
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        placeholder="Password"
        className="border rounded p-2"
      />
      <button className="bg-blue-600 text-white rounded p-2">Login</button>
    </form>
  )
}
