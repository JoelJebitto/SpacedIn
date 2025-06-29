import LearnButton from "./LearnButton";
import { useNavigate } from "react-router-dom";
import CircleProgressBar from "./CircleProgressBar";

export default function Deck({
  title,
  description,
  id,
  createdOn,
  progress = 0,
}) {
  const dateObject = new Date(createdOn);

  const options = {
    year: "numeric",
    month: "long",
    day: "numeric",
    weekday: "long",
  };
  const formattedDate = dateObject.toLocaleDateString(undefined, options);
  const navigate = useNavigate();
  const handleOpen = () => {
    navigate(`/deck/${id}`);
  };

  return (
    <div className="bg-black/50 flex flex-col mx-auto my-2 p-5 text-white w-full md:w-[47%] rounded-xl shadow-2xl ">
      <div
        onClick={handleOpen}
        className="flex justify-between cursor-pointer items-start"
      >
        <div>
          <h1 className="text-3xl font-bold">{title}</h1>
          <p className="text-gray-300 text-md">{formattedDate}</p>
        </div>
        <CircleProgressBar val={progress} className="size-16" />
      </div>
      <hr className="border-2 border-gray-800 my-2 " />
      <p className="text-gray-100 flex-1 text-lg">{description}</p>

      <LearnButton className="mt-20" />
    </div>
  );
}
