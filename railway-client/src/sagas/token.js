import { call, all, put, takeLatest } from 'redux-saga/effects';

import tokenApi from '../services/token';

import { 
    FETCH_REFRESH_TOKEN,
    LOGOUT
 } from '../actions/types';

import {
    getRefreshTokenSuccess,
    getRefreshTokenFailed,
} from '../actions/token';

import {logout} from '../actions/signin'

/**
 * token in saga implementation
 */
function* getTokenSaga({ payload }) {
    try {
        const data = yield call(tokenApi.token.getToken);
        if(data.status === 200){
            yield put(getRefreshTokenSuccess(data.data));
        }else{
            yield put(getRefreshTokenFailed(data.data));
            yield put(logout());
            const win = window.open("/","_self");
        }
    } catch (err) {
        //yield put(logout());
        window.location.href="/";
        sessionStorage.clear();
        //yield put(getRefreshTokenFailed(err));
    }
}

function* watchTokenActions() {
    yield takeLatest(FETCH_REFRESH_TOKEN, getTokenSaga);
    yield takeLatest(LOGOUT, logout);
}

export default function* tokenSaga() {
    yield all([watchTokenActions()]);
}
