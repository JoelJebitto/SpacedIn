import { useParams, Link } from 'react-router-dom'
import CardList from '../components/CardList.jsx'

export default function Deck() {
  const { id } = useParams()
  return (
    <div className="p-4 space-y-4">
      <Link to="/dashboard" className="text-blue-600">Back</Link>
      <CardList deckId={id} />
    </div>
  )
}
