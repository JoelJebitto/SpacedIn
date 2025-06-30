import { useEffect, useState } from "react";
import useAuth from "../store/useAuth";
import { api } from "../services/api";
import { Link } from "react-router-dom";

export default function DeckList({ userId, onChange }) {
  const { user } = useAuth();
  const [decks, setDecks] = useState([]);
  const [stats, setStats] = useState({});
  const [title, setTitle] = useState("");
  const [editingId, setEditingId] = useState(null);
  const [editTitle, setEditTitle] = useState("");

  useEffect(() => {
    if (user?.id) {
      api.getDecks(user.id).then(setDecks).catch(console.error);
    }
  }, [user]);

  useEffect(() => {
    if (userId && decks.length) {
      decks.forEach((d) => {
        api
          .getDeckStats(d.id, userId)
          .then((s) => setStats((prev) => ({ ...prev, [d.id]: s })))
          .catch(console.error);
      });
    }
  }, [decks, userId]);

  const create = async (e) => {
    e.preventDefault();
    if (!title.trim()) return;
    const deck = await api.createDeck(user.id, { title });
    setDecks([...decks, deck]);
    setTitle("");
    onChange && onChange();
  };

  const remove = async (id) => {
    await api.deleteDeck(id);
    setDecks(decks.filter((d) => d.id !== id));
    onChange && onChange();
  };

  const startEdit = (deck) => {
    setEditingId(deck.id);
    setEditTitle(deck.title);
  };

  const save = async (id) => {
    if (!editTitle.trim()) return;
    const updated = await api.updateDeck(id, { title: editTitle });
    setDecks(decks.map((d) => (d.id === id ? updated : d)));
    setEditingId(null);
    onChange && onChange();
  };

  return (
    <div className="p-4 space-y-4 text-gray-100">
      <form onSubmit={create} className="flex gap-2">
        <input
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="New Deck"
          className="border border-gray-600 rounded p-2 flex-1 bg-gray-700 text-gray-100"
        />
        <button
          className="bg-green-600 text-white rounded px-3 disabled:opacity-50"
          disabled={!title.trim()}
        >
          Add
        </button>
      </form>
      <ul className="space-y-2">
        {decks.map((d) => (
          <li key={d.id} className="border border-gray-600 rounded p-4 space-y-1 bg-gray-800 shadow">

            {editingId === d.id ? (
              <div className="flex justify-between items-center gap-2">
                <input
                  value={editTitle}
                  onChange={(e) => setEditTitle(e.target.value)}
                  className="border border-gray-600 rounded p-2 flex-1 bg-gray-700 text-gray-100"

                />
                <button
                  onClick={() => save(d.id)}
                  className="text-green-600 disabled:opacity-50"
                  disabled={!editTitle.trim()}
                >
                  Save
                </button>
                <button
                  onClick={() => setEditingId(null)}
                  className="text-gray-400"
                >
                  Cancel
                </button>
              </div>
            ) : (
              <div className="flex justify-between items-center">
                <Link to={`/decks/${d.id}`}>{d.title}</Link>
                <div className="flex gap-2">
                  <button
                    onClick={() => startEdit(d)}
                    className="text-blue-600"
                  >
                    Edit
                  </button>
                  <button onClick={() => remove(d.id)} className="text-red-600">
                    X
                  </button>
                </div>
              </div>
            )}
            {stats[d.id] && (
              <div className="space-y-1">
                <div className="w-full h-2 bg-gray-700 rounded">
                  <div
                    className="h-full bg-blue-600 rounded"
                    style={{
                      width: `${(stats[d.id].reviewedCards / stats[d.id].totalCards) * 100}%`,
                    }}
                  />
                </div>
                <div className="text-xs text-gray-300">
                  Reviewed {stats[d.id].reviewedCards} /{" "}
                  {stats[d.id].totalCards} (Due {stats[d.id].dueCards})
                </div>
              </div>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}
