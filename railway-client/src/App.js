import React, { Component } from 'react';
import { Router } from 'react-router-dom';
import { createBrowserHistory } from 'history';
import { ThemeProvider } from '@material-ui/styles';
import validate from 'validate.js';

import theme from './theme';
import 'react-perfect-scrollbar/dist/css/styles.css';
import './assets/scss/index.scss';
import validators from './common/validators';
import Routes from './Routes';
import setupAxios from './utils/axios';

import IdleTimer from 'react-idle-timer'
import { connect } from 'react-redux';

import { logout } from 'actions/signin';
import { getRefreshToken } from 'actions/token';
import jwt_decode from "jwt-decode";
import moment from 'moment';

const browserHistory = createBrowserHistory();

validate.validators = {
    ...validate.validators,
    ...validators,
};

setupAxios();

class App extends Component {
    constructor(props) {
        super(props)
        this.idleTimer = null
        this.handleOnAction = this.handleOnAction.bind(this)
        this.handleOnActive = this.handleOnActive.bind(this)
        this.handleOnIdle = this.handleOnIdle.bind(this)
    }

    handleOnAction(event) {
        if(sessionStorage.getItem('loginSuccess')){
            //sessionStorage.setItem('loginSuccessTime', new Date());
           // var token = sessionStorage.getItem("token")
           // var decoded = jwt_decode(token);
           // let expireTime = moment(new Date(decoded.exp*1000));
            //let beforeTimeExpire = moment(expireTime - (5 * 60 * 1000)); // minite
        
           /*  if( moment(new Date()).isAfter(beforeTimeExpire) &&  beforeTimeExpire.isBefore(expireTime)){
                this.props.getRefreshToken();
            } */
        }
    }

    handleOnActive(event) {
      
    }

    handleOnIdle(event) {
        if(sessionStorage.getItem('loginSuccess')){
            sessionStorage.removeItem('token');
            sessionStorage.removeItem('loginSuccess');
            sessionStorage.removeItem('mobile');
            window.location = '/sign-in';
            this.props.logout();
        }
       
    }

    render() {
        return (
            <div>
                <IdleTimer
                    ref={ref => { this.idleTimer = ref }}
                    timeout={1000 * 60 * 60}
                    onActive={this.handleOnActive}
                    onIdle={this.handleOnIdle}
                    onAction={this.handleOnAction}
                    debounce={250}
                />

                <ThemeProvider theme={theme}>
                    <Router history={browserHistory}>
                        <Routes />
                    </Router>
                </ThemeProvider>
            </div>
        );
    }
}
function mapStateToProps({ signin }) {
    let { loginsuccess } = signin;
    return {
        loginsuccess,
    };
}

export default connect(mapStateToProps, {logout , getRefreshToken})(App);
