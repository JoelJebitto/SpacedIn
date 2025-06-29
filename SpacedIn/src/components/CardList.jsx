import { useEffect, useState } from 'react'
import { api } from '../services/api'

export default function CardList({ deckId }) {
  const [cards, setCards] = useState([])
  const [question, setQuestion] = useState('')
  const [answer, setAnswer] = useState('')

  useEffect(() => {
    api.getCards(deckId).then(setCards).catch(console.error)
  }, [deckId])

  const create = async (e) => {
    e.preventDefault()
    const card = await api.createCard({ deckId, question, answer })
    setCards([...cards, card])
    setQuestion(''); setAnswer('')
  }

  const remove = async (id) => {
    await api.deleteCard(id)
    setCards(cards.filter(c => c.id !== id))
  }

  return (
    <div className="space-y-4">
      <form onSubmit={create} className="flex gap-2">
        <input value={question} onChange={e=>setQuestion(e.target.value)} placeholder="Question" className="border p-2" />
        <input value={answer} onChange={e=>setAnswer(e.target.value)} placeholder="Answer" className="border p-2" />
        <button className="bg-green-600 text-white px-3">Add</button>
      </form>
      <ul className="space-y-2">
        {cards.map(c => (
          <li key={c.id} className="border p-2 flex justify-between items-center">
            <div>
              <span>{c.question}</span>
              <span className="text-sm text-gray-600 block">{c.answer}</span>
            </div>
            <button onClick={() => remove(c.id)} className="text-red-600">X</button>
          </li>
        ))}
      </ul>
    </div>
  )
}
