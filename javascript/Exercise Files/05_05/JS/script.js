console.log(document.querySelector(".cta a").outerHTML);

const CTAELEMENT = document.querySelector(".cta a");

console.log( CTAELEMENT.attributes );


if (CTAELEMENT.hasAttribute("target")) {
  console.log(CTAELEMENT.getAttribute("target"));
} else {
  //console.log("Hello Niko");
  CTAELEMENT.setAttribute("target","_blank");
}
