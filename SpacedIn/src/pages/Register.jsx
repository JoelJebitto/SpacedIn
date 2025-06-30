import { useState } from 'react'
import { api } from '../services/api'
import { Link, Navigate } from 'react-router-dom'
import useAuth from '../store/useAuth'

export default function Register() {
  const { token } = useAuth()
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [email, setEmail] = useState('')
  const [done, setDone] = useState(false)

  const handleSubmit = async (e) => {
    e.preventDefault()
    await api.register({ username, password, email })
    setDone(true)
  }

  if (token) return <Navigate to="/dashboard" replace />
  if (done) return <Navigate to="/" replace />

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
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        placeholder="Email"
        className="border rounded p-2"
      />
      <input
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        placeholder="Password"
        className="border rounded p-2"
      />
      <button className="bg-blue-600 text-white rounded p-2">Register</button>
      <Link to="/" className="text-blue-600 text-center">Login</Link>
    </form>
  )
}
