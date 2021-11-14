import React, { useState } from 'react';

import { connect } from 'react-redux';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { Redirect } from 'react-router-dom';

import { loginWithUserPassword } from 'actions/signin';
import ErrorDisplay from 'views/Common/ErrorDisplay';
import Spinner from 'views/Common/Spinner';

const useStyles = makeStyles(theme => ({
    container: {
        padding: '20px',
        background: '#FFFFFF 0% 0% no-repeat padding-box',
        boxShadow: ' 0px 0px 12px #0000001F',
        borderRadius: '17px',
        opacity: 1,
    },
    paper: {
        marginTop: theme.spacing(2),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(1),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
    otpClass: {
        padding: '10px 50px',
    },
}));


const SignIn = ({
    loading,
    error,
    loginSuccess,
    loginWithUserPassword
}) => {
    const classes = useStyles();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const onUsernameFieldChange = event => {
        let value = event.target.value;
        setUsername(value);
    };

    const onPasswordFieldChange = event => {
        let value = event.target.value;
        setPassword(value);
    };


    const loginWithUserPass = () => {
        loginWithUserPassword({
            username: username,
            password: password
        });
    };
 
    return loginSuccess ? (
        <Redirect to="/item-list" />
    ) : loading ? (
        <Spinner></Spinner>
    ) : error ? (
        <ErrorDisplay error={error} refreshButton={true}></ErrorDisplay>
    ) : (
        <div className={classes.root}>
            <Container
                component="main"
                maxWidth="xs"
                className={classes.container}
            >
                <CssBaseline />
                <div className={classes.paper}>
                    <Avatar className={classes.avatar}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h4">
                        Sign in
                    </Typography>
                        <div className={classes.form} noValidate>
                            <TextField
                                variant="outlined"
                                margin="normal"
                                fullWidth
                                id="username"
                                label="Username"
                                name="username"
                                autoFocus
                                onChange={onUsernameFieldChange}
                            />
                            <TextField
                                variant="outlined"
                                margin="normal"
                                fullWidth
                                type="password"
                                id="password"
                                label="Password"
                                name="password"
                                autoFocus
                                onChange={onPasswordFieldChange}
                            />
                            <Button
                                variant="contained"
                                fullWidth
                                color="primary"
                                className={classes.submit}
                                onClick={() => loginWithUserPass()}
                                disabled={
                                    username.length < 1 || password.length < 1
                                }
                                type="submit"
                            >
                                LOGIN
                            </Button>
                        </div>
                </div>
            </Container>
        </div>
    );
};

function mapStateToProps({ signin }) {
    let {loading, error } = signin;
    let loginSuccess = sessionStorage.getItem('loginSuccess');
    return {
        loading,
        error,
        loginSuccess,
    };
}

export default connect(mapStateToProps, { loginWithUserPassword })(SignIn);
