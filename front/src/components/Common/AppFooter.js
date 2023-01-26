import React from "react";

// reactstrap components
import {
  Row,
  Col,
  Button,
  UncontrolledTooltip
} from "reactstrap"
import "./Common.css"

function MainFooter() {
  return (
    <footer className="footer-body">
      <div className="footer-left">
        <img src='img/elephant.png' alt='elephant-logo' className="footer-logo" />
        <span>© 2023 All Rights Reserved</span>
      </div>
      <div className="footer-right">
        <p>Made by Africa Elephant</p>
        <p>마지막 선물은 산뜻한 안녕 저기 사라진 별의 자리 아스라히 하얀 빛</p>
        <Row className="justify-content-md-center sharing-area text-center">
          <Col className="text-center" lg="12" md="12">
            <Button
              id="twitter"
              onClick={(e) => e.preventDefault()}
            >
              <i className="fa fa-twitter" /> Twitter
            </Button>
            <UncontrolledTooltip delay={0} target="twitter">
              Tweet!
            </UncontrolledTooltip>
            <Button
              id="tooltip840791273"
              onClick={(e) => e.preventDefault()}
            >
              <i className="fa fa-google-plus" /> Google
            </Button>
            <UncontrolledTooltip delay={0} target="tooltip840791273">
              Share!
            </UncontrolledTooltip>
            <Button
              onClick={(e) => e.preventDefault()}
            >
              <i className="fa fa-facebook-square" /> Facebook
            </Button>
          </Col>
        </Row>
      </div>
    </footer>
  );
}

export default MainFooter;
