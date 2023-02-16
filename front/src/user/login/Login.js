import React from 'react';
import './Login.css';
import { GOOGLE_AUTH_URL, NAVER_AUTH_URL, KAKAO_AUTH_URL } from '../../constants';
import { BrowserRouter } from 'react-router-dom';
import googleLogo from '../../img/btn_google_signin_light_normal_web.png';
import naverLogo from '../../img/btnG.png';
import kakaoLogo from '../../img/kakao_login_large_narrow.png';
import { userSimpleState } from '../../recoil/user/userStore';
import { useRecoilState } from 'recoil';
import { useEffect } from 'react';
import { Navigate } from 'react-router-dom';

const Login = ({ authenticated, location }) => {
  const [userSimple, setUserSimple] = useRecoilState(userSimpleState);

  if (userSimple) {
    return (
      <div>
        <Navigate to={'/'} replace={true} />
      </div>
    );
  }

  return (
    <>
      <div id="login-whole">
        <div className="login-content">
          <h1 className="login-title">소셜로그인</h1>
          <p>기존에 사용하시는 계정으로 간단하게 회원가입 하세요.</p>
        </div>
        <SocialLogin />
      </div>
    </>
  );
};

const SocialLogin = () => (
  <div id="social-login">
    <div className={'google-login'}>
      <a href={GOOGLE_AUTH_URL}>
        <img src={googleLogo} alt="Google" />
      </a>
    </div>

    <div className={'naver-login'}>
      <a href={NAVER_AUTH_URL}>
        <img src={naverLogo} alt="Naver" />
      </a>
    </div>

    <div className={'kakao-login'}>
      <a href={KAKAO_AUTH_URL}>
        <img src={kakaoLogo} alt="Kakao" />
      </a>
    </div>
  </div>
);

export default Login;
