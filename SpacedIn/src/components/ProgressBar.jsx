export default function ProgressBar({ val }) {
  return (
    <div className="w-full">
      <div
        className="inline-block mb-2 py-0.5 px-1.5 bg-blue-50 border border-blue-200 text-xs font-medium text-blue-600 rounded-lg"
        style={{ marginInlineStart: `calc(${val}% - 20px)` }}
      >
        {val}%
      </div>{" "}
      <div
        className="flex w-full h-2 bg-gray-200 rounded-full overflow-hidden"
        role="progressbar"
        aria-valuenow={val}
        aria-valuemin="0"
        aria-valuemax="100"
      >
        <div
          className="flex flex-col justify-center rounded-full overflow-hidden bg-blue-600 transition duration-500"
          style={{ width: `${val}%` }}
        ></div>
      </div>
    </div>
  );
}
