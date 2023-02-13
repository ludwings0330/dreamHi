import React, {  } from 'react';
import {
  Route,
  Routes
} from 'react-router-dom';
import Login from '../../user/login/Login';
import OAuth2RedirectHandler from '../../user/oauth2/OAuth2RedirectHandler';
import './LoginPage.css';


function LoginPage() {
    return (
      <>
        <div className="app-body">
          <Login />
        </div>
      </>
    );
}

export default LoginPage;
