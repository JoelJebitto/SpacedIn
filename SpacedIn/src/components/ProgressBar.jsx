export default function ProgressBar({ val = 75, title = "Progress title" }) {
  return (
    <div className="flex-1 md:mb-0 mb-5">
      <div className="mb-2 flex justify-between items-center ">
        <h3 className="text-sm font-semibold text-gray-800 dark:text-white">
          {title}
        </h3>
        <span className="text-sm text-gray-800 dark:text-white">{val}%</span>
      </div>
      <div
        className="flex w-full h-2 bg-gray-200 rounded-full overflow-hidden dark:bg-neutral-700"
        role="progressbar"
        aria-valuenow={val}
        aria-valuemin="0"
        aria-valuemax="100"
      >
        <div
          className="flex flex-col justify-center rounded-full overflow-hidden bg-blue-600 text-xs text-white text-center whitespace-nowrap transition duration-500 dark:bg-blue-500"
          style={{ width: `${val}%` }}
        ></div>
      </div>
    </div>
  );
}
