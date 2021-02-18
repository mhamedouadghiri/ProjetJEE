import React from "react";
import useToken from "../components/useToken";
import Login from "../student/Login";
import HeaderApp from "../components/HeaderApp";
import Sidebar from "./Sidebar";
import EntrepriseS from "./EntrepriseS";
import InfoStudent from "./InfoStudent";
import "./Etudiant.css";

function Etudiant() {
  const { token, setToken } = useToken();
  if (!token) {
    return <Login setToken={setToken} />;
  }
  return (
    <div className="App">
      <HeaderApp setToken={setToken} />
      <div className="app_body">
        <Sidebar user={token} />
        <EntrepriseS user={token} />
        <InfoStudent user={token} />
      </div>
    </div>
  );
}

export default Etudiant;
