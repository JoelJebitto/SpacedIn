import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { api } from "../services/api";
import useAuth from "../store/useAuth";

export default function Review() {
  const { id } = useParams();
  const { user } = useAuth();
  const [cards, setCards] = useState([]);
  const [index, setIndex] = useState(0);
  const [showAnswer, setShowAnswer] = useState(false);
  const [reviewed, setReviewed] = useState(0);
  const [totalCards, setTotalCards] = useState(0);

  useEffect(() => {
    if (user?.id) {
      api
        .getDueCards(id, user.id)
        .then((data) => {
          setCards(data);
          setTotalCards(data.length);
        })
        .catch(console.error);
    }
  }, [id, user]);

  if (!cards.length) {
    return (
      <div className="p-4 space-y-2">
        <Link to={`/decks/${id}`} className="text-blue-600">
          Back
        </Link>
        <p>No cards due.</p>
      </div>
    );
  }

  const card = cards[index];

  const grade = async (q) => {
    await api.reviewCard(card.id, user.id, q);
    setReviewed((r) => r + 1);
    setShowAnswer(false);
    if (index + 1 < cards.length) setIndex(index + 1);
    else setCards([]);
  };

  return (
    <div className="p-4 space-y-4 w-full text-gray-100">
      <Link to={`/decks/${id}`} className="text-blue-600">
        Back
      </Link>
      <div className="space-y-1">
        <div className="w-full h-2 bg-gray-700 rounded">
          <div
            className="h-full bg-blue-600 rounded"
            style={{ width: `${(reviewed / totalCards) * 100}%` }}
          />
        </div>
        <div className="text-sm text-gray-300">
          Reviewed {reviewed} / {totalCards} cards
        </div>
      </div>
      <div className="border border-gray-600 rounded p-4 bg-gray-800 shadow">
        <div dangerouslySetInnerHTML={{ __html: card.question }} />
        {showAnswer && (
          <div
            className="mt-2 text-sm"
            dangerouslySetInnerHTML={{ __html: card.answer }}
          />
        )}
      </div>
      {showAnswer ? (
        <div className="flex gap-2">
          {[0, 1, 2, 3, 4, 5].map((n) => (
            <button key={n} onClick={() => grade(n)} className="border rounded px-2">
              {n}
            </button>
          ))}
        </div>
      ) : (
        <button
          onClick={() => setShowAnswer(true)}
          className="bg-blue-600 text-white rounded px-3 py-2"
        >
          Show Answer
        </button>
      )}
    </div>
  );
}
