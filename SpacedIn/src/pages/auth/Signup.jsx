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

    const res = await fetch("http://localhost:8080/auth/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });

    const contentType = res.headers.get("content-type");

    if (res.ok && contentType && contentType.includes("application/json")) {
      const data = await res.json();
      setToken(data.token);
      navigate("/dashboard");
    } else {
      const errorText = await res.text(); // fallback
      alert("Sign-up failed: " + errorText);
    }
  };

  return (
    <form onSubmit={handleSignUp}>
      <input
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        placeholder="Username"
        required
      />
      <input
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        placeholder="Password"
        required
      />
      <button type="submit">Sign Up</button>
    </form>
  );
}

export default SignUp;
