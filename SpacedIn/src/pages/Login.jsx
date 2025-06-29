import { useState } from 'react'

export default function Login() {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')

  const handleSubmit = async (e) => {
    e.preventDefault()
    const res = await fetch('/api/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password })
    })
    const data = await res.json()
    localStorage.setItem('token', data.token)
  }

  return (
    <form onSubmit={handleSubmit} className="p-4 flex flex-col gap-2">
      <input value={username} onChange={e => setUsername(e.target.value)} placeholder="Username" className="border p-2" />
      <input type="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="Password" className="border p-2" />
      <button className="bg-blue-600 text-white p-2">Login</button>
    </form>
  )
}
