import LearnButton from "./LearnButton";
import ProgressBar from "./ProgressBar";

const DeckOverview = () => {
  return (
    <div className="bg-black/80 p-5 flex flex-col rounded-xl shadow-2xl mt-10">
      <ProgressBar val={70} title="Deck Progress" />
      <div className="flex flex-row-reverse pt-5">
        <LearnButton className="w-1/3" />
      </div>
    </div>
  );
};

export default DeckOverview;
