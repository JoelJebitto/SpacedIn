import { useParams, Link } from "react-router-dom";
import CardList from "../components/CardList.jsx";
import { useEffect, useState, useCallback } from "react";
import useAuth from "../store/useAuth";
import { api } from "../services/api";

export default function Deck() {
  const { id } = useParams();
  const { user } = useAuth();
  const [stats, setStats] = useState(null);

  const refreshStats = useCallback(() => {
    if (user?.id) {
      api.getDeckStats(id, user.id).then(setStats).catch(console.error);
    }
  }, [id, user]);

  useEffect(() => {
    refreshStats();
  }, [refreshStats]);

  return (
    <div className="p-4 space-y-4 w-full">
      <Link to="/dashboard" className="text-blue-600">
        Back
      </Link>
      {stats && (
        <div className="space-y-1">
          <div className="w-full h-2 bg-gray-200 rounded">
            <div
              className="h-full bg-blue-600 rounded"
              style={{
                width: `${(stats.reviewedCards / stats.totalCards) * 100}%`,
              }}
            />
          </div>
          <div className="text-sm text-gray-700">
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
