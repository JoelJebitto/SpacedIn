import { useEffect } from "react";
import useDeckStore from "../store/useDeckStore";
import Deck from "./Deck";
import useDeckProgressStore from "../store/useDeckProgressStore";

const Decks = () => {
  const { decks, getMyDecks } = useDeckStore();
  const { progressMap, fetchProgress } = useDeckProgressStore();

  useEffect(() => {
    getMyDecks();
  }, [getMyDecks]);

  useEffect(() => {
    decks.forEach((d) => fetchProgress(d.id));
  }, [decks, fetchProgress]);
  return (
    <div className="mt-7 p-5 bg-black/80 text-white rounded-xl shadow-2xl">
      {/* <h1 className="text-4xl font-bold mb-3 underline">Decks:</h1> */}
      <div className=" flex flex-wrap">
        {decks.map((i) => (
          <Deck key={i.id} progress={progressMap[i.id] ?? 0} {...i} />
        ))}
      </div>
    </div>
  );
};

export default Decks;
