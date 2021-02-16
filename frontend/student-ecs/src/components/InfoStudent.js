import Button from "react-bootstrap/Button";
import "bootstrap/dist/css/bootstrap.min.css";
import React from "react";
import "./InfoStudent.css";

function InfoStudent() {
  const recentItem = (topic) => (
    <div className="sidebar__recentItem">
      <span className="sidebar__hash">#</span>
      <p>{topic}</p>
    </div>
  );
  return (
    <div className="info">
      <div className="sidebar__bottom">
        <p>Skills</p>
        {recentItem("PHP")}
        {recentItem("JavaSript")}
        {recentItem("Java")}
        {recentItem("React")}
        {recentItem("Git")}
        <div className="ajoutInfo">
          <Button variant="light">Ajouter</Button>
        </div>
      </div>
      <div className="sidebar__bottom">
        <p>Education</p>
        {recentItem("ENSIAS")}
        {recentItem("LICENCE")}
        {recentItem("DEUG")}
        {recentItem("BAC")}

        <div className="ajoutInfo">
          <Button variant="light">Ajouter</Button>
        </div>
      </div>
    </div>
  );
}

export default InfoStudent;
