import "./App.css";
import Login from "./Login";
import useToken from "./useToken";
import Header from "./components/Header";
import Sidebar from "./components/Sidebar";
import InfoStudent from "./components/InfoStudent";
import Entreprise from "./components/Entrepris";

function App() {
  const { token, setToken } = useToken();
  if (!token) {
    return <Login setToken={setToken} />;
  }
  return (
    <div className="App">
      <Header setToken={setToken} />
      <div className="app_body">
        <Sidebar key={token.id} user={token} />
        <Entreprise />
        <InfoStudent />
      </div>
    </div>
  );
}
// function Home() {
//   return (
//     <div className="home">
//       <h1>Home page</h1>
//       <p className="home_parag">
//         This is your profile You can creat a Student account and you can visit
//         also the entreprises that exist in this application in order to manage
//         your school
//       </p>
//     </div>
//   );
// }
export default App;
