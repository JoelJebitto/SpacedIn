import AddCardButton from "./AddCardButton";
import LearnButton from "./LearnButton";
import ProgressBar from "./ProgressBar";

const DeckOverview = () => {
  return (
    <div className="bg-black/80 p-5 flex flex-col rounded-xl shadow-2xl mt-10">
      <ProgressBar val={70} title="Deck Progress" />
      <div className="flex sm:flex-row-reverse flex-col-reverse  sm:pt-5">
        <LearnButton className="w-full sm:w-1/3" />
        <AddCardButton className="w-full sm:w-1/3 sm:mx-2" />
      </div>
    </div>
  );
};

export default DeckOverview;
