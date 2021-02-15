import React, { useState } from "react";
import "./Responsable.css";
import Button from "react-bootstrap/Button";
import "bootstrap/dist/css/bootstrap.min.css";
import { dropInscription, removeInscription } from "./Animations";
import Header from "./Header";

function Responsable() {
  //for connection
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [email1, setEmail1] = useState("");

  let detailsAuth = {
    email: email1,
    password: password,
    "user-type": "internships_manager",
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
        //mode: "no-cors",
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

  const [name, setName] = useState("");
  const [phone, setPhone] = useState("");
  const [password1, setPassword1] = useState("");
  const [password2, setPassword2] = useState("");

  let details = {
    email: email,
    password: password1,
    name: name,
    phone: phone,
    "user-type": "internships_manager",
  };

  let formBody = [];
  for (let property in details) {
    let encodedKey = encodeURIComponent(property);
    let encodedValue = encodeURIComponent(details[property]);
    formBody.push(encodedKey + "=" + encodedValue);
  }
  formBody = formBody.join("&");

  const inscription = (e) => {
    e.preventDefault();
    if (
      password1 === password2 &&
      name !== "" &&
      email !== "" &&
      phone !== ""
    ) {
      alert("dakchi howa hadak");
      fetch(
        "http://localhost:8080/ProjetJEE-1.0-SNAPSHOT/api/users/auth/register-user",
        {
          method: "post",
          //mode: "no-cors",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
          },
          body: formBody,
        }
      )
        .then((response) => response.json())
        .then((data) => console.log("the data:", data));

      removeInscription();
      setPhone("");
      setEmail("");
      setName("");
      setPassword1("");
      setPassword2("");
    } else {
      alert("dakchi machi howa hadak bdl ");
    }
  };

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
            value={email1}
            onChange={(e) => setEmail1(e.target.value)}
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
          Not a member?{" "}
          <span className="login__register" onClick={dropInscription}>
            Register Now
          </span>
        </p>
      </div>
      {/* inscription */}
      <div className="formulaireInscription">
        <form className="userInscription" onSubmit={inscription} method="post">
          <div className="firstPart">
            <div className="first">
              <h3>S'inscrire</h3>
              <p>C'est rapide est facile</p>
            </div>
            <h3 className="closeBtn" onClick={removeInscription}>
              X
            </h3>
          </div>
          <div className="separateur"></div>
          <div className="secondPart">
            <div className="zoneMail">
              <input
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Email"
                type="email"
              />
            </div>
            <div className="zoneNP">
              <input
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Name"
                type="text"
              />
              <input
                value={phone}
                onChange={(e) => setPhone(e.target.value)}
                placeholder="0x-xx-xx-xx"
                type="text"
              />
            </div>
            <div className="zonePassword">
              <div className="password1">
                <label>Password :</label>
                <input
                  value={password1}
                  onChange={(e) => setPassword1(e.target.value)}
                  placeholder="Password"
                  type="password"
                />
              </div>
              <div className="password2">
                <label>Confirm your Password :</label>
                <input
                  value={password2}
                  onChange={(e) => setPassword2(e.target.value)}
                  placeholder="Password"
                  type="password"
                />
              </div>
            </div>
          </div>
          <Button type="submit" className="btn btn-warning">
            Sing In
          </Button>
        </form>
      </div>
    </div>
  );
}

export default Responsable;
