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
// nodejs library that concatenates strings
import classnames from "classnames";
// reactstrap components
import {
  Button,
  Collapse,
  NavbarBrand,
  Navbar,
  NavItem,
  NavLink,
  Nav,
  Container,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownItem,
  DropdownMenu
} from "reactstrap";

import { AiOutlineBell } from 'react-icons/ai'

function IndexNavbar() {
  const [navbarColor, setNavbarColor] = React.useState("navbar-transparent");
  const [navbarCollapse, setNavbarCollapse] = React.useState(false);

  const toggleNavbarCollapse = () => {
    setNavbarCollapse(!navbarCollapse);
    document.documentElement.classList.toggle("nav-open");
  };

  React.useEffect(() => {
    const updateNavbarColor = () => {
      if (
          document.documentElement.scrollTop > 299 ||
          document.body.scrollTop > 299
      ) {
        setNavbarColor("");
      } else if (
          document.documentElement.scrollTop < 300 ||
          document.body.scrollTop < 300
      ) {
        setNavbarColor("navbar-transparent");
      }
    };

    window.addEventListener("scroll", updateNavbarColor);

    return function cleanup() {
      window.removeEventListener("scroll", updateNavbarColor);
    };
  });
  return (
      <Navbar className={classnames("fixed-top", navbarColor)} expand="lg">
        <Container>
          <div className="navbar-translate">
            <NavbarBrand
                data-placement="bottom"
                href="/index"
                target="_blank"
                title="Coded by Creative Tim"
            >
              <img src={require("assets/img/dreamhigh-logo.png")} alt="" />
            </NavbarBrand>

          </div>
          <UncontrolledDropdown>
            <DropdownToggle
                caret
                color="default"
                data-toggle="dropdown"
                nav
            >
              <div className="notification d-none d-lg-block d-xl-block" />
              <AiOutlineBell size="30" color="#7EA6F4" />
              <p className="d-lg-none">Notifications</p>
            </DropdownToggle>
            <DropdownMenu className="dropdown-navbar" right tag="ul">
              <NavLink tag="li">
                <DropdownItem className="nav-item">
                  공고 진행 중
                </DropdownItem>
              </NavLink>
              <NavLink tag="li">
                <DropdownItem className="nav-item">
                  채용 완료
                </DropdownItem>
              </NavLink>
            </DropdownMenu>
          </UncontrolledDropdown>
          <UncontrolledDropdown>
            <DropdownToggle
                caret
                color="default"
                nav
                onClick={(e) => e.preventDefault()}
            >
              <div className="photo">
                <img alt="..." src={require("assets/img/logo.png")} />
              </div>
              <b className="caret d-none d-lg-block d-xl-block" />
              <p className="d-lg-none">Log out</p>
            </DropdownToggle>
            <DropdownMenu className="dropdown-navbar" right tag="ul">
              <NavLink tag="li">
                <DropdownItem className="nav-item">내 이력서</DropdownItem>
              </NavLink>
              <NavLink tag="li">
                <DropdownItem className="nav-item">내 제작사</DropdownItem>
              </NavLink>
              <NavLink tag="li">
                <DropdownItem className="nav-item">모아보기</DropdownItem>
              </NavLink>
              <DropdownItem divider tag="li" />
              <NavLink tag="li">
                <DropdownItem className="nav-item">로그아웃</DropdownItem>
              </NavLink>
            </DropdownMenu>
          </UncontrolledDropdown>
        </Container>
      </Navbar>
  );
}

export default IndexNavbar;