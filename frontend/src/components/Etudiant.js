import React, { useState } from "react";
import "./Responsable.css";

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

  const postData = (e) => {
    e.preventDefault();
    fetch(
      "http://localhost:8080/ProjetJEE-1.0-SNAPSHOT/api/users/auth/check-user",
      {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
        },
        body: formBodyAuth,
      }
    )
      .then((response) => response.json())
      .then((data) => console.log(data));
  };
  //for inscription

  return (
    <>
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
    </>
  );
}

export default Etudiant;
