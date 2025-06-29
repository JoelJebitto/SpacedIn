import { useEffect } from "react";
import AddCardButton from "./AddCardButton";
import LearnButton from "./LearnButton";
import ProgressBar from "./ProgressBar";
import useDeckProgressStore from "../store/useDeckProgressStore";

const DeckOverview = ({ deckId }) => {
  const { progressMap, fetchProgress } = useDeckProgressStore();
  useEffect(() => {
    fetchProgress(deckId);
  }, [deckId, fetchProgress]);

  const progress = progressMap[deckId] ?? 0;

  return (
    <div className="bg-black/80 p-5 flex flex-col rounded-xl shadow-2xl mt-10">
      <ProgressBar val={progress} title="Deck Progress" />
      <div className="flex sm:flex-row-reverse flex-col-reverse  sm:pt-5">
        <LearnButton className="w-full sm:w-1/3" />
        <AddCardButton deckId={deckId} className="w-full sm:w-1/3 sm:mx-2" />
      </div>
    </div>
  );
};

export default DeckOverview;
