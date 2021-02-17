import React from "react";
import useToken from "../components/useToken";
import Login from "../student/Login";

function Entreprise() {
  const { token, setToken } = useToken();
  if (!token) {
    return <Login setToken={setToken} />;
  }
  return <div className="App">Dakchi howa hadak</div>;
}

export default Entreprise;
