import { call, all, put, takeLatest } from 'redux-saga/effects';

import sessionApi from '../services/sessionApi';

import { FETCH_CENTERS, SUBMIT_FORM_SESSION } from '../actions/types';

import {
    fetchCentersSuccess,
    fetchCentersFailed,
    submitFormSessionCreateSuccess,
    submitFormSessionCreateFailed,
} from '../actions/session';

function* fetchCenters() {
    try {
        const data = yield call(sessionApi.centers.getCenters);
        yield put(fetchCentersSuccess(data));
    } catch (err) {
        yield put(fetchCentersFailed(err));
    }
}

function* submitFormSessionCreate({ payload }) {
    try {
        const data = yield call(sessionApi.sessions.submit, payload);
        yield put(submitFormSessionCreateSuccess(data));
    } catch (err) {
        yield put(submitFormSessionCreateFailed(err));
    }
}

function* watchRegistrationActions() {
    yield takeLatest(FETCH_CENTERS, fetchCenters);
    yield takeLatest(SUBMIT_FORM_SESSION, submitFormSessionCreate);
}

export default function* registrationSaga() {
    yield all([watchRegistrationActions()]);
}
