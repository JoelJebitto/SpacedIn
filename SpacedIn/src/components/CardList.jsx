import { useEffect, useState } from "react";
import { api } from "../services/api";
import RichTextEditor from "./RichTextEditor";
import LiveThinkingBox from "./LiveThinkingBox";
import useReasoningAnswerStream from "../hooks/useReasoningAnswerStream";

export default function CardList({ deckId, onChange }) {
  const [cards, setCards] = useState([]);
  const [question, setQuestion] = useState("");
  const [answer, setAnswer] = useState("");
  const [editingId, setEditingId] = useState(null);
  const [editQuestion, setEditQuestion] = useState("");
  const [editAnswer, setEditAnswer] = useState("");
  const [streamClose, setStreamClose] = useState(null);
  const [isStreaming, setIsStreaming] = useState(false);
  const {
    streamText,
    reasoningText,
    answerText,
    handleChunk,
    handleEnd,
    reset,
  } = useReasoningAnswerStream();

  // Update answer field when the streaming hook provides the final text
  useEffect(() => {
    if (answerText) setAnswer(answerText);
  }, [answerText]);

  useEffect(() => {
    api.getCards(deckId).then(setCards).catch(console.error);
  }, [deckId]);

  const create = async (e) => {
    e.preventDefault();
    if (!question.trim() || !answer.trim() || isStreaming) return;
    const card = await api.createCard({ deckId, question, answer });
    setCards([...cards, card]);
    setQuestion("");
    setAnswer("");
    onChange && onChange();
  };

  const remove = async (id) => {
    await api.deleteCard(id);
    setCards(cards.filter((c) => c.id !== id));
    onChange && onChange();
  };

  const startEdit = (c) => {
    setEditingId(c.id);
    setEditQuestion(c.question);
    setEditAnswer(c.answer);
  };

  const save = async (id) => {
    if (!editQuestion.trim() || !editAnswer.trim()) return;
    const updated = await api.updateCard(id, {
      question: editQuestion,
      answer: editAnswer,
    });
    setCards(cards.map((c) => (c.id === id ? updated : c)));
    setEditingId(null);
    onChange && onChange();
  };

  const startStream = () => {
    if (streamClose) streamClose(); // close any existing stream

    reset();
    setAnswer("");
    setIsStreaming(true);

    const close = api.streamAnswer(question, handleChunk, () => {
      handleEnd();
      setIsStreaming(false);
    });

    setStreamClose(() => close);
  };

  return (
    <div className="space-y-4">
      <form onSubmit={create} className="space-y-2">
        <RichTextEditor
          value={question}
          onChange={setQuestion}
          placeholder="Question"
        />
        <RichTextEditor
          value={answer}
          onChange={setAnswer}
          placeholder="Answer"
        />
        <div className="flex gap-2">
          <button
            type="button"
            onClick={startStream}
            className="text-sm text-blue-400"
            disabled={!question.trim() || isStreaming}
          >
            AI Generate
          </button>
        </div>
        {isStreaming && <LiveThinkingBox text={streamText} />}
        {!isStreaming && reasoningText && (
          <div className="border border-gray-600 bg-gray-800 text-gray-100 p-2 whitespace-pre-wrap">
            {reasoningText}
          </div>
        )}
        <button
          className="bg-green-600 text-white rounded px-3 mt-2 disabled:opacity-50"
          disabled={!question.trim() || !answer.trim() || isStreaming}
        >
          Add
        </button>
      </form>

      <ul className="space-y-2">
        {cards.map((c) => (
          <li
            key={c.id}
            className="border border-gray-600 rounded p-4 bg-gray-800 shadow"
          >
            {editingId === c.id ? (
              <div className="space-y-2">
                <RichTextEditor
                  value={editQuestion}
                  onChange={setEditQuestion}
                />
                <RichTextEditor value={editAnswer} onChange={setEditAnswer} />
                <div className="flex gap-2">
                  <button
                    onClick={() => save(c.id)}
                    className="text-green-600 disabled:opacity-50"
                    disabled={!editQuestion.trim() || !editAnswer.trim()}
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
              </div>
            ) : (
              <div className="flex justify-between items-start">
                <div>
                  <span dangerouslySetInnerHTML={{ __html: c.question }} />
                  <span
                    className="text-sm text-gray-400 block"
                    dangerouslySetInnerHTML={{ __html: c.answer }}
                  />
                </div>
                <div className="flex gap-2">
                  <button
                    onClick={() => startEdit(c)}
                    className="text-blue-600"
                  >
                    Edit
                  </button>
                  <button onClick={() => remove(c.id)} className="text-red-600">
                    X
                  </button>
                </div>
              </div>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}
