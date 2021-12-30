import { call, all, put, takeLatest } from 'redux-saga/effects';

import sessionApi from '../services/sessionApi';

import {
    FETCH_SESSIONS,
    FETCH_DISTRICT,
    NOTIFY_ALL,
    CANCEL_SESSION,
    FETCH_CANCELLED_SESSIONS,


    FETCH_UNIT_TYPE,
    SUBMIT_FORM_ITEM,
    UPDATE_FORM_ITEM,
    RESET_FORM_ITEM,
    FETCH_ITEMS,
    DELETE_FORM_ITEM,
    FETCH_CATEGORY,
} from '../actions/types';
import {
    fetchDistrictFailed,
    fetchDistrictSuccess,
    fetchSessionsSuccess,
    fetchSessionsFailed,
    notifyAllSuccess,
    notifyAllFailed,
    cancelSessionSuccess,
    cancelSessionFailed,
    fetchCancelledSessionsSuccess,
    fetchCancelledSessionsFailed,


    fetchUnitTypsFailed,
    fetchUnitTypesSuccess,
    submitFormItemCreateSuccess,
    submitFormItemCreateFailed,
    submitFormItemUpdateSuccess,
    submitFormItemUpdateFailed,
    restFormItemSuccess,
    fetchItemsSuccess,
    fetchItemsFailed,
    submitFormItemRemoveSuccess,
    submitFormItemRemoveFailed,
    fetchItemCategorySuccess,
    fetchItemCategoryFailed,
} from 'actions/session';

function* fetchItems({ payload }) {
    try {
        const data = yield call(sessionApi.Items.getAll,payload);
        if(data.status){
            yield put(fetchItemsSuccess(data));
        }else{
            yield put(fetchItemsFailed(data));
        }
    } catch (err) {
        let data = {
            "data": [],
            "status": false,
            "statusMessage": "",
            "error_code": null
        }
        yield put(fetchItemsFailed(data));
    }
}

function* notifyAllUsers({ payload }) {
    try {
        const data = yield call(sessionApi.notify.notifyAll, payload);
        if(data?.status){
            yield put(notifyAllSuccess(data));
        }else{
            yield put(notifyAllFailed(data));
        }
         
    } catch (err) {
        let data = {
            status : false,
            error_msg: "Fail to send SMS",
            error_code: "",
            timestamp : "",
        }
        yield put(notifyAllFailed(data));
    }
}

/** Fetch Disctricts master data */
function* fetchDistricts() {
    try {
        const data = yield call(sessionApi.districts.getDistricts);
        if(data.status){
            yield put(fetchDistrictSuccess(data));
        }else {
            yield put(fetchDistrictFailed(data));
        }
    } catch (err) {
        let data = {
            "data": [],
            "status": false,
            "error_msg": "",
            "timestamp": "2021-06-11 15:29:52.196",
            "error_code": null
        }
        yield put(fetchDistrictFailed(data));
    }
}

function* fetchSessionsData({payload}) {
    try {
        const data = yield call(sessionApi.sessions.getAll,payload);
        if(data.status){
            yield put(fetchSessionsSuccess(data));
        }else{
            yield put(fetchSessionsFailed(data));
        }
    } catch (err) {
        let data = {
            "data": [],
            "status": true,
            "error_msg": null,
            "timestamp": "2021-06-13 09:54:38.022",
            "error_code": null
        }
        yield put(fetchSessionsFailed(data));
    }
}

function* deleteItemFormData({ payload }) {
    try {
        const data = yield call(sessionApi.Items.delete, payload);
        if(data.status){
            yield put(submitFormItemRemoveSuccess(data));
        }else{
            yield put(submitFormItemRemoveFailed(data));
        }
    } catch (err) {
        let data = {
            "data": [],
            "status": false,
            "statusMessage": "The session delete failed.",
            "error_code": null
        } 
        yield put(submitFormItemRemoveFailed(data));
    }
}

function* resetItemForm() {
    //yield put(fetchCancelledSessions());
    yield put(restFormItemSuccess());
}

function* cancelSessions({payload}) {
    try {
        const data = yield call(sessionApi.cancel.cancelSessions,payload);
        if(data?.status){
            yield put(cancelSessionSuccess(data));
        }else{
            yield put(cancelSessionFailed(data));
        }
    } catch (err) {
        let data = {
            "data": [],
            "status": false,
            "error_msg": "The session cancellation failed.",
            "timestamp": "2021-06-05 12:28:06.465",
            "error_code": null
        }
        yield put(cancelSessionFailed(data));
    }
}

function* fetchCancelledSessionsList() {
    try {
        let data = yield call(sessionApi.cancel.getCancelledSessionList);
        if(data?.status){
            yield put(fetchCancelledSessionsSuccess(data));
        }else{
            yield put(fetchCancelledSessionsFailed(data));
        }
    } catch (err) {
        let data = {
            "data": [],
            "status": false,
            "error_msg": null,
            "timestamp": "2021-06-15 14:55:58.892",
            "error_code": null
        }
        yield put(fetchCancelledSessionsFailed(data));
    }
}
/**---------------------------------------------------------- */
function* fetchUnitTypes() {
    try {
        const data = yield call(sessionApi.units.unitTypes);
        if(data?.status){
            yield put(fetchUnitTypesSuccess(data));
        }else{
            yield put(fetchUnitTypsFailed(data));
        }
    } catch (err) {
        let data = {
            "data": {},
            "status": false,
            "error_msg": null,
            "timestamp": "2021-06-05 12:28:06.465",
            "error_code": null
        }
        yield put(fetchUnitTypsFailed(data));
    }
}

function* submitFormItem({ payload }) {
    try {
        const data = yield call(sessionApi.Items.submit, payload);
        if(data.status){
           // yield put(fetchSessions());
            yield put(submitFormItemCreateSuccess(data));
        }else{
            yield put(submitFormItemCreateFailed(data));
        }
    } catch (err) {
        let data = {statusMessage: "Error occurred when creating the Item."}
        yield put(submitFormItemCreateFailed(data));
    }
}

function* updateFormItem({ payload }) {
    try {
        let data = yield call(sessionApi.Items.update, payload);
        if(data.status){
            //yield put(fetchSessions());
            yield put(submitFormItemUpdateSuccess(data));
        }else{
            yield put(submitFormItemUpdateFailed(data));
        }
    } catch (err) {
        let data = {error_msg: "Error occurred when updating the item."}
        yield put(submitFormItemUpdateFailed(data));
    }
}

function* fetchItemCategory({ payload }) {
    try {
        let data = yield call(sessionApi.Category.categoryList);
        if(data.status){
            yield put(fetchItemCategorySuccess(data));
        }else{
            yield put(fetchItemCategoryFailed(data));
        }
    } catch (err) {
        let data = {error_msg: "Error occurred when fetch item categories."}
        yield put(fetchItemCategoryFailed(data));
    }
}

function* watchSessionActions() {
    // takeevery
    yield takeLatest(FETCH_DISTRICT, fetchDistricts);
    yield takeLatest(FETCH_SESSIONS, fetchSessionsData);
    yield takeLatest(NOTIFY_ALL, notifyAllUsers);
    yield takeLatest(CANCEL_SESSION, cancelSessions);
    yield takeLatest(FETCH_CANCELLED_SESSIONS, fetchCancelledSessionsList);
    
    yield takeLatest(DELETE_FORM_ITEM, deleteItemFormData);
    yield takeLatest(FETCH_ITEMS, fetchItems);
    yield takeLatest(RESET_FORM_ITEM, resetItemForm);
    yield takeLatest(UPDATE_FORM_ITEM, updateFormItem);
    yield takeLatest(SUBMIT_FORM_ITEM, submitFormItem);
    yield takeLatest(FETCH_UNIT_TYPE, fetchUnitTypes);
    yield takeLatest(FETCH_CATEGORY, fetchItemCategory);
}

export default function* SessionSaga() {
    yield all([watchSessionActions()]);
}
