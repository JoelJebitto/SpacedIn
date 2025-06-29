import { useNavigate } from "react-router-dom";

export default function Landing() {
  const navigate = useNavigate();

  const handleStart = () => {
    navigate("/signup");
  };

  return (
    <div className="flex flex-col items-center justify-center text-center py-40 px-5">
      <h1 className="text-5xl font-bold text-gray-900 mb-6">Welcome to SpacedIn</h1>
      <p className="text-gray-800 text-lg mb-8">
        Master your subjects with spaced repetition.
      </p>
      <button
        onClick={handleStart}
        className="bg-black text-white px-6 py-3 rounded-xl shadow-2xl hover:bg-gray-800"
      >
        Get Started
      </button>
    </div>
  );
}
