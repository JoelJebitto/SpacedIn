import EditCardButton from "./EditCardButton";
import LearnButton from "./LearnButton";

const Card = () => {
  return (
    <div className="bg-black/50 flex flex-col mx-auto my-2 p-5 text-white w-full sm:w-[80%] md:w-[47%] rounded-xl shadow-2xl">
      <h1 className="text-xl mb-24">value of pie</h1>

      <div className="flex flex-col">
        <EditCardButton className="w-full" />
        <LearnButton className="w-full" />
      </div>
    </div>
  );
};

export default Card;
