import { useState } from "react";
import { useNavigate } from "react-router-dom";
import useAuthStore from "../../store/useAuthStore";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const setToken = useAuthStore((state) => state.setToken);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    const res = await fetch("http://localhost:8080/api/v1/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });

    if (res.ok) {
      const data = await res.json();
      setToken(data.token);
      navigate("/dashboard");
    } else {
      alert("Invalid credentials");
    }
  };

  return (
    <form
      onSubmit={handleLogin}
      className="mx-auto bg-black/90 text-white shadow-2xl flex flex-col rounded-2xl px-5 py-7 w-[90vw] md:w-[30rem] lg:w-[35rem] my-auto mt-[30vh]"
    >
      <h1 className="text-5xl my-3 font-bold ">Login</h1>
      <input
        className="py-3 rounded-2xl px-5 my-2 bg-black/90 outline-none"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        placeholder="Username"
        required
      />
      <input
        className="py-3 rounded-2xl px-5 my-3 bg-black/90 outline-none"
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        placeholder="Password"
        required
      />
      <button
        type="submit"
        className="py-2 rounded-2xl px-5 mb-1 mt-5 shadow-2xl bg-gray-300/80 text-black hover:bg-gray-300/90 active:bg-gray-300"
      >
        Login
      </button>
    </form>
  );
}
export default Login;
