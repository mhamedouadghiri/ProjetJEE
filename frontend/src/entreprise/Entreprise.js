import React from "react";
import useToken from "../components/useToken";
import Login from "./Login";
import HeaderApp from "../components/HeaderApp";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Sidebar from "./Sidebar";
import Offre from "./Offre";
import Candidature from "./Candidature";

function Entreprise() {
  const { token, setToken } = useToken();
  if (!token) {
    return <Login setToken={setToken} />;
  }
  return (
    <div className="App">
      <HeaderApp setToken={setToken} />
      <div className="app_body">
        <Router>
          <Sidebar user={token} />
          <Switch>
            <Route
              exact
              path="/entreprise"
              render={(props) => <Offre token={token} />}
            />
            <Route
              exact
              path="/entreprise/candidature"
              component={Candidature}
            />
          </Switch>
        </Router>
      </div>
    </div>
  );
}

export default Entreprise;
