export default function CircleProgressBar({ val, className }) {
  return (
    <div className={"relative " + className}>
      <svg
        className="size-full -rotate-90"
        viewBox="0 0 36 36"
        xmlns="http://www.w3.org/2000/svg"
      >
        {/* Background Circle */}
        <circle
          cx="18"
          cy="18"
          r="16"
          fill="none"
          className="stroke-current text-gray-200 dark:text-neutral-700"
          strokeWidth="2"
        ></circle>
        {/* Progress Circle */}
        <circle
          cx="18"
          cy="18"
          r="16"
          fill="none"
          className="stroke-current text-blue-400 dark:text-blue-300"
          strokeWidth="2"
          strokeDasharray="100"
          strokeDashoffset={100 - val}
          strokeLinecap="round"
        ></circle>
      </svg>

      {/* Percentage Text */}
      <div className="absolute top-1/2 start-1/2 transform -translate-y-1/2 -translate-x-1/2">
        <span className="text-center text-2xl font-bold text-blue-400 dark:text-blue-300">
          {val}%
        </span>
      </div>
    </div>
  );
}
