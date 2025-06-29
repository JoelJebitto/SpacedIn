import { useNavigate } from "react-router-dom";
import useAuthStore from "../store/useAuthStore";
const Btn = ({ children, onClick }) => {
  return (
    <button
      className="p-3 transition-all hover:cursor-pointer active:bg-gray-800 rounded-xl"
      onClick={onClick}
    >
      {children}
    </button>
  );
};

const Header = () => {
  const { logout, token } = useAuthStore();
  const navigate = useNavigate();
  const handleLogin = () => {
    navigate("/login");
  };
  const handleSignup = () => {
    navigate("/signup");
  };
  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return (
    <div className="bg-black/99 z-100 flex w-full px-5 py-3 text-white shadow-2xl lg:w-[60rem] lg:rounded-2xl mx-auto lg:mt-3 ">
      <h1
        onClick={() => {
          navigate("/dashboard");
        }}
        className="flex-1 cursor-pointer text-xl font-bold my-auto"
      >
        SpacedIn
      </h1>
      <div className="flex">
        {token ? (
          <Btn onClick={handleLogout}>Logout</Btn>
        ) : (
          <>
            <Btn onClick={handleLogin}>Login</Btn>
            <Btn onClick={handleSignup}>Signup</Btn>
          </>
        )}
      </div>
    </div>
  );
};

export default Header;
