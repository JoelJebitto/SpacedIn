import DeckList from '../components/DeckList.jsx'
import useAuth from '../store/useAuth'
import { Navigate } from 'react-router-dom'

export default function Dashboard() {
  const { token, logout } = useAuth()
  if (!token) return <Navigate to="/" replace />
  return (
    <div className="p-4 space-y-4">
      <button onClick={logout} className="text-blue-600">Logout</button>
      <DeckList />
    </div>
  )
}
