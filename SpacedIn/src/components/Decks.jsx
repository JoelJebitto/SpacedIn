import Deck from "./Deck";

const lDeck = [
  {
    title: "English",
    description: "Consepts of Class 11",
    id: 0,
  },
  {
    title: "Math",
    description: "Consepts of Class 11",
    id: 1,
  },
  {
    title: "Science",
    description: "Consepts of Class 11",
    id: 2,
  },
  {
    title: "Chemistry",
    description: "Consepts of Class 11",
    id: 3,
  },
];

const Decks = () => {
  return (
    <div className="mt-7 p-5 bg-black/80 text-white rounded-xl">
      {/* <h1 className="text-4xl font-bold mb-3 underline">Decks:</h1> */}
      <div className=" flex flex-wrap">
        {lDeck.map((i) => (
          <Deck {...i} />
        ))}
      </div>
    </div>
  );
};

export default Decks;
