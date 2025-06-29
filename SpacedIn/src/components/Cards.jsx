import Card from "./Card";

const Cards = ({ cards = [] }) => {
  return (
    <div className="bg-black/80 p-5 flex flex-wrap rounded-xl shadow-2xl mt-10">
      {cards.map((c) => (
        <Card key={c.id} {...c} />
      ))}
    </div>
  );
};

export default Cards;
