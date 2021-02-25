import React, {useState} from "react";
import "./Login.css";
import Button from "react-bootstrap/Button";
import "bootstrap/dist/css/bootstrap.min.css";
import {dropInscription, removeInscription} from "./components/Animations";
import Header from "./components/Header";
import {properties} from "./resources/properties";
import {Form, Image} from "react-bootstrap";

export default function Login({setToken, userType}) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  let detailsAuth = {
    email: email,
    password: password,
    "user-type": userType,
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
    fetch(`${properties.url}${properties.checkUser}`, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
      },
      body: formBodyAuth,
    }).then((response) => {
      if (response.status === 200) {
        response.json()
          .then((data) => {
            setToken(data);
            console.log(data);
          })
      } else {
        alert("Incorrect username or password.");
      }
    });
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
    "user-type": userType,
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
    if (password1 === password2) {
      fetch(`${properties.url}${properties.registerUser}`, {
        method: "post",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
        },
        body: formBody,
      }).then((response) => {
        if (response.status === 200) {
          response.json()
            .then((data) => {
              console.log("the data:", data)
            });
          removeInscription();
          setPhone("");
          setEmail("");
          setName("");
        } else {
          alert("An error has occurred.")
        }
      });
      setPassword1("");
      setPassword2("");
    } else {
      alert("Passwords don't match.");
    }
  };

  return (
    <div className="main_login">
      <Header/>
      <div className={"d-flex align-items-center flex-column"}>
        <Image src="logo.png" alt="ecs logo"/>
        <h1 className={"display-4 text-muted"}>Sign in as <b>{userType}</b></h1>
        <br/>
        <Form onSubmit={handleSubmit}>
          <Form.Control
            required
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Email"
            type="email"
          />
          <Form.Control
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Password"
            type="password"
          />
          <Button variant={"primary"} type="submit">Sign In</Button>
        </Form>
        {/*a student cannot create create an account on their own... the school takes care of this operation*/}
        {
          userType !== "student"
            ?
            <p>
              New to ECS?{" "}
              <span className="login__register" onClick={dropInscription}>
                Create an account.
              </span>
            </p>
            :
            <div/>
        }
      </div>
      {/* inscription */}
      <div className="formulaireInscription">
        <Form className="userInscription" onSubmit={inscription} method="post">
          <div className="firstPart">
            <div className="first">
              <h3>Create your account</h3>
              <p>It takes less than 30 seconds</p>
            </div>
            <h3 className="closeBtn" onClick={removeInscription}>X</h3>
          </div>
          <div className="separateur"/>
          <div className="secondPart">
            <div className="zoneMail">
              <Form.Control
                required
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Email"
                type="email"
              />
            </div>
            <div className="zoneNP">
              <Form.Control
                required
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Name"
                type="text"
              />
              <Form.Control
                value={phone}
                onChange={(e) => setPhone(e.target.value)}
                placeholder="0x-xx-xx-xx"
                type="text"
              />
            </div>
            <div className="zonePassword">
              <div className="password1">
                <Form.Label>Password :</Form.Label>
                <Form.Control
                  required
                  value={password1}
                  onChange={(e) => setPassword1(e.target.value)}
                  placeholder="Password"
                  type="password"
                />
              </div>
              <div className="password2">
                <Form.Label>Confirm your Password :</Form.Label>
                <Form.Control
                  required
                  value={password2}
                  onChange={(e) => setPassword2(e.target.value)}
                  placeholder="Password"
                  type="password"
                />
              </div>
            </div>
          </div>
          <Button type="submit" className="btn btn-warning">Sign In</Button>
        </Form>
      </div>
    </div>
  );
}
