import { useState, useRef } from "react";
import useCardStore from "../store/useCardStore";

function AddCardButton({ deckId, className }) {
  const [open, setOpen] = useState(false);
  const front = useRef();
  const back = useRef();
  const { createCard } = useCardStore();

  const handleCreate = () => {
    createCard(deckId, front.current.value, back.current.value);
    setOpen(false);
  };

  return (
    <>
      <button
        onClick={() => setOpen(true)}
        className={`bg-green-700 p-5 shadow-2xl mt-3 flex justify-center rounded-2xl text-gray-200 font-bold active:bg-green-500 hover:bg-green-600 ${className}`}
      >
        Add Card
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          strokeWidth={1.5}
          stroke="currentColor"
          className="size-6 mx-1"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            d="M12 9v6m3-3H9m12 0a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
          />
        </svg>
      </button>

      {open && (
        <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
          <div className="bg-gray-900 p-6 rounded-xl border border-gray-700 w-full max-w-md shadow-2xl relative">
            <button
              onClick={() => setOpen(false)}
              className="absolute top-2 right-3 text-gray-100 hover:text-red-500 text-xl"
            >
              &times;
            </button>
            <h2 className="text-lg font-semibold mb-4 text-gray-200">Add New Card</h2>
            <input
              ref={front}
              type="text"
              placeholder="Front"
              className="w-full px-4 py-2 mb-3 bg-gray-900 border rounded-md text-white focus:outline-none focus:ring focus:ring-blue-400"
            />
            <input
              ref={back}
              type="text"
              placeholder="Back"
              className="w-full px-4 py-2 mb-3 bg-gray-900 border rounded-md text-white focus:outline-none focus:ring focus:ring-blue-400"
            />
            <button
              className="mt-2 bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded"
              onClick={handleCreate}
            >
              Create
            </button>
          </div>
        </div>
      )}
    </>
  );
}

export default AddCardButton;
