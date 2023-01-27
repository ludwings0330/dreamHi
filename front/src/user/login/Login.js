import React, { Component } from 'react';
import './Login.css';
import { GOOGLE_AUTH_URL, NAVER_AUTH_URL, KAKAO_AUTH_URL } from '../../constants';
import { BrowserRouter } from 'react-router-dom'
import googleLogo from '../../img/btn_google_signin_light_normal_web@2x.png';
import naverLogo from "../../img/btnG_완성형.png";
import kakaoLogo from '../../img/kakao_login_large_narrow.png';
import AppHeader from '../../components/Common/AppHeader';
import AppFooter from '../../components/Common/AppFooter';

class Login extends Component {

  render() {
    if(this.props.authenticated) {
      return <BrowserRouter
        to={{
          pathname: "/",
          state: { from: this.props.location }
        }}/>;
    }

    return (
      <div>
        <AppHeader />
        <div className="login-container">
        <div className="login-content">
          <h1 className="login-title">소셜로그인</h1>
          <SocialLogin />
        </div>
      </div>
        <AppFooter />
      </div>
    );
  }
}


class SocialLogin extends Component {
  render() {

    return (
      <div className="social-login">
        <a className="btn btn-block social-btn google" href={GOOGLE_AUTH_URL}>
          <img src={googleLogo} alt="Google" /> Log in with Google</a>
        <a className="btn btn-block social-btn naver" href={NAVER_AUTH_URL}>
          <img src={naverLogo} alt="Naver" /> Log in with Naver</a>
        <a className="btn btn-block social-btn kakao" href={KAKAO_AUTH_URL}>
          <img src={kakaoLogo} alt="Kakao" /> Log in with Kakao</a>
      </div>
    );
  }
}




export default Login
