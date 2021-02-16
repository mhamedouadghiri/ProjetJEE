import "./App.css";
import useToken from "./components/useToken";
import Login from "./Login";
import Header from "./components/Header";
import Sidebar from "./components/Sidebar";
import Entreprise from "./components/Entreprise";
import Student from "./components/Student";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

function App() {
  const { token, setToken } = useToken();
  if (!token) {
    return <Login setToken={setToken} />;
  }
  return (
    <div className="App">
      <Header setToken={setToken} />
      <div className="app_body">
        <Router>
          <Sidebar key={token.id} user={token} />
          <Switch>
            <Route exact path="/" component={Home} />
            <Route exact path="/entreprise" component={Entreprise} />
            <Route
              exact
              path="/student"
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
export default App;
