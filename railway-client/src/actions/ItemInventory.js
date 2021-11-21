import {
    FETCH_LOOKUP_ITEMS,
    FETCH_LOOKUP_ITEMS_SUCCESS,
    FETCH_LOOKUP_ITEMS_FAILED,
    FETCH_LOOKUP_ITEMS_RESET,
    CREATE_INVENTORY,
    CREATE_INVENTORY_SUCCESS,
    CREATE_INVENTORY_FAILED,
    CREATE_INVENTORY_RESET,
    FETCH_INVENTORY,
    FETCH_INVENTORY_SUCCESS,
    FETCH_INVENTORY_FAILED,
    FETCH_INVENTORY_RESET,
} from './types';

export const getLookupValues = payload => {
    return { type: FETCH_LOOKUP_ITEMS, payload };
};

export const getLookupValuesSuccess = payload => {
    return { type: FETCH_LOOKUP_ITEMS_SUCCESS, payload };
};

export const getLookupValuesFailed = payload => {
    return { type: FETCH_LOOKUP_ITEMS_FAILED, payload };
};

export const getLookupValuesReset = payload => {
    return { type: FETCH_LOOKUP_ITEMS_RESET, payload };
};


export const submitItemInventoryCreate = payload => {
    return { type: CREATE_INVENTORY, payload };
};

export const submitItemInventoryCreateSuccess = payload => {
    return { type: CREATE_INVENTORY_SUCCESS, payload };
};

export const submitItemInventoryCreateFailed = payload => {
    return { type: CREATE_INVENTORY_FAILED, payload };
};

export const submitItemInventoryCreateReset = payload => {
    return { type: CREATE_INVENTORY_RESET, payload };
};


export const getInventoryList = payload => {
    return { type: FETCH_INVENTORY, payload };
};

export const getInventoryListSuccess = payload => {
    return { type: FETCH_INVENTORY_SUCCESS, payload };
};

export const getInventoryListFailed = payload => {
    return { type: FETCH_INVENTORY_FAILED, payload };
};

export const getInventoryListReset = payload => {
    return { type: FETCH_INVENTORY_RESET, payload };
};