import CircleProgressBar from "./CircleProgressBar";
import ProgressBar from "./ProgressBar";

const FullOverview = () => {
  return (
    <div className="bg-gray-600 p-5 flex flex-col md:flex-row rounded-xl">
      <CircleProgressBar val={90} className="size-72 md:size-80 mx-auto" />
      <div className="p-5 h-full flex flex-col flex-1 mt-5 md:mt-0 md:ml-5">
        <div className="flex flex-col flex-1">
          <ProgressBar title="Chemistry" val={30} />
          <ProgressBar title="English" val={90} />
          <ProgressBar title="Math" val={70} />
          <ProgressBar title="physics" val={20} />
        </div>
        <button className="bg-red-700 p-5 mt-3 flex justify-center rounded-2xl text-gray-200 font-bold active:bg-red-500 hover:bg-red-600">
          Learn
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth={1.5}
            stroke="currentColor"
            className="size-6"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M15.362 5.214A8.252 8.252 0 0 1 12 21 8.25 8.25 0 0 1 6.038 7.047 8.287 8.287 0 0 0 9 9.601a8.983 8.983 0 0 1 3.361-6.867 8.21 8.21 0 0 0 3 2.48Z"
            />
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M12 18a3.75 3.75 0 0 0 .495-7.468 5.99 5.99 0 0 0-1.925 3.547 5.975 5.975 0 0 1-2.133-1.001A3.75 3.75 0 0 0 12 18Z"
            />
          </svg>
        </button>
      </div>
    </div>
  );
};

export default FullOverview;
