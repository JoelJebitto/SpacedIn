const BASE = ''

async function request(path, options = {}) {
  const token = localStorage.getItem('token')
  const headers = { 'Content-Type': 'application/json', ...(options.headers || {}) }
  if (token) headers.Authorization = `Bearer ${token}`
  const res = await fetch(BASE + path, { ...options, headers })
  if (!res.ok) throw new Error('API error')
  return res.json()
}

export const api = {
  login: (data) => request('/api/auth/login', { method: 'POST', body: JSON.stringify(data) }),
  register: (data) => request('/api/auth/register', { method: 'POST', body: JSON.stringify(data) }),
  getDecks: (userId) => request(`/api/decks/user/${userId}`),
  createDeck: (userId, data) => request(`/api/decks/${userId}`, { method: 'POST', body: JSON.stringify(data) }),
  updateDeck: (id, data) => request(`/api/decks/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
  deleteDeck: (id) => request(`/api/decks/${id}`, { method: 'DELETE' }),
  getCards: (deckId) => request(`/api/decks/${deckId}/cards`),
  createCard: (data) => request('/api/cards', { method: 'POST', body: JSON.stringify(data) }),
  updateCard: (id, data) => request(`/api/cards/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
  deleteCard: (id) => request(`/api/cards/${id}`, { method: 'DELETE' })
}
