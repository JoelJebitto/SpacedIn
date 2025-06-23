import { useState } from "react";

export default function AddDeckButton() {
  const [open, setOpen] = useState(false);

  return (
    <>
      {/* Floating Button */}
      <div className="fixed bottom-6 right-6 group">
        <button
          onClick={() => setOpen(true)}
          className="w-14 h-14 flex items-center justify-center rounded-full bg-blue-600 hover:bg-blue-700 text-white text-2xl shadow-2xl transition-all"
        >
          +
        </button>
        <span className="absolute bottom-16 right-0 mb-2 opacity-0 group-hover:opacity-100 bg-gray-800 text-white text-xs px-2 py-1 rounded transition">
          Add Deck
        </span>
      </div>

      {/* Modal */}
      {open && (
        <div className="fixed inset-0 bg-black/50 flex  items-center justify-center z-50">
          <div className="bg-gray-900 p-6 rounded-xl border border-gray-100 w-full max-w-md shadow-lg relative">
            <button
              onClick={() => setOpen(false)}
              className="absolute top-2 right-3 text-gray-100 hover:text-red-500 text-xl"
            >
              &times;
            </button>
            <h2 className="text-lg font-semibold mb-4 text-gray-200 ">
              Add New Deck
            </h2>
            <input
              type="text"
              placeholder="Deck name"
              className="w-full px-4 py-2 border rounded-md focus:outline-none bg-gray-900 text-white focus:ring focus:ring-blue-400 "
            />
            <button className="mt-4 bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded">
              Create
            </button>
          </div>
        </div>
      )}
    </>
  );
}
