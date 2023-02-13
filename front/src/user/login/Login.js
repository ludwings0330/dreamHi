import React from 'react';
import './Login.css';
import { GOOGLE_AUTH_URL, NAVER_AUTH_URL, KAKAO_AUTH_URL } from '../../constants';
import { BrowserRouter } from 'react-router-dom';
import googleLogo from '../../img/btn_google_signin_light_normal_web@2x.png';
import naverLogo from '../../img/btnG_완성형.png';
import kakaoLogo from '../../img/kakao_login_large_narrow.png';
import { userSimpleState } from '../../recoil/user/userStore';
import { useRecoilState } from 'recoil';
import { useEffect } from 'react';
import {Navigate} from "react-router-dom";
const Login = ({ authenticated, location }) => {
  const [userSimple, setUserSimple] = useRecoilState(userSimpleState);

  if (userSimple.id !== '') {
    return (
      <div><Navigate to={"/"} replace={true}/></div>
    );
  }

  return (
    <div>
      <div className="login-container">
        <div className="login-content">
          <h1 className="login-title">소셜로그인</h1>
          <SocialLogin />
        </div>
      </div>
    </div>
  );
};

const SocialLogin = () => (
  <div className="social-login">
    <a className="btn btn-block social-btn google" href={GOOGLE_AUTH_URL}>
      <img src={googleLogo} alt="Google" /> Log in with Google
    </a>
    <a className="btn btn-block social-btn naver" href={NAVER_AUTH_URL}>
      <img src={naverLogo} alt="Naver" /> Log in with Naver
    </a>
    <a className="btn btn-block social-btn kakao" href={KAKAO_AUTH_URL}>
      <img src={kakaoLogo} alt="Kakao" /> Log in with Kakao
    </a>
  </div>
);

export default Login;
