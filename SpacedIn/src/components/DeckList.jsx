import { useEffect, useState } from 'react'
import useAuth from '../store/useAuth'
import { api } from '../services/api'
import { Link } from 'react-router-dom'

export default function DeckList() {
  const { user } = useAuth()
  const [decks, setDecks] = useState([])
  const [title, setTitle] = useState('')

  useEffect(() => {
    if (user?.id) {
      api.getDecks(user.id).then(setDecks).catch(console.error)
    }
  }, [user])

  const create = async (e) => {
    e.preventDefault()
    const deck = await api.createDeck(user.id, { title })
    setDecks([...decks, deck])
    setTitle('')
  }

  return (
    <div className="p-4 space-y-4">
      <form onSubmit={create} className="flex gap-2">
        <input value={title} onChange={e=>setTitle(e.target.value)} placeholder="New Deck" className="border p-2" />
        <button className="bg-green-600 text-white px-3">Add</button>
      </form>
      <ul className="space-y-2">
        {decks.map(d => (
          <li key={d.id} className="border p-2">
            <Link to={`/decks/${d.id}`}>{d.title}</Link>
          </li>
        ))}
      </ul>
    </div>
  )
}
