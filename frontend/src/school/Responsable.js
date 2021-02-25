import React from "react";
import "./Responsable.css";
import useToken from "../components/useToken";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Login from "./Login";
import HeaderApp from "../components/HeaderApp";
import Sidebar from "./Sidebar";
import Offre from "./Offre";
import Student from "./Student";
import Entreprise from "./Entreprise";

function Responsable() {
  const { token, setToken } = useToken();
  if (!token) {
    return <Login setToken={setToken} />;
  }
  return (
    <div className="App">
      <HeaderApp setToken={setToken} />
      <div className="app_body">
        <Router>
          <Sidebar key={token.id} user={token} />
          <Switch>
            <Route exact path="/responsable/" component={Home} />
            <Route
              exact
              path="/responsable/entreprise"
              render={(props) => <Entreprise user={token} />}
            />
            <Route
              exact
              path="/responsable/entreprise/offre"
              component={Offre}
            />
            <Route
              exact
              path="/responsable/student"
              render={(props) => <Student key={token.id} token={token} />}
            />
          </Switch>
        </Router>
      </div>
    </div>
  );
}

function Home() {
  return (
    <div className="home">
      <h1>Home page</h1>
      <p className="home_parag">
        This is your profile You can creat a Student account and you can visit
        also the entreprises that exist in this application in order to manage
        your school
      </p>
    </div>
  );
}
export default Responsable;
