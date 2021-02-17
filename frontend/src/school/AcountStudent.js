import { Avatar } from "@material-ui/core";
import React from "react";
import "./AcountStudent.css";

function AcountStudent({ name, email }) {
  return (
    <div className="accountstudent">
      <div className="post_header">
        <Avatar />
        <div className="post_info">
          <h2>{name}</h2>
          <p>{email}</p>
        </div>
      </div>
      <div className="post_option">{name}</div>
      <div className="post_option">GL</div>
      <div className="post_option">2ème année</div>
    </div>
  );
}

export default AcountStudent;
