import { call, all, put, takeLatest } from 'redux-saga/effects';

import dashboard from '../services/dashboard';

import { 
    FETCH_DASHBOARD,
 } from '../actions/types';

import {
    fetchDashboardSuccess,
    fetchDashboardFailed,
} from '../actions/dashboard';


/**
 * dashboard in saga implementation
 */
function* fetchDashboardSaga({payload}) {
    try {
        const data = yield call(dashboard.dashboard.fetchDashboard,payload);
        if(data?.status === true){
            yield put(fetchDashboardSuccess(data));
        }else{
            yield put(fetchDashboardFailed(data));
        }
    } catch (err) {
        yield put(fetchDashboardFailed(err));
    }
}

function* watchTokenActions() {
    yield takeLatest(FETCH_DASHBOARD, fetchDashboardSaga);
}

export default function* dashboardSaga() {
    yield all([watchTokenActions()]);
}
