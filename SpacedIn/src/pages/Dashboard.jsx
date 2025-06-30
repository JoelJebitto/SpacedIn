import DeckList from "../components/DeckList.jsx";
import useAuth from "../store/useAuth";
import { Navigate } from "react-router-dom";
import { useEffect, useState, useCallback } from "react";
import { api } from "../services/api";

export default function Dashboard() {
  const { token, user } = useAuth();
  const [stats, setStats] = useState(null);

  const refreshStats = useCallback(() => {
    if (user?.id) {
      api.getUserStats(user.id).then(setStats).catch(console.error);
    }
  }, [user]);

  useEffect(() => {
    refreshStats();
  }, [refreshStats]);

  if (!token) return <Navigate to="/" replace />;
  return (
    <div className="p-4 space-y-4 w-full text-gray-100">
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
            Reviewed {stats.reviewedCards} / {stats.totalCards} cards (Due{' '}
            {stats.dueCards})
          </div>
        </div>
      )}
      <DeckList userId={user?.id} onChange={refreshStats} />
    </div>
  );
}
