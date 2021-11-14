import { call, all, put, takeLatest } from 'redux-saga/effects';

import registrationListApi from '../services/registrationApi';

import { 
    FETCH_REGISTERED_USERS,
    FETCH_SELECTED_SESSIONS,
} from '../actions/types';

import {
    fetchRegisteredUsersSuccess,
    fetchRegisteredUsersFailed,
    fetchSelectedSessionSuccess,
    fetchSelectedSessionFailed,
} from '../actions/registrationList';

function* fetchRegisteredUsersForSession({ payload }) {
    try {
        const data = yield call(registrationListApi.users.getRegisteredUsers,payload);
        if(data.status){
            yield put(fetchRegisteredUsersSuccess(data));
        }else{
            yield put(fetchRegisteredUsersFailed(data));
        }
    } catch (err) {
        let data = {
            "data": [],
            "status": false,
            "error_msg": 'Error occur in fetch registered users.',
            "timestamp": "2021-06-14 17:40:23.074",
            "error_code": null
        }
        yield put(fetchRegisteredUsersFailed(data));
    }
}

/** Fetch selected session list for dashboard */
function* fetchSelectedSessionList({ payload }) {
    try {
        const data = yield call(registrationListApi.dashboard.getSessionList,payload);
        if(data.status){
            yield put(fetchSelectedSessionSuccess(data));
        }else {
            yield put(fetchSelectedSessionFailed(data));
        }     
    } catch (err) {
        let data = {
            "data": [],
            "status": true,
            "error_msg": "Error occured when fetch registered session list.",
            "timestamp": "2021-06-14 17:40:23.074",
            "error_code": null
        }
        yield put(fetchSelectedSessionFailed(data));
    }
}

function* watchRegistrationListActions() {
    yield takeLatest(FETCH_REGISTERED_USERS, fetchRegisteredUsersForSession);
    yield takeLatest(FETCH_SELECTED_SESSIONS, fetchSelectedSessionList);
}

export default function* registrationListSaga() {
    yield all([watchRegistrationListActions()]);
}
