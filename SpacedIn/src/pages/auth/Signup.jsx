import { useState } from "react";
import { useNavigate } from "react-router-dom";
import useAuthStore from "../../store/useAuthStore";

function SignUp() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const setToken = useAuthStore((state) => state.setToken);
  const navigate = useNavigate();

  const handleSignUp = async (e) => {
    e.preventDefault();

    const res = await fetch("http://localhost:8080/api/v1/auth/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });

    const contentType = res.headers.get("content-type");

    console.log("helook");
    if (res.ok && contentType) {
      const data = await res.json();
      setToken(data.token);
      console.log("heloo");
      navigate("/dashboard");
    } else {
      const errorText = await res.text(); // fallback
      alert("Sign-up failed: " + errorText);
    }
  };

  return (
    <form
      onSubmit={handleSignUp}
      className="mx-auto bg-gray-300 flex flex-col rounded-2xl p-5 w-[90vw] md:w-[30rem] lg:w-[40rem] my-auto mt-[30vh]"
    >
      <input
        className="py-2 rounded-2xl px-5 my-1 bg-gray-200 outline-none"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        placeholder="Username"
        required
      />
      <input
        className="py-2 rounded-2xl px-5 my-1 bg-gray-200 outline-none"
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        placeholder="Password"
        required
      />
      <button type="submit" className="py-2 rounded-2xl px-5 my-1 bg-gray-400">
        SignUp
      </button>
    </form>
  );
}

export default SignUp;
