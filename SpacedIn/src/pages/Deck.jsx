import Cards from "../components/Cards";
import DeckOverview from "../components/DeckOverview";
// import useCurrentDeckStore from "../store/useCurrentDeckStore";

const Deck = () => {
  const deck = {
    title: "hello",
    description: "hi",
  };
  return (
    <div className="flex flex-col w-screen lg:w-[55rem] m-5 lg:mx-auto">
      <div className="bg-black/80 p-5 flex flex-col rounded-xl shadow-2xl">
        <h1 className="text-white text-4xl font-bold">{deck.title}</h1>
        <p className="text-gray-100">{deck.description}</p>
      </div>
      <DeckOverview />
      <Cards />
    </div>
  );
};

export default Deck;
