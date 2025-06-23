import LearnButton from "./LearnButton";

export default function Deck({ title, description, id }) {
  return (
    <div
      className="bg-black/50 flex flex-col mx-auto my-2 p-5 text-white w-full md:w-[47%] rounded-xl shadow-2xl"
      key={id}
    >
      <h1 className="text-3xl font-bold">{title}</h1>
      <p className="text-gray-100">{description}</p>

      <LearnButton className="mt-20" />
    </div>
  );
}
