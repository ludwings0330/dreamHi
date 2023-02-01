import React, { Component } from 'react';
import {
  Route,
  Routes
} from 'react-router-dom';
import MainHeader from '../../components/Common/MainHeader';
import Login from '../../user/login/Login';
import OAuth2RedirectHandler from '../../user/oauth2/OAuth2RedirectHandler';
import LoadingIndicator from '../../components/Common/LoadingIndicator';
import { getCurrentUser } from '../../util/APIUtils';
import { ACCESS_TOKEN } from '../../constants';
import './LoginPage.css';
import MainFooter from '../../components/Common/MainFooter';
import Layout from '../../components/Common/Layout';


class LoginPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      authenticated: false,
      currentUser: null,
      loading: true
    }

    this.loadCurrentlyLoggedInUser = this.loadCurrentlyLoggedInUser.bind(this);
    this.handleLogout = this.handleLogout.bind(this);
  }

  loadCurrentlyLoggedInUser() {
    getCurrentUser()
      .then(response => {
        this.setState({
          currentUser: response,
          authenticated: true,
          loading: false
        });
      }).catch(error => {
      this.setState({
        loading: false
      });
    });
  }

  handleLogout() {
    localStorage.removeItem(ACCESS_TOKEN);
    this.setState({
      authenticated: false,
      currentUser: null
    });
  }

  componentDidMount() {
    this.loadCurrentlyLoggedInUser();
  }

  render() {
    if(this.state.loading) {
      return <LoadingIndicator />
    }

    return (
      <Layout className="app">

        <div className="app-body">
          <Login />
          <Routes>
            <Route path="/login" element={<Login authenticated={this.state.authenticated} />} />
            <Route path="/oauth2/redirect" element={<OAuth2RedirectHandler />} />
          </Routes>
        </div>
      </Layout>
    );
  }
}

export default LoginPage;
