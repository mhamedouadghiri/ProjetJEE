import React, { useRef, useEffect } from "react";
import { gsap } from "gsap";
import { Link } from "react-router-dom";

import {
  staggerText,
  staggerReveal,
  fadeInUp,
  handleHover,
  handleHoverExit,
  handleCityReturn,
  handleCity,
  staggerRevealClose,
} from "./Animations";

import Entreprise from "../images/Entreprise1.webp";
import Student from "../images/Student.webp";
import Responsible from "../images/responsible.webp";

const comptes = [
  { name: "Entreprise", image: Entreprise, component: "/entreprise" },
  { name: "Student", image: Student, component: "/etudiant" },
  { name: "Responsable", image: Responsible, component: "/responsable" },
];

const Hamburger = ({ state }) => {
  // Create varibles of our dom nodes
  let menuLayer = useRef(null);
  let reveal1 = useRef(null);
  let reveal2 = useRef(null);
  let cityBackground = useRef(null);
  let line1 = useRef(null);
  let line2 = useRef(null);
  let line3 = useRef(null);
  let info = useRef(null);

  useEffect(() => {
    // If the menu is open and we click the menu button to close it.
    if (state.clicked === false) {
      // If menu is closed and we want to open it.

      staggerRevealClose(reveal2, reveal1);
      // Set menu to display none
      gsap.to(menuLayer, { duration: 1, css: { display: "none" } });
    } else if (
      state.clicked === true ||
      (state.clicked === true && state.initial === null)
    ) {
      // Set menu to display block
      gsap.to(menuLayer, { duration: 0, css: { display: "block" } });
      //Allow menu to have height of 100%
      gsap.to([reveal1, reveal2], {
        duration: 0,
        opacity: 1,
        height: "100%",
      });
      staggerReveal(reveal1, reveal2);
      fadeInUp(info);
      staggerText(line1, line2, line3);
    }
  }, [state]);

  return (
    <div ref={(el) => (menuLayer = el)} className="hamburger-menu">
      <div
        ref={(el) => (reveal1 = el)}
        className="menu-secondary-background-color"
      ></div>
      <div ref={(el) => (reveal2 = el)} className="menu-layer">
        <div
          ref={(el) => (cityBackground = el)}
          className="menu-compte-background"
        ></div>
        <div className="container">
          <div className="wrapper">
            <div className="menu-links">
              <nav>
                <ul>
                  <li>
                    <Link
                      onMouseEnter={(e) => handleHover(e)}
                      onMouseOut={(e) => handleHoverExit(e)}
                      ref={(el) => (line1 = el)}
                      to="/opportunities"
                    >
                      Opportunities
                    </Link>
                  </li>
                  <li>
                    <Link
                      onMouseEnter={(e) => handleHover(e)}
                      onMouseOut={(e) => handleHoverExit(e)}
                      ref={(el) => (line2 = el)}
                      to="/solutions"
                    >
                      Solutions
                    </Link>
                  </li>
                  <li>
                    <Link
                      onMouseEnter={(e) => handleHover(e)}
                      onMouseOut={(e) => handleHoverExit(e)}
                      ref={(el) => (line3 = el)}
                      to="/contact-us"
                    >
                      Contact us
                    </Link>
                  </li>
                </ul>
              </nav>
              <div ref={(el) => (info = el)} className="info">
                <h3>Our ECS Application</h3>
                <p>
                  The ECS application givs you the opportunities to find a PFE
                  stage easly and if you are a company you can shose the best
                  student in the year .
                </p>
              </div>
              <div className="locations">
                Login as:
                {/* Returning the list of comptes */}
                {comptes.map((el) => (
                  <Link
                    className="comptes"
                    key={el.name}
                    onMouseEnter={() => handleCity(el.image, cityBackground)}
                    onMouseOut={() => handleCityReturn(cityBackground)}
                    to={el.component}
                  >
                    {el.name}
                  </Link>
                ))}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Hamburger;
