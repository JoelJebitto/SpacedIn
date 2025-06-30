import Header from "./Header";
import { Outlet } from "react-router-dom";

export default function Layout() {
  return (
    <>
      <Header />
      <main className="flex flex-col pt-16 w-screen max-w-4xl mx-auto p-4">
        <Outlet />
      </main>
    </>
  );
}
