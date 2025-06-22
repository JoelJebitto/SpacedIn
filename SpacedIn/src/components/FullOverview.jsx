import CircleProgressBar from "./CircleProgressBar";
import DeckProgress from "./DeckProgress";

const FullOverview = () => {
  return (
    <div className="bg-gray-600 p-5 flex flex-col md:flex-row rounded-xl">
      <CircleProgressBar val={90} className="size-72 md:size-80 mx-auto" />
      <div className="p-5 h-full flex flex-col flex-1 mt-5 md:mt-0 md:ml-5">
        <DeckProgress />
        <DeckProgress />
        <DeckProgress />
      </div>
    </div>
  );
};

export default FullOverview;
