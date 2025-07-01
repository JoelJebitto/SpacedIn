import { useNavigate } from "react-router-dom";
import useAuth from "../store/useAuth";
const Btn = ({ children, onClick }) => (
  <button
    className="px-3 py-2 rounded-md hover:bg-gray-700 active:bg-gray-600 text-sm font-medium text-gray-100"
    onClick={onClick}
  >
    {children}
  </button>
);

const Header = () => {
  const { logout, token } = useAuth();
  const navigate = useNavigate();
  const handleLogin = () => {
    navigate("/");
  };
  const handleSignup = () => {
    navigate("/register");
  };
  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return (
    <div className="bg-gray-800 shadow-sm flex w-full justify-between px-5 py-3 sticky top-0 z-50 text-gray-100">
      <h1
        className="flex-1 text-xl font-bold my-auto"
        onClick={() => navigate("/dashboard")}
      >
        SpacedIn
      </h1>
      <div className="flex gap-2">
        {token ? (
          <>
            <Btn onClick={() => navigate('/aidemo')}>AI Demo</Btn>
            <Btn onClick={handleLogout}>Logout</Btn>
          </>
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
