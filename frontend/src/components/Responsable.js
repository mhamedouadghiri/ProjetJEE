import React, { useState } from "react";
import "./Responsable.css";
import Button from "react-bootstrap/Button";
import "bootstrap/dist/css/bootstrap.min.css";
import { dropInscription, removeInscription } from "./Animations";

function Responsable() {
  //for connection
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const postData = (e) => {
    e.preventDefault();
    let result = fetch(
      "https://webhook.site/989f9414-3148-4021-a820-08491aa47267",
      {
        method: "post",
        mode: "no-cors",
        headers: {
          Accept: "application/json",
          "Content-type": "application/json",
        },
        body: JSON.stringify({
          email: email,
          password: password,
        }),
      }
    );
    console.log(result);
  };
  //for inscription
  const [email1, setEmail1] = useState("");
  const [name, setName] = useState("");
  const [phone, setPhone] = useState("");
  const [password1, setPassword1] = useState("");
  const [password2, setPassword2] = useState("");

  const inscription = (e) => {
    e.preventDefault();
    if (
      password1 === password2 &&
      name !== "" &&
      email1 !== "" &&
      phone !== ""
    ) {
      alert("dakchi howa hadak");
      let result = fetch(
        "https://webhook.site/989f9414-3148-4021-a820-08491aa47267",
        {
          method: "post",
          mode: "no-cors",
          headers: {
            Accept: "application/json",
            "Content-type": "application/json",
          },
          body: JSON.stringify({
            email: email1,
            password: password1,
            name: name,
            "user-type": "internships_manager",
          }),
        }
      );
      console.log(result);
      removeInscription();
      setPhone("");
      setEmail1("");
      setName("");
      setPassword1("");
      setPassword2("");
    } else {
      alert("dakchi machi howa hadak bdl ");
    }
  };

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
          Not a member?{" "}
          <span className="login__register" onClick={dropInscription}>
            Register Now
          </span>
        </p>
      </div>

      <div className="formulaireInscription">
        <form className="userInscription" onSubmit={inscription}>
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
                value={email1}
                onChange={(e) => setEmail1(e.target.value)}
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
    </>
  );
}

export default Responsable;
