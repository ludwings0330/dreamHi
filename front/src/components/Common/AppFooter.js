import React from "react";

// reactstrap components
import {Row, Container, Col, Button, UncontrolledTooltip} from "reactstrap";

function MainFooter() {
  return (
    <footer className="footer footer-black footer-white">
      <Container>
        <Row>
          <nav className="footer-nav">
            <ul>
              <li>
                <img src="img/elephant.png" alt="footer-img" className="logo-footer" />
                ⓒ 2023 All Rights Reserved
              </li>
            </ul>
          </nav>
          <div className="credits ml-auto">
            <span className="copyright">
              Made by Africa Elephant
            </span>
            <Row className="justify-content-md-center sharing-area text-center">
              <Col className="text-center" lg="8" md="12">
                <Button
                  className="twitter-sharrre btn-round"
                  color="twitter-bg"
                  href="#pablo"
                  id="tooltip3373767"
                  onClick={(e) => e.preventDefault()}
                >
                  <i className="fa fa-twitter" /> Twitter
                </Button>
                <UncontrolledTooltip delay={0} target="tooltip3373767">
                  Tweet!
                </UncontrolledTooltip>
                <Button
                  className="linkedin-sharrre btn-round  ml-2"
                  color="google-bg"
                  href="https://www.google.com/"
                  id="tooltip840791273"
                  onClick={(e) => e.preventDefault()}
                >
                  <i className="fa fa-google-plus" /> Google
                </Button>
                <UncontrolledTooltip delay={0} target="tooltip840791273">
                  Share!
                </UncontrolledTooltip>
                <Button
                  className="facebook-sharrre btn-round ml-2"
                  color="facebook-bg"
                  href="#pablo"
                  id="tooltip68961360"
                  onClick={(e) => e.preventDefault()}
                >
                  <i className="fa fa-facebook-square" /> Facebook
                </Button>
              </Col>
            </Row>
          </div>
        </Row>
      </Container>
    </footer>
  );
}

export default MainFooter;
