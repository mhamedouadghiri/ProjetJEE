import { Avatar } from "@material-ui/core";
import React, { useState } from "react";
import "./Post.css";
import EmailIcon from "@material-ui/icons/Email";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";

function Post({ name, email, description, message }) {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  return (
    <>
      <div className="post">
        <div className="post_header">
          <Avatar />
          <div className="post_info">
            <h2>{name}</h2>
            <p>{email}</p>
          </div>
        </div>
        <div className="post_body">
          <p>{message}</p>
          <p className="description">{description}</p>
        </div>
        <div className="post_buttons">
          <div onClick={handleShow} className="inputOption">
            <EmailIcon style={{ color: "gray" }} />
            <h5>Postule</h5>
          </div>
        </div>
      </div>
      <Modal centered show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Postuler à cette offre</Modal.Title>
        </Modal.Header>
        <form>
          <Modal.Body>
            Lettre de Motivation :
            <div className="lettreMotivation">
              <textarea rows="4" cols="62" placeholder="Convince us"></textarea>
            </div>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
            <Button variant="primary" onClick={handleClose}>
              Save Changes
            </Button>
          </Modal.Footer>
        </form>
      </Modal>
    </>
  );
}

export default Post;
