import Header from "./Header";
import { Outlet } from "react-router-dom";

export default function Layout() {
  return (
    <>
      <Header />
      <main className="flex flex-col rounded-2xl pt-5 mb-10 max-w-4xl mx-2 md:mx-auto p-4 bg-black/85  mt-10">
        <Outlet />
      </main>
    </>
  );
}
