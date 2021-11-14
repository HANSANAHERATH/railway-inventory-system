import {
    FETCH_LOCATION,
    FETCH_LOCATION_SUCCESS,
    FETCH_LOCATION_FAILED,
    RESET_LOCATION,
} from './types';

export const fetchlocationData = payload => {
    return { type: FETCH_LOCATION, payload };
};

export const fetchlocationDataSuccess = payload => {
    return { type: FETCH_LOCATION_SUCCESS, payload };
};

export const fetchlocationDataFailed = payload => {
    return { type: FETCH_LOCATION_FAILED, payload };
};

export const resetFetchlocationData = payload => {
    return { type: RESET_LOCATION, payload };
};