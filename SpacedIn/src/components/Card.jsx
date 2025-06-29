import { useState } from "react";
import EditCardButton from "./EditCardButton";
import LearnButton from "./LearnButton";

const Card = ({ front, back }) => {
  const [showBack, setShowBack] = useState(false);
  const toggle = () => setShowBack((s) => !s);

  return (
    <div
      onClick={toggle}
      className="bg-black/50 cursor-pointer flex flex-col mx-auto my-2 p-5 text-white w-full sm:w-[80%] md:w-[47%] rounded-xl shadow-2xl"
    >
      <h1 className="text-xl font-semibold mb-2">{front}</h1>
      {showBack && <p className="text-gray-300 flex-1 mb-3">{back}</p>}
      <div className="flex flex-col mt-2">
        <EditCardButton className="w-full" />
        <LearnButton className="w-full" />
      </div>
    </div>
  );
};

export default Card;
