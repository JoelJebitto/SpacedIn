function AddCardButton({ className }) {
  return (
    <button
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
  );
}

export default AddCardButton;
