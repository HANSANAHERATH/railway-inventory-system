import {
    LOGOUT,
    LOGIN_WITH_USER_PASS,
    LOGIN_WITH_USER_PASS_SUCCESS,
    LOGIN_WITH_USER_PASS_FAILED,
    LOGIN_WITH_USER_PASS_RESET
} from './types';

export const logout = payload => {
    return { type: LOGOUT, payload };
};


/*********************************************** */
export const loginWithUserPassword = payload => {
    return { type: LOGIN_WITH_USER_PASS, payload };
};

export const loginWithUserPassSuccess = payload => {
    return { type: LOGIN_WITH_USER_PASS_SUCCESS, payload };
};

export const loginWithUserPassFailed = payload => {
    return { type: LOGIN_WITH_USER_PASS_FAILED, payload };
};
