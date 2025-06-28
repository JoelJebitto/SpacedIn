import Header from "./Header";
import { Outlet } from "react-router-dom";

export default function Layout() {
  return (
    <>
      <div className="bg-white/90 absolute z-50 flex w-full left-1/2 transform -translate-x-1/2 ">
        <Header />
      </div>
      <main className="flex mt-25">
        <Outlet />
      </main>
    </>
  );
}
