import { useState, useRef } from "react";
import useDeckStore from "../store/useDeckStore";

export default function EditDeckButton({ deck, className, onUpdated }) {
  const [open, setOpen] = useState(false);
  const name = useRef();
  const description = useRef();
  const { updateDeck } = useDeckStore();

  const handleUpdateDeck = async () => {
    const updated = await updateDeck(
      deck.id,
      name.current.value,
      description.current.value,
    );
    if (updated && onUpdated) {
      onUpdated(updated);
    }
    setOpen(false);
  };

  return (
    <>
      <button
        onClick={() => setOpen(true)}
        className={`bg-orange-700 p-5 shadow-2xl mt-3 flex justify-center rounded-2xl text-gray-200 font-bold active:bg-orange-500 hover:bg-orange-600 ${className}`}
      >
        Edit Deck
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
            d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10"
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
            <h2 className="text-lg font-semibold mb-4 text-gray-200 ">
              Edit Deck
            </h2>
            <input
              ref={name}
              defaultValue={deck.title}
              type="text"
              placeholder="Deck name"
              className="w-full px-4 py-2 mb-3 border rounded-md focus:outline-none bg-gray-900 text-white focus:ring focus:ring-blue-400 "
            />
            <textarea
              ref={description}
              defaultValue={deck.description}
              placeholder="Description"
              className="w-full px-4 py-2 mb-3 border rounded-md focus:outline-none bg-gray-900 text-white focus:ring focus:ring-blue-400"
            />
            <button
              className="mt-4 bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded"
              onClick={handleUpdateDeck}
            >
              Save
            </button>
          </div>
        </div>
      )}
    </>
  );
}
