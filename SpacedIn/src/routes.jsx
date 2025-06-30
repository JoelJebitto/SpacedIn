import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Login from './pages/Login.jsx'
import Register from './pages/Register.jsx'
import Dashboard from './pages/Dashboard.jsx'
import Deck from './pages/Deck.jsx'
import Review from './pages/Review.jsx'

export default function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/decks/:id" element={<Deck />} />
        <Route path="/decks/:id/review" element={<Review />} />
      </Routes>
    </BrowserRouter>
  )
}
