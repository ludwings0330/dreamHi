import React, { Component } from 'react';
import { ACCESS_TOKEN } from '../../constants';
import { REFRESH_TOKEN } from '../../constants';
import { BrowserRouter  } from 'react-router-dom'

class OAuth2RedirectHandler extends Component {
    getUrlParameter(name) {
        console.log(name);
        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
        let regex = new RegExp('[\\?&]' + name + '=([^&#]*)');

        let results = regex.exec(this.props.location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    };

    render() {        
        const accessToken = this.getUrlParameter(ACCESS_TOKEN);
        const refreshToken = this.getUrlParameter(REFRESH_TOKEN);
        const error = this.getUrlParameter('error');

        if(accessToken) {
            localStorage.setItem(ACCESS_TOKEN, accessToken);
            localStorage.setItem(REFRESH_TOKEN, refreshToken);
            return <BrowserRouter to={{
                pathname: "/profile",
                state: { from: this.props.location }
            }}/>; 
        } else {
            return <BrowserRouter to={{
                pathname: "/login",
                state: { 
                    from: this.props.location,
                    error: error 
                }
            }}/>; 
        }
    }
}

export default OAuth2RedirectHandler;