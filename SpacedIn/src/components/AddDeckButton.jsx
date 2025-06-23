const AddDeckButton = () => {
  return (
    <div>
      <div className="fixed bottom-6 right-6 group">
        <button
          className="w-14 h-14 flex items-center justify-center rounded-full bg-blue-600 hover:bg-blue-700 text-white text-2xl shadow-lg transition-all"
          aria-label="Add Deck"
        >
          +
        </button>
        <span className="absolute bottom-16 right-0 mb-2 opacity-0 group-hover:opacity-100 bg-black/80 text-white text-xs px-2 py-1 rounded transition">
          Add Deck
        </span>
      </div>{" "}
    </div>
  );
};

export default AddDeckButton;
