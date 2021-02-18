import Button from "react-bootstrap/Button";
import React, { useState } from "react";
import AcountStudent from "./AcountStudent";
import "./Student.css";
import Modal from "react-bootstrap/Modal";
import Header from "react-bootstrap/ModalHeader";
import Title from "react-bootstrap/ModalTitle";
import Footer from "react-bootstrap/ModalFooter";
import Body from "react-bootstrap/ModalBody";
import { properties } from "../resources/properties";

function Student(props) {
  const [show, setShow] = useState(false);
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password1, setPassword1] = useState("");
  const [password2, setPassword2] = useState("");

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  let detailsStud = {
    email: email,
    password: password1,
    "user-type": "student",
    status: false,
    "first-name": firstName,
    "last-name": lastName,
    "school-id": props.token.id,
  };
  let formBody = [];
  for (let property in detailsStud) {
    let encodedKey = encodeURIComponent(property);
    let encodedValue = encodeURIComponent(detailsStud[property]);
    formBody.push(encodedKey + "=" + encodedValue);
  }
  formBody = formBody.join("&");

  const creerStudent = () => {
    if (
      email !== "" &&
      password1 === password2 &&
      firstName !== "" &&
      lastName !== ""
    ) {
      alert("test");
      console.log(detailsStud);
      fetch(`${properties.url}${properties.registerUser}`, {
        method: "post",

        headers: {
          Accept: "application/json",
          "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
        },
        body: formBody,
      })
        .then((response) => response.json())
        .then((data) => console.log("the data:", data));

      setPassword2("");
      setPassword1("");
      setFirstName("");
      setLastName("");
      setEmail("");

      handleClose();
    } else {
      alert("khassk t3mr");
      console.log(detailsStud);
    }
  };
  return (
    <div className="student">
      <div className="students">
        <AcountStudent name={"lotfi"} email={"ayoub@gmail.com"} />
        <AcountStudent name={"Sefian"} email={"Sefian@gmail.com"} />
        <AcountStudent name={"Oubaamrane"} email={"Ouba@gmail.com"} />
        <AcountStudent name={"Amine"} email={"amine@gmail.com"} />
        <AcountStudent name={"Mohammed"} email={"Medlotfi96@gmail.com"} />
        <AcountStudent name={"Doha"} email={"Doha@gmail.com"} />
        <AcountStudent name={"Ikram"} email={"ikram@gmail.com"} />
        <AcountStudent name={"Ikhlass"} email={"ikhlass@gmail.com"} />
        <AcountStudent name={"Amine"} email={"AmineJamai@gmail.com"} />
        <AcountStudent name={"lotfi"} email={"ayoub@gmail.com"} />
        <AcountStudent name={"lotfi"} email={"ayoub@gmail.com"} />
        <AcountStudent name={"lotfi"} email={"ayoub@gmail.com"} />
      </div>
      <div className="creatstudent">
        <Button onClick={handleShow} className="btn btn-warning">
          créer un Student
        </Button>
      </div>
      <Modal centered show={show} onHide={handleClose} animation={false}>
        <Header closeButton>
          <Title>Ajouter Un étudiant</Title>
        </Header>
        <form onSubmit={creerStudent} className="inscriptionStudent">
          <Body>
            <div className="firstPart">
              <div>
                <label>First Name :</label>
                <input
                  value={firstName}
                  type="text"
                  placeholder="FirstName"
                  onChange={(e) => {
                    setFirstName(e.target.value);
                  }}
                />
              </div>
              <div>
                <label>Last Name :</label>
                <input
                  value={lastName}
                  type="text"
                  placeholder="LastName"
                  onChange={(e) => {
                    setLastName(e.target.value);
                  }}
                />
              </div>
            </div>
            <div className="zone">
              <label>Email:</label>
              <input
                className="emailZone"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Email"
                type="email"
              />
            </div>
            <div className="firstPart">
              <div>
                <label>Password :</label>
                <input
                  value={password1}
                  type="password"
                  placeholder="Password"
                  onChange={(e) => {
                    setPassword1(e.target.value);
                  }}
                />
              </div>
              <div className="test">
                <label>Confirm Password :</label>
                <input
                  value={password2}
                  type="password"
                  placeholder="Password"
                  onChange={(e) => {
                    setPassword2(e.target.value);
                  }}
                />
              </div>
            </div>
          </Body>
          <Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
            <Button type="submit" variant="primary">
              Save Student
            </Button>
          </Footer>
        </form>
      </Modal>
    </div>
  );
}

export default Student;
