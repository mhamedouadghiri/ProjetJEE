import { Avatar } from "@material-ui/core";
import Button from "react-bootstrap/Button";
import "bootstrap/dist/css/bootstrap.min.css";
import React, { useState } from "react";
import "./Sidebar.css";
import SchoolIcon from "@material-ui/icons/School";
import BusinessCenterIcon from "@material-ui/icons/BusinessCenter";
import image from "../images/Lotfi.jpeg";
import Modal from "react-bootstrap/Modal";

function Sidebar(props) {
  //pour les experiences
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  //pour les langues
  const [showL, setShowL] = useState(false);
  const handleCloseL = () => setShowL(false);
  const handleShowL = () => setShowL(true);

  const recentItem = (topic, icon) => (
    <div className="sidebar__recentItem">
      <span className="sidebar__hash">{icon}</span>
      <p>{topic}</p>
    </div>
  );

  return (
    <>
      <div className="sidebar">
        <div className="sidebar__top">
          <img src={image} alt="" />
          <Avatar className="sidebar__avatar" src={image} />
          <h2>Lotfi {props.user.name}</h2>
          <h4>{props.user.email}</h4>
        </div>
        <div className="sidebar__stats">
          <div className="sidebar__stat">
            <p>Nombre d'entreprise</p>
            <p className="sidebar__statNumber">2.543</p>
          </div>
          <div className="sidebar__stat">
            <p>Nombre d'étudiant</p>
            <p className="sidebar__statNumber">2.448</p>
          </div>
        </div>

        <div className="sidebar__bottom">
          <p>Experiences</p>

          {recentItem("ENSIAS", <BusinessCenterIcon />)}

          {recentItem("Stage 1ere Ann", <SchoolIcon />)}
          <div className="ajoutInfo">
            <Button onClick={handleShow} variant="light">
              Ajouter
            </Button>
          </div>
        </div>
        <div className="sidebar__bottom">
          <p>Langues</p>

          {recentItem("Français", <BusinessCenterIcon />)}

          {recentItem("Anglais", <SchoolIcon />)}
          {recentItem("Arabe", <SchoolIcon />)}
          <div className="ajoutInfo">
            <Button onClick={handleShowL} variant="light">
              Ajouter
            </Button>
          </div>
        </div>
      </div>
      {/* pour les experiences */}
      <Modal centered show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Nouvelle Expérience</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Im waiting for Mister Sefian to tell me which data must be sended to
          the backend.
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
      {/* pour les langues */}
      <Modal centered show={showL} onHide={handleCloseL}>
        <Modal.Header closeButton>
          <Modal.Title>Nouvelle Langues </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Im waiting for Mister Sefian to tell me which data must be sended to
          the backend.
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseL}>
            Close
          </Button>
          <Button variant="primary" onClick={handleCloseL}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default Sidebar;
