import "./App.css";
import CollectData from "./CollectData";
import Nav from "./Nav";
import Login from "./Login";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

function App() {
  return (
    <Router>
      <div className="App">
        <Nav />
        <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/collectdata" component={CollectData} />
          <Route path="/login" component={Login} />
        </Switch>
      </div>
    </Router>
  );
}
const Home = () => {
  return (
    <div>
      <h1>Home page</h1>
    </div>
  );
};

export default App;
