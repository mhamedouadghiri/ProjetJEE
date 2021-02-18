import React, { useState } from "react";
import "./Login.css";
import Header from "../components/Header";
import { properties } from "../resources/properties";

function Login({ setToken }) {
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

  const handleSubmit = (e) => {
    e.preventDefault();
    if (email !== "" && password !== "") {
      fetch(`${properties.url}${properties.checkUser}`, {
        method: "POST",
        //mode: "no-cors",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
        },
        body: formBodyAuth,
      })
        .then((response) => response.json())
        .then((data) => {
          setToken(data);
          console.log(data);
        });
    } else {
      alert("you have to right your email and your password please");
    }
  };

  return (
    <div className="main_login">
      <Header />
      <div className="login">
        <img
          src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcREX6wLF2TKTLUlKd0kJeVxB3lxclYa551e6g&usqp=CAU"
          alt=""
        />
        <form onSubmit={handleSubmit}>
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
          You forgot your password?{" "}
          <span className="login__register">Click here</span>
        </p>
      </div>
    </div>
  );
}

export default Login;
