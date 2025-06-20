import { useNavigate } from "react-router-dom";
const Btn = ({ children, onClick }) => {
  return (
    <button
      className="p-3 transition-all active:bg-gray-800 rounded-xl"
      onClick={onClick}
    >
      {children}
    </button>
  );
};

const Header = () => {
  const navigate = useNavigate();
  const handleLogin = () => {
    navigate("/login");
  };
  const handleSignup = () => {
    navigate("/signup");
  };
  const handleLogout = () => {
    navigate("/");
  };

  return (
    <div className="bg-gray-900 flex px-5 py-3 text-white lg:w-[60rem] lg:rounded-2xl mx-auto lg:mt-3 mb-3">
      <h1 className="flex-1 text-xl my-auto">SpacedIn</h1>
      <div className="flex">
        <Btn onClick={handleLogin}>Login</Btn>
        <Btn onClick={handleSignup}>Signup</Btn>
        <Btn onClick={handleLogout}>Logout</Btn>
      </div>
    </div>
  );
};

export default Header;
