import LearnButton from "./LearnButton";

export default function Deck({ title, description, id, createdOn }) {
  const dateObject = new Date(createdOn);

  const options = {
    year: "numeric",
    month: "long",
    day: "numeric",
    weekday: "long",
  };
  const formattedDate = dateObject.toLocaleDateString(undefined, options);
  return (
    <div
      className="bg-black/50 flex flex-col mx-auto my-2 p-5 text-white w-full md:w-[47%] rounded-xl shadow-2xl"
      key={id}
    >
      <h1 className="text-3xl font-bold">{title}</h1>
      <p className="text-gray-300 text-md">{formattedDate}</p>
      <hr class="border-2 border-gray-800 my-2 " />
      <p className="text-gray-100 flex-1 text-lg">{description}</p>

      <LearnButton className="mt-20" />
    </div>
  );
}
