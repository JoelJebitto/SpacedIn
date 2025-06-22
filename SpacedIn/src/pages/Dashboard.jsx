import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import useAuthStore from "../store/useAuthStore";
import FullOverview from "../components/FullOverview";
import Decks from "../components/Decks";

function Dashboard() {
  const navigate = useNavigate();
  const { isTokenValid } = useAuthStore();
  useEffect(() => {
    !isTokenValid() && navigate("/");
  }, [navigate, isTokenValid]);
  return (
    <div className="flex flex-col w-screen lg:w-[55rem] m-5 lg:mx-auto">
      <FullOverview />
      <Decks />
    </div>
  );
}

export default Dashboard;
