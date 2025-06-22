import ProgressBar from "./ProgressBar";

export default function DeckProgress() {
  return (
    <div className="flex-1 my-3 text-white">
      <h1 className="text-lg font-bold">English</h1>
      <ProgressBar val="90" />
    </div>
  );
}
