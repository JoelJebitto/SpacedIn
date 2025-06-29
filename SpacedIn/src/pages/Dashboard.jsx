import DeckList from '../components/DeckList.jsx'
import useAuth from '../store/useAuth'
import { Navigate } from 'react-router-dom'

export default function Dashboard() {
  const { token } = useAuth()
  if (!token) return <Navigate to="/" replace />
  return <DeckList />
}
