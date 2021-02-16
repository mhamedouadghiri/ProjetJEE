import { gsap } from "gsap";
export const dropInscription = () => {
  const tl = gsap.timeline();
  tl.fromTo(
    ".formulaireInscription",
    { y: "100%" },
    { y: "0%", duration: 0.4 }
  );
};

export const removeInscription = () => {
  const tl = gsap.timeline();
  tl.fromTo(
    ".formulaireInscription",
    { y: "0%" },
    { y: "-100%", duration: 0.4 }
  );
};
