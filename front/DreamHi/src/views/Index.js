/*!

=========================================================
* Paper Kit React - v1.3.1
=========================================================

* Product Page: https://www.creative-tim.com/product/paper-kit-react

* Copyright 2022 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/paper-kit-react/blob/main/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React from "react";

// reactstrap components

// core components
import IndexNavbar from "components/commons/Navbars/IndexNavbar.js";
import IndexHeader from "components/commons/Headers/IndexHeader.js";
import DemoFooter from "components/commons/Footers/DemoFooter.js";

// index sections
import SectionButtons from "components/Main/index-sections/SectionButtons.js";
import SectionNavbars from "components/Main/index-sections/SectionNavbars.js";
import SectionNavigation from "components/Main/index-sections/SectionNavigation.js";
import SectionProgress from "components/Main/index-sections/SectionProgress.js";
import SectionNotifications from "components/Main/index-sections/SectionNotifications.js";
import SectionTypography from "components/Main/index-sections/SectionTypography.js";
import SectionJavaScript from "components/Main/index-sections/SectionJavaScript.js";
import SectionCarousel from "components/Main/index-sections/SectionCarousel.js";
import SectionNucleoIcons from "components/Main/index-sections/SectionNucleoIcons.js";
import SectionDark from "components/Main/index-sections/SectionDark.js";
import SectionLogin from "components/Main/index-sections/SectionLogin.js";
import SectionExamples from "components/Main/index-sections/SectionExamples.js";
import SectionDownload from "components/Main/index-sections/SectionDownload.js";

function Index() {
  document.documentElement.classList.remove("nav-open");
  React.useEffect(() => {
    document.body.classList.add("index");
    return function cleanup() {
      document.body.classList.remove("index");
    };
  });
  return (
    <>
      <IndexNavbar />
      <SectionCarousel />
      <IndexHeader />
      <div className="main">
        <SectionButtons />
        <SectionNavbars />
        <SectionNavigation />
        <SectionProgress />
        <SectionNotifications />
        <SectionTypography />
        <SectionJavaScript />

        <SectionNucleoIcons />
        <SectionDark />
        <SectionLogin />
        <SectionExamples />
        <SectionDownload />
        <DemoFooter />
      </div>
    </>
  );
}

export default Index;
