import { useState } from 'react'

export default function Register() {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [email, setEmail] = useState('')

  const handleSubmit = async (e) => {
    e.preventDefault()
    await fetch('/api/auth/register', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password, email })
    })
  }

  return (
    <form onSubmit={handleSubmit} className="p-4 flex flex-col gap-2">
      <input value={username} onChange={e => setUsername(e.target.value)} placeholder="Username" className="border p-2" />
      <input value={email} onChange={e => setEmail(e.target.value)} placeholder="Email" className="border p-2" />
      <input type="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="Password" className="border p-2" />
      <button className="bg-blue-600 text-white p-2">Register</button>
    </form>
  )
}
