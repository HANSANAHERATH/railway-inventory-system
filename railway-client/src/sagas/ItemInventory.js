import { call, all, put, takeLatest } from 'redux-saga/effects';

import { 
    FETCH_LOOKUP_ITEMS,
    CREATE_INVENTORY,
    FETCH_INVENTORY,
 } from '../actions/types';

import {
    getLookupValuesSuccess,
    getLookupValuesFailed,
    submitItemInventoryCreateSuccess,
    submitItemInventoryCreateFailed,
    getInventoryListSuccess,
    getInventoryListFailed,
} from '../actions/ItemInventory';

import itemInventory from 'services/ItemInventory';

function* fetchItemsLookup({ payload }) {
    try {
        const data = yield call(itemInventory.items.fetchLookupData,payload);        
        if(data?.status === true){
            yield put(getLookupValuesSuccess(data));
        }else{
            yield put(getLookupValuesFailed(data));
        }
    } catch (err) {
        const data = {
            data : [],
            status: true,
            statusMessage: "ERROR",
        }
        yield put(getLookupValuesFailed(data));
    }
}

function* createInventory({ payload }) {
    try {
        const data = yield call(itemInventory.items.createInventory,payload);        
        if(data?.status === true){
            yield put(submitItemInventoryCreateSuccess(data));
        }else{
            yield put(submitItemInventoryCreateFailed(data));
        }
    } catch (err) {
        const data = {
            data : [],
            status: true,
            statusMessage: "ERROR",
        }
        yield put(submitItemInventoryCreateFailed(data));
    }
}

function* fetchInventoryList({ payload }) {
    try {
        const data = yield call(itemInventory.items.fetchInvenoryList,payload);        
        if(data?.status === true){
            yield put(getInventoryListSuccess(data));
        }else{
            yield put(getInventoryListFailed(data));
        }
    } catch (err) {
        const data = {
            content: [],
            page: 0,
            pageSize: 0,
            totalCount: 0,
            totalPage: 0,
            statusMessage: "ERROR",
        }
        yield put(getInventoryListFailed(data));
    }
}


function* watchLocationActions() {
    yield takeLatest(FETCH_LOOKUP_ITEMS, fetchItemsLookup);
    yield takeLatest(CREATE_INVENTORY, createInventory);
    yield takeLatest(FETCH_INVENTORY, fetchInventoryList);
}

export default function* locationSaga() {
    yield all([watchLocationActions()]);
}
