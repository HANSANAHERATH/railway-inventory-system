import { call, all, put, takeLatest } from 'redux-saga/effects';

import { 
    FETCH_LOCATION,
 } from '../actions/types';

import {
    fetchlocationDataSuccess,
    fetchlocationDataFailed,
} from '../actions/location';

import location from 'services/location';


/**
 * dashboard in saga implementation
 */
function* fetchLocationSaga({ payload }) {
    try {
        const data = yield call(location.location.fetchLocation,payload);        
        if(data?.status === true){
            yield put(fetchlocationDataSuccess(data));
        }else{
            yield put(fetchlocationDataFailed(data));
        }
    } catch (err) {
        const data = {
            data : [],
            status: true,
            error_msg: "ERROR",
        }
        yield put(fetchlocationDataFailed(data));
    }
}

function* watchLocationActions() {
    yield takeLatest(FETCH_LOCATION, fetchLocationSaga);
}

export default function* locationSaga() {
    yield all([watchLocationActions()]);
}
