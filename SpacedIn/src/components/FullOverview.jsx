import CircleProgressBar from "./CircleProgressBar";
import LearnButton from "./LearnButton";
import ProgressBar from "./ProgressBar";

const FullOverview = () => {
  return (
    <div className="bg-black/80 p-5 flex flex-col md:flex-row rounded-xl shadow-2xl">
      <CircleProgressBar val={90} className="size-72 md:size-80 mx-auto" />
      <div className="p-5 h-full flex flex-col flex-1 mt-5 md:mt-0 md:ml-5">
        <div className="flex flex-col flex-1">
          <ProgressBar title="Chemistry" val={30} />
          <ProgressBar title="English" val={90} />
          <ProgressBar title="Math" val={70} />
          <ProgressBar title="physics" val={20} />
        </div>
        <LearnButton />
      </div>
    </div>
  );
};

export default FullOverview;
