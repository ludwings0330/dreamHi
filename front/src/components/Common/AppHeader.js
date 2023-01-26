import React from "react";
// nodejs library that concatenates strings
import classnames from "classnames";
// reactstrap components
import {
  Button,
  Navbar,
  NavLink,
  Container,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownItem,
  DropdownMenu, Col, Row
} from "reactstrap";

// react-icons
import { AiOutlineBell } from 'react-icons/ai'
import { CgProfile } from "react-icons/cg";

// css
import "./Common.css"
import "bootstrap/scss/bootstrap.scss";

function MainHeader() {

  return (
    <Navbar className="fixed-top" variant="dark" bg="dark" expand="lg">
      <Container className="header-main">
        <div>
          <div className="header-top">
            <div>
              <img src="img/logo.png" alt="DreamHi logo" className="header-logo" />
            </div>

            <div className="header-top-right">
              <UncontrolledDropdown>
                <DropdownToggle
                  caret
                  color="default"
                  data-toggle="dropdown"
                  nav
                >
                  <div className="notification d-none d-lg-block d-xl-block" />
                  <AiOutlineBell size="30" color="#7EA6F4" />
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
                  <b className="caret d-none d-lg-block d-xl-block" />
                  <CgProfile size="30" color="#7EA6F4" />
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
            </div>
          </div>

          <div className="header-bottom">
            <Row>
              <Col md="12">
                <Button
                  color="primary"
                  outline
                  type="button"
                  className="me-3"
                >
                  공고
                </Button>
                <Button color="primary" outline type="button" className="me-3">
                  배우
                </Button>
                <Button color="primary" outline type="button" className="me-3">
                  제작사
                </Button>
              </Col>
            </Row>
          </div>
        </div>
      </Container>
    </Navbar>

  );
}

export default MainHeader;