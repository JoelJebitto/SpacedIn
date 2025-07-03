import { useParams, Link } from "react-router-dom";
import CardList from "../components/CardList.jsx";
import { useEffect, useState, useCallback } from "react";
import useAuth from "../store/useAuth";
import { api } from "../services/api";

export default function Deck() {
  const { id } = useParams();
  const { user } = useAuth();
  const [stats, setStats] = useState(null);
  const [deck, setDeck] = useState(null);

  const refreshStats = useCallback(() => {
    if (user?.id) {
      api.getDeckStats(id, user.id).then(setStats).catch(console.error);
    }
  }, [id, user]);

  useEffect(() => {
    refreshStats();
  }, [refreshStats]);

  useEffect(() => {
    api.getDeck(id).then(setDeck).catch(console.error);
  }, [id]);

  console.log(stats);

  return (
    <div className="p-4 space-y-4 w-full text-gray-100">
      <Link to="/dashboard" className="text-blue-600 flex gap-1">
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
            d="M6.75 15.75 3 12m0 0 3.75-3.75M3 12h18"
          />
        </svg>
        Back
      </Link>

      {deck && <h1 className="text-4xl font-bold">{deck.title}</h1>}

      {stats && (
        <div className="space-y-1">
          <div className="w-full h-2 bg-gray-700 rounded">
            <div
              className="h-full bg-blue-600 rounded"
              style={{
                width: `${(stats.reviewedCards / stats.totalCards) * 100}%`,
              }}
            />
          </div>
          <div className="text-sm text-gray-300">
            Reviewed {stats.reviewedCards} / {stats.totalCards} cards (Due{" "}
            {stats.dueCards})
          </div>
        </div>
      )}
      <Link
        to={`/decks/${id}/review`}
        className="bg-blue-600 text-white rounded px-3 py-2 inline-block"
      >
        Study
      </Link>

      <CardList deckId={id} onChange={refreshStats} />
    </div>
  );
}
