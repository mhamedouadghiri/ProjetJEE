import React from "react";
import "./Nav.css";
import { Link } from "react-router-dom";

function Nav() {
  return (
    <nav>
      <img
        src="https://lh3.googleusercontent.com/proxy/LSgjG7SEpjFike5a7AWALTkL3LBMd6px2f4Iy1JlKQdlJNLWU_FTr3b2eDiVbKWn0V5y2IfqE8TEKVxlYxSYZWe8dtEdFyw_-GREFYWnqaNWIi6asgLYM-1wZ7SemHkuSUAUPxz7bb2ZSAPK4urdnm41b9NICbwbgzzjO1x7U0xgUK3xe10gTA"
        alt=""
      />
      <ul>
        <Link className="link" to="/login">
          <li>Login</li>
        </Link>
        <Link className="link" to="/collectdata">
          <li>CollectData</li>
        </Link>
      </ul>
    </nav>
  );
}

export default Nav;
