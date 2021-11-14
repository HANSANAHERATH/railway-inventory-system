import { call, all, put, takeLatest } from 'redux-saga/effects';

import signInApi from '../services/signInApi';

import { LOGIN_WITH_USER_PASS } from '../actions/types';

import {
    loginWithUserPassSuccess,
    loginWithUserPassFailed
} from '../actions/signin';

/**
 * sign in saga implementation
 */
function* loginWithUserPassSaga({ payload }) {
    try {
        let data = yield call(signInApi.login.loginApi, payload);
        yield put(loginWithUserPassSuccess(data));
    } catch (err) {
        yield put(loginWithUserPassFailed(err));
    }
}

/* function* logout() {
    try {
        yield put(logoutSuccess());
    } catch (err) {}
} */

function* watchSignInActions() {
    yield takeLatest(LOGIN_WITH_USER_PASS, loginWithUserPassSaga);
    //yield takeLatest(LOGOUT, logout);
}

export default function* signInSaga() {
    yield all([watchSignInActions()]);
}
