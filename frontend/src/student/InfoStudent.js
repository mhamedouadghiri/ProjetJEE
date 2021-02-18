import Button from "react-bootstrap/Button";
import "bootstrap/dist/css/bootstrap.min.css";
import React, { useEffect, useState } from "react";
import "./InfoStudent.css";
import Modal from "react-bootstrap/Modal";
import { properties } from "../resources/properties";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

function InfoStudent({ user }) {
  let [educations, setEducations] = useState(null);
  const [refresh, setRefresh] = useState(false);
  useEffect(() => {
    if (!refresh) {
      fetch(`${properties.url}${properties.StudentEducation}${user.id}`)
        .then((res) => res.json())
        .then((data) => {
          setEducations(data);
          setRefresh(true);
          console.log(data);
        });
    }
  }, [refresh]);

  const recentItem = (topic) => (
    <div className="sidebar__recentItem">
      <span className="sidebar__hash">#</span>
      <p>{topic}</p>
    </div>
  );
  //pour les skills
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  //pour les educations
  const [showL, setShowL] = useState(false);
  const handleCloseL = () => setShowL(false);
  const handleShowL = () => setShowL(true);

  const [name, setName] = useState("");
  const [level, setLevel] = useState("");
  const [sdate, setSdate] = useState(null);
  const [edate, setEdate] = useState(null);
  let detailsEduc = {
    name: name,
    level: level,
    "start-date": sdate,
    "end-date": edate,
    "student-id": user.id,
  };
  let formBodyEduc = [];
  for (let property in detailsEduc) {
    let encodedKey = encodeURIComponent(property);
    let encodedValue = encodeURIComponent(detailsEduc[property]);
    formBodyEduc.push(encodedKey + "=" + encodedValue);
  }
  formBodyEduc = formBodyEduc.join("&");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (name !== "" && level !== "") {
      fetch(`${properties.url}${properties.educations}`, {
        method: "POST",
        //mode: "no-cors",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
        },
        body: formBodyEduc,
      })
        .then((response) => response.json())
        .then((data) => {
          console.log(data);
          setRefresh(false);
        });
      setName("");
      setLevel("");
      setSdate(null);
      setEdate(null);
      handleCloseL();
    } else {
      alert("you have to right your email and your password please");
    }
    console.log(detailsEduc);
  };
  return (
    <>
      <div className="infoTest">
        <div className="sidebar__bottom">
          <p>Skills</p>
          {recentItem("PHP")}
          {recentItem("JavaSript")}
          {recentItem("Java")}
          {recentItem("React")}
          {recentItem("Git")}
          <div className="ajoutInfo">
            <Button onClick={handleShow} variant="light">
              Ajouter
            </Button>
          </div>
        </div>
        <div className="sidebar__bottom">
          <p>Education</p>
          {educations
            ? educations.map((education) => recentItem(education.name))
            : "Update your profile.."}

          <div className="ajoutInfo">
            <Button onClick={handleShowL} variant="light">
              Ajouter
            </Button>
          </div>
        </div>
      </div>
      {/* pour les skills */}
      <Modal centered show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>New Skill</Modal.Title>
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
      {/* pour les educations */}
      <Modal centered show={showL} onHide={handleCloseL}>
        <Modal.Header closeButton>
          <Modal.Title>New Education</Modal.Title>
        </Modal.Header>
        <form onSubmit={handleSubmit}>
          <Modal.Body>
            <div className="firstPart">
              <div>
                <label>Education Name :</label>
                <input
                  value={name}
                  type="text"
                  placeholder="Name"
                  onChange={(e) => {
                    setName(e.target.value);
                  }}
                />
              </div>
              <div>
                <label>Education level :</label>
                <input
                  value={level}
                  type="text"
                  placeholder="Level"
                  onChange={(e) => {
                    setLevel(e.target.value);
                  }}
                />
              </div>
            </div>
            <div className="datepart">
              <div>
                <label>start date: </label>
                <DatePicker
                  selected={sdate}
                  onChange={(date) => setSdate(date)}
                  dateFormat="yyy-MM-dd"
                  maxDate={new Date()}
                  isClearable
                  showYearDropdown
                />
              </div>
              <div>
                <label>start date: </label>
                <DatePicker
                  selected={edate}
                  onChange={(date) => setEdate(date)}
                  dateFormat="yyy-MM-dd"
                  maxDate={new Date()}
                  isClearable
                  showYearDropdown
                />
              </div>
            </div>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseL}>
              Close
            </Button>
            <Button type="submit" variant="primary">
              Save Changes
            </Button>
          </Modal.Footer>
        </form>
      </Modal>
    </>
  );
}

export default InfoStudent;
