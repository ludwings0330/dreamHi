import React from 'react';
import 'bootstrap/scss/bootstrap.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTwitter, faInstagram, faYoutube, faGithub } from '@fortawesome/free-brands-svg-icons';
import './MainFooter.css';

function MainFooter() {
  return (
    <footer>
      <div className={'footer-container'}>
        <div className="footer-left">
          <h1>AFRICA ELEPHANT</h1>
          <p>http://i8a702.p.ssafy.io</p>
          <div className={'sns'}>
            <i>
              <FontAwesomeIcon icon={faTwitter} />
            </i>
            <i>
              <FontAwesomeIcon icon={faInstagram} />
            </i>
            <i>
              <FontAwesomeIcon icon={faYoutube} />
            </i>
            <i>
              <FontAwesomeIcon icon={faGithub} />
            </i>
          </div>
        </div>
        <div className={'footer-right'}>
          <div className={'footer-list'}>
            <h2>CONTECT</h2>
            <ul>
              <li>contect #1</li>
              <li>contect #2</li>
              <li>contect #3</li>
              <li>contect #4</li>
            </ul>
          </div>
          <div className={'footer-list'}>
            <h2>ABOUT</h2>
            <ul>
              <li>about #1</li>
              <li>about #2</li>
              <li>about #3</li>
              <li>about #4</li>
            </ul>
          </div>
        </div>
        <div className={'copy-right'}>
          <img className={'footer-logo'} src={'/img/elephant.png'} alt={''} />
          <p>Made by Africa Elephant</p>
        </div>
      </div>
    </footer>
  );
}

export default MainFooter;
