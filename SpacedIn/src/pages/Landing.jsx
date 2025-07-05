import { useNavigate } from "react-router-dom";
import Header from "../components/Header";
import useAuth from "../store/useAuth";
import { useEffect } from "react";

function Landing() {
  const { token } = useAuth();
  const navigate = useNavigate();
  useEffect(() => {
    if (token) {
      navigate("/dashboard");
    }
  });

  return (
    <>
      <Header />
      <div className="mx-auto w-2/5 flex mt-56 flex-col">
        <h1 className=" text-6xl font-bold mb-10  p-2 mx-auto">SpacedIn</h1>
        <p>
          SpacedIN is your intelligent learning companion that boosts memory
          retention using proven spaced repetition techniques. Designed for
          students, professionals, and lifelong learners, it personalizes your
          study sessions with AI-driven insights, smart scheduling, and an
          intuitive interface—helping you master concepts faster and remember
          them longer.
        </p>
        <div className="gap-2 flex mx-auto mt-10">
          <button
            className="bg-blue-400 p-5 rounded-xl"
            onClick={() => navigate("/login")}
          >
            Sign In
          </button>
          <button
            className="bg-green-400 p-5 rounded-xl"
            onClick={() => {
              navigate("/register");
            }}
          >
            Sign Up
          </button>
        </div>
      </div>
    </>
  );
}

export default Landing;
