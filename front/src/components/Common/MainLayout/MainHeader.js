import React, { useState } from 'react';
import { ACCESS_TOKEN } from '../../../constants';

import {
  Navbar,
  NavLink,
  Container,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownItem,
  DropdownMenu, Col, Row
} from "reactstrap";

import Button from '../CommonComponent/Button';
import { Link, useNavigate } from 'react-router-dom';


// react-icons
import { AiOutlineBell } from 'react-icons/ai'
import { CgProfile } from "react-icons/cg";

// css
import './MainHeader.css';
import "bootstrap/scss/bootstrap.scss";
import { userSimpleSelector } from 'recoil/user/userStore';
import { useRecoilValue } from 'recoil';

// api
import {logout} from "service/authService";


function MainHeader() {
  const navigate = useNavigate();
  const userSimple = useRecoilValue(userSimpleSelector());
  
  const logoutClick = () => {
    logout();
  }
  return (

    <Navbar>
      <Container className={"main-container"}>


        <div className="header-top">


          {/*메인로고 이미지*/}
          <div>
            <img title="home"
                 onClick={() => {
                   navigate("/")
                 }} src= {'/img/logo.png'} alt="DreamHi logo" className="header-logo" />
          </div>


          <div className="header-top-right">

            {/*로그인 전에 보이는 버튼*/}
            {userSimple.id == null ?
              <Button
                title="로그인"
                onClick={() => {
                  navigate("/login")
                }} /> :

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


                  <Link to={"/actor/detail"} tag="li">
                    <DropdownItem className="nav-item">내 이력서</DropdownItem>
                  </Link>

                  <Link to={"/maker/detail"} tag="li">
                    <DropdownItem className="nav-item">내 제작사</DropdownItem>
                  </Link>

                  <Link to={"/collection"} tag="li">
                    <DropdownItem className="nav-item">모아보기</DropdownItem>
                  </Link>

                  <Link to={"/"} tag="li">
                    <DropdownItem className="nav-item" onClick={logoutClick}
                    >로그아웃</DropdownItem>
                  </Link>

                </DropdownMenu>
              </UncontrolledDropdown>
            }



            {/*공백부분*/}
            <div className="space-y-6 bg-white px-4 py-5 sm:p-6">
            </div>

            {/*로그인 시 보이는 토글버튼*/}


          </div>


        </div>




        {/*공고 배우 제작사 버튼 구현 부분*/}
        <div className="header-bottom">
          <Row>
            <Col md="12">

              <Button
                title="공고"
                onClick={() => {
                  navigate("/announcement")
                }} />



              <Button
                title="배우"
                onClick={() => {
                  navigate("/actor")
                }} />


              <Button
                title="제작사"
                onClick={() => {
                  navigate("/maker")
                }} />


            </Col>
          </Row>
        </div>

        <hr  className={"header-line"}/>



      </Container>
    </Navbar>

  );
}

export default MainHeader;
