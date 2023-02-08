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
          <Routes>
            <Route path="/login" element={<Login  />} />
            <Route path="/oauth2/redirect" element={<OAuth2RedirectHandler />} />
          </Routes>
        </div>
      </>
    );
}

export default LoginPage;
