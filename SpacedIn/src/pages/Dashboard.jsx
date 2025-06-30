import DeckList from "../components/DeckList.jsx";
import useAuth from "../store/useAuth";
import { Navigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { api } from "../services/api";

export default function Dashboard() {
  const { token, user } = useAuth();
  const [stats, setStats] = useState(null);

  useEffect(() => {
    if (user?.id) {
      api.getUserStats(user.id).then(setStats).catch(console.error);
    }
  }, [user]);

  if (!token) return <Navigate to="/" replace />;
  return (
    <div className="p-4 space-y-4 w-full">
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
          <div className="text-sm text-gray-200">
            Reviewed {stats.reviewedCards} / {stats.totalCards} cards (Due{" "}
            {stats.dueCards})
          </div>
        </div>
      )}
      <DeckList userId={user?.id} />
    </div>
  );
}
