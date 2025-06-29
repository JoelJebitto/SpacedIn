import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Cards from "../components/Cards";
import DeckOverview from "../components/DeckOverview";
import useCardStore from "../store/useCardStore";

const Deck = () => {
  const { id } = useParams();
  const [deck, setDeck] = useState(null);
  const { cards, getCards } = useCardStore();

  useEffect(() => {
    const fetchDeck = async () => {
      const token = localStorage.getItem("token");
      const res = await fetch(`http://localhost:8080/api/v1/decks/${id}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      if (res.ok) {
        const data = await res.json();
        setDeck(data);
      }
    };
    fetchDeck();
    getCards(id);
  }, [id, getCards]);

  if (!deck) {
    return <div className="text-center text-white mt-10">Loading...</div>;
  }

  return (
    <div className="flex flex-col w-screen lg:w-[55rem] m-5 lg:mx-auto">
      <div className="bg-black/80 p-5 flex flex-col rounded-xl shadow-2xl">
        <h1 className="text-white text-4xl font-bold">{deck.title}</h1>
        <p className="text-gray-100">{deck.description}</p>
      </div>
      <DeckOverview deckId={id} deck={deck} />
      <Cards cards={cards} />
    </div>
  );
};

export default Deck;
