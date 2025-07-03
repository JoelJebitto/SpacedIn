import { useEffect, useState } from "react";
import { api } from "../services/api";
import RichTextEditor from "./RichTextEditor";
import LiveThinkingBox from "./LiveThinkingBox";
import useDeepSeekStream from "../hooks/useDeepSeekStream";

export default function CardList({ deckId, onChange }) {
  const [cards, setCards] = useState([]);
  const [question, setQuestion] = useState("");
  const [answer, setAnswer] = useState("");
  const [editingId, setEditingId] = useState(null);
  const [editQuestion, setEditQuestion] = useState("");
  const [editAnswer, setEditAnswer] = useState("");
  const [streamClose, setStreamClose] = useState(null);
  const [isStreaming, setIsStreaming] = useState(false);
  const { reasoningText, onStreamChunk, onStreamEnd, liveRef, reset } =
    useDeepSeekStream();

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

  const startStream = (engine) => {
    if (streamClose) streamClose(); // Close any existing stream

    setIsStreaming(true);
    reset();

    const close = api.streamAnswer(
      question,
      (chunk) => {
        const last = liveRef.current.slice(-1);
        const spaced =
          !last || /\s/.test(last) || /^\s/.test(chunk) ? chunk : " " + chunk;
        onStreamChunk(spaced);
      },
      () => {
        const { answer } = onStreamEnd();
        setIsStreaming(false);
        setAnswer(answer.trim());
      },
      engine,
    );

    // ✅ Properly store the stream closer
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
            onClick={() => startStream("chatgpt")}
            className="text-sm text-blue-400"
            disabled={!question.trim() || isStreaming}
          >
            ChatGPT
          </button>
          <button
            type="button"
            onClick={() => startStream("deepseek")}
            className="text-sm text-purple-400"
            disabled={!question.trim() || isStreaming}
          >
            DeepSeek
          </button>
        </div>
        {isStreaming && <LiveThinkingBox text={reasoningText} />}
        <button
          className="bg-green-600 text-white rounded px-3 mt-2 disabled:opacity-50"
          disabled={!question.trim() || !answer.trim() || isStreaming}
        >
          Add
        </button>
      </form>

      <ul className="space-y-2">
        {cards.map((c) => (
          <li key={c.id} className=" rounded-md shadow-2xl p-4 bg-black/75 ">
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
                <div className="px-2 flex gap-4 my-auto">
                  <button
                    onClick={() => startEdit(c)}
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
                  <button onClick={() => remove(c.id)} className="text-red-600">
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
          </li>
        ))}
      </ul>
    </div>
  );
}
