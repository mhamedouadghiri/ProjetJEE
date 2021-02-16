import React, { useState } from "react";

import "./Responsable.css";
import { useHistory } from "react-router-dom";
import Header from "./Header";

function Etudiant() {
  //for connection
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  let detailsAuth = {
    email: email,
    password: password,
    "user-type": "student",
  };

  let formBodyAuth = [];
  for (let property in detailsAuth) {
    let encodedKey = encodeURIComponent(property);
    let encodedValue = encodeURIComponent(detailsAuth[property]);
    formBodyAuth.push(encodedKey + "=" + encodedValue);
  }
  formBodyAuth = formBodyAuth.join("&");
  //c'est la méthode qui permet d'échanger data avec le backend
  // const postData = (e) => {
  //   e.preventDefault();
  //   fetch(
  //     "http://localhost:8080/ProjetJEE-1.0-SNAPSHOT/api/users/auth/check-user",
  //     {
  //       method: "POST",
  //       headers: {
  //         Accept: "application/json",
  //         "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
  //       },
  //       body: formBodyAuth,
  //     }
  //   )
  //     .then((response) => response.json())
  //     .then((data) => console.log(data));
  // };
  //c'est la méthode qui permet de changer la direction en fonction de la réponse du backend
  // const history = useHistory();
  // const postData = (e) => {
  //   e.preventDefault();
  //   if (email !== "" && password !== "") {
  //     history.push("/test");
  //     sessionStorage.setItem("mydata", JSON.stringify(detailsAuth));
  //   } else {
  //     alert("test");
  //   }
  // };
  const history = useHistory();
  const postData = (e) => {
    e.preventDefault();
    if (email !== "" && password !== "") {
      sessionStorage.setItem("mydata", JSON.stringify(detailsAuth));
      history.push("/test");
    } else {
      alert("test");
    }
  };

  //for inscription

  return (
    <div className="App">
      <Header />
      <div className="login">
        <img
          src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcREX6wLF2TKTLUlKd0kJeVxB3lxclYa551e6g&usqp=CAU"
          alt=""
        />
        <form onSubmit={postData}>
          <input
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Email"
            type="email"
          />
          <input
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Password"
            type="password"
          />

          <button type="submit">Sing In</button>
        </form>
        <p>
          Forget your password?{" "}
          <span className="login__register">Get Your Account</span>
        </p>
      </div>
    </div>
  );
}

export default Etudiant;
