import { Route, Routes } from "react-router-dom";
import NoMatch from "./NoMatch";
import Board from "../pages/Board";
import Layout from "./Layout";
import LandingPage from "./LandingPage";
import Card from "./Card";
import SignUp from "./Auth/SignUp";
import Login from "./Auth/Login";
import Logout from "./Auth/Logout";

export default function RoutesSetup() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<LandingPage />} />
        <Route path="/board" element={<Board />} />
        <Route path="*" element={<NoMatch />} />
        <Route path="/board/card/:cardid" element={<Card />} />
      </Route>
      <Route path="/signup" element={<SignUp />} />
      <Route path="/Login" element={<Login />} />
      <Route path="/Logout" element={<Logout />} />
      <Route path="*" element={<NoMatch />} />
    </Routes>
  )
}
