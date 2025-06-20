import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

function Dashboard() {
  const navigate = useNavigate();
  useEffect(() => {
    const token = localStorage.getItem("token");
    token == null && navigate("/");
  }, [navigate]);
  return <div>DashBoard</div>;
}

export default Dashboard;
