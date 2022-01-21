import {
    LOGIN_WITH_USER_PASS,
    LOGIN_WITH_USER_PASS_SUCCESS,
    LOGIN_WITH_USER_PASS_FAILED,
} from '../actions/types';

const initialState = {
    loading: false,
    error: undefined,
    loginSuccess: false,
};

const signinReducer = (state = initialState, action) => {
    switch (action.type) {
        case LOGIN_WITH_USER_PASS:
            return {
                ...state,
                loading: true,
            };
        case LOGIN_WITH_USER_PASS_SUCCESS:
            sessionStorage.setItem('token', action.payload.token);
            sessionStorage.setItem('loginSuccess', true);
            sessionStorage.setItem('loginSuccessTime', new Date());
            return {
                ...state,
                loading: false,
                loginSuccess: true
            };
        case LOGIN_WITH_USER_PASS_FAILED:
            return {
                ...state,
                loading: false,
                loginSuccess: false,
                error: 'Try again.',
            };
        default:
            return state;
    }
};

export default signinReducer;
