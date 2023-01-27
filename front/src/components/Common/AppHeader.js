import React from 'react';

import {
  Navbar,
  NavLink,
  Container,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownItem,
  DropdownMenu, Col, Row
} from "reactstrap";

import Button from './Button';


// react-icons
import { AiOutlineBell } from 'react-icons/ai'
import { CgProfile } from "react-icons/cg";

// css
import "./Common.css"
import "bootstrap/scss/bootstrap.scss";
import { Link } from 'react-router-dom';
import AppPost from '../../pages/AppPost';
import AppActor from '../../pages/AppActor';
import AppMain from '../../pages/AppMain';
import AppMaker from '../../pages/AppMaker';

function MainHeader() {

  return (

    <Navbar>
      <Container className={"main-container"}>
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
                  <div className="notification d-none d-lg-block d-xl-block"/>
                  <AiOutlineBell size="40" color="#7EA6F4" />
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
                  <CgProfile size="40" color="#7EA6F4" />
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

                <Button>
                  공고
                </Button>



                <Button>
                  배우
                </Button>



                <Button>
                  제작사
                </Button>


              </Col>
            </Row>
          </div>

          <hr  className={"header-line"}/>
        </div>


      </Container>
    </Navbar>

  );
}

export default MainHeader;

