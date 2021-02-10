import React from "react";
import "./App.scss";

import Header from "./components/Header";
import Responsable from "./components/Responsable";

import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Etudiant from "./components/Etudiant";
import Entreprise from "./components/Entreprise";
import CollectData from "./components/CollectData";

function App() {
  return (
    <Router>
      <div className="App">
        <Header />
        <div className="container">
          <div className="wrapper">
            <div className="home">
              <Switch>
                <Route exact path="/" component={Home} />
                <Route exact path="/opportunities" component={Opportunities} />
                <Route exact path="/solutions" component={Solutions} />
                <Route exact path="/contact-us" component={Contact} />
                <Route exact path="/responsable" component={Responsable} />
                <Route exact path="/etudiant" component={Etudiant} />
                <Route exact path="/entreprise" component={Entreprise} />
                <Route exact path="/data" component={CollectData} />
              </Switch>
            </div>
          </div>
        </div>
      </div>
    </Router>
  );
}

function Opportunities() {
  return <p>Discover our numerous opportunities</p>;
}

function Solutions() {
  return <p>Solutions that help you.</p>;
}

function Contact() {
  return <p>Feel free to reach us.</p>;
}

function Home() {
  return (
    <div className="container">
      <div className="wrapper">
        <h5>
          The <b>ECS</b>, is a creative application, makes student easly find a
          stage, and in the oder side the businesses find also easly the
          trainees who meet their needs.
        </h5>
      </div>
    </div>
  );
}
export default App;
