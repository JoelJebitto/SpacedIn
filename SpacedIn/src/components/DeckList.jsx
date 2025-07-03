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
          className="rounded p-2 flex-1 active:outline-none active:border-none focus:outline-none focus:border-none active:bg-gray-700 focus:bg-gray-700  active:text-gray-100 focus:text-gray-100 bg-gray-600/90 text-gray-200"
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
          <li
            key={d.id}
            className="border border-gray-700 rounded p-4 space-y-1bg-black/90 shadow-2xl"
          >
            {editingId === d.id ? (
              <div className="flex justify-between pb-2 items-center gap-2">
                <input
                  value={editTitle}
                  onChange={(e) => setEditTitle(e.target.value)}
                  className="border border-gray-600 rounded p-2 flex-1 bg-black/85 text-gray-100"
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
                <div className="flex gap-2 mb-2">
                  <button
                    onClick={() => startEdit(d)}
                    className="text-blue-600"
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      fill="none"
                      viewBox="0 0 24 24"
                      strokeWidth={1.5}
                      stroke="currentColor"
                      className="size-6"
                    >
                      <path
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10"
                      />
                    </svg>
                  </button>
                  <button onClick={() => remove(d.id)} className="text-red-600">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      fill="none"
                      viewBox="0 0 24 24"
                      strokeWidth={1.5}
                      stroke="currentColor"
                      className="size-6"
                    >
                      <path
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0"
                      />
                    </svg>
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
