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
    const res = await fetch("http://localhost:8080/auth/login", {
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
        Login
      </button>
    </form>
  );
}
export default Login;
