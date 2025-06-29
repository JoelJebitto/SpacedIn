import { useEffect } from "react";
import useDeckStore from "../store/useDeckStore";
import Deck from "./Deck";

const Decks = () => {
  const { decks, getMyDecks } = useDeckStore();

  useEffect(() => {
    getMyDecks();
  }, [getMyDecks]);
  return (
    <div className="mt-7 p-5 bg-black/80 text-white rounded-xl shadow-2xl">
      {/* <h1 className="text-4xl font-bold mb-3 underline">Decks:</h1> */}
      <div className=" flex flex-wrap">
        {decks.map((i) => (
          <Deck {...i} />
        ))}
      </div>
    </div>
  );
};

export default Decks;
