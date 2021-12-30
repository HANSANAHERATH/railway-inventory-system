import {
    FETCH_DISTRICT,
    FETCH_DISTRICT_SUCCESS,
    FETCH_DISTRICT_FAILED,
    FETCH_SESSIONS,
    FETCH_SESSIONS_SUCCESS,
    FETCH_SESSIONS_FAILED,
    NOTIFY_ALL,
    NOTIFY_ALL_SUCCESS,
    NOTIFY_ALL_FAILED,
    CANCEL_SESSION,
    CANCEL_SESSION_SUCCESS,
    CANCEL_SESSION_FAILED,
    FETCH_CANCELLED_SESSIONS,
    FETCH_CANCELLED_SESSIONS_SUCCESS,
    FETCH_CANCELLED_SESSIONS_FAILED,
    CANCEL_SESSION_RESET,

    FETCH_UNIT_TYPE,
    FETCH_UNIT_TYPE_SUCCESS,
    FETCH_UNIT_TYPE_FAILED,
    SUBMIT_FORM_ITEM,
    SUBMIT_FORM_ITEM_SUCCESS,
    SUBMIT_FORM_ITEM_FAILED,
    UPDATE_FORM_ITEM,
    UPDATE_FORM_ITEM_SUCCESS,
    UPDATE_FORM_ITEM_FAILED,
    RESET_FORM_ITEM,
    RESET_FORM_ITEM_SUCCESS,
    FETCH_ITEMS,
    FETCH_ITEMS_SUCCESS,
    FETCH_ITEMS_FAILED,
    FETCH_ITEMS_RESET,
    DELETE_FORM_ITEM,
    DELETE_FORM_ITEM_SUCCESS,
    DELETE_FORM_ITEM_FAILED,
    FETCH_CATEGORY,
    FETCH_CATEGORY_SUCCESS,
    FETCH_CATEGORY_FAILED,
    FETCH_CATEGORY_RESET,
} from './types';

export const fetchDistricts = payload => {
    return { type: FETCH_DISTRICT, payload };
};

export const fetchDistrictSuccess = payload => {
    return { type: FETCH_DISTRICT_SUCCESS, payload };
};

export const fetchDistrictFailed = payload => {
    return { type: FETCH_DISTRICT_FAILED, payload };
};

export const fetchSessions = payload => {
    return { type: FETCH_SESSIONS, payload };
};

export const notifyAll = payload => {
    return { type: NOTIFY_ALL, payload };
};

export const notifyAllSuccess = payload => {
    return { type: NOTIFY_ALL_SUCCESS, payload };
};

export const notifyAllFailed = payload => {
    return { type: NOTIFY_ALL_FAILED, payload };
};

export const fetchSessionsSuccess = payload => {
    return { type: FETCH_SESSIONS_SUCCESS, payload };
};

export const fetchSessionsFailed = payload => {
    return { type: FETCH_SESSIONS_FAILED, payload };
};




/** Cancel a Sessions */
export const cancelSession = payload => {
    return { type: CANCEL_SESSION, payload };
};

export const cancelSessionSuccess = payload => {
    return { type: CANCEL_SESSION_SUCCESS, payload };
};

export const cancelSessionFailed = payload => {
    return { type: CANCEL_SESSION_FAILED, payload };
};

export const cancelSessionReset = payload => {
    return { type: CANCEL_SESSION_RESET, payload };
};

/** Cancelled Sessions List */
export const fetchCancelledSessions = payload => {
    return { type: FETCH_CANCELLED_SESSIONS, payload };
};

export const fetchCancelledSessionsSuccess = payload => {
    return { type: FETCH_CANCELLED_SESSIONS_SUCCESS, payload };
};

export const fetchCancelledSessionsFailed = payload => {
    return { type: FETCH_CANCELLED_SESSIONS_FAILED, payload };
};


/***--------------------------- */


/** Fetch vaccine typs */
export const fetchUnitTyps = payload => {
    return { type: FETCH_UNIT_TYPE, payload };
};

export const fetchUnitTypesSuccess = payload => {
    return { type: FETCH_UNIT_TYPE_SUCCESS, payload };
};

export const fetchUnitTypsFailed = payload => {
    return { type: FETCH_UNIT_TYPE_FAILED, payload };
};

// Create
export const submitFormItemCreate = payload => {
    return { type: SUBMIT_FORM_ITEM, payload };
};

export const submitFormItemCreateSuccess = payload => {
    return { type: SUBMIT_FORM_ITEM_SUCCESS, payload };
};

export const submitFormItemCreateFailed = payload => {
    return { type: SUBMIT_FORM_ITEM_FAILED, payload };
};


// Update
export const submitFormItemUpdate = payload => {
    return { type: UPDATE_FORM_ITEM, payload };
};

export const submitFormItemUpdateSuccess = payload => {
    return { type: UPDATE_FORM_ITEM_SUCCESS, payload };
};

export const submitFormItemUpdateFailed = payload => {
    return { type: UPDATE_FORM_ITEM_FAILED, payload };
};

// Reset

export const restFormItemSuccess = payload => {
    return { type: RESET_FORM_ITEM_SUCCESS, payload };
};

export const resetItemCreateForm = payload => {
    return { type: RESET_FORM_ITEM, payload };
};

//fetch items
export const fetchItems = payload => {
    return { type: FETCH_ITEMS, payload };
};

export const fetchItemsSuccess = payload => {
    return { type: FETCH_ITEMS_SUCCESS, payload };
};

export const fetchItemsFailed = payload => {
    return { type: FETCH_ITEMS_FAILED, payload };
};

export const fetchItemsReset = payload => {
    return { type: FETCH_ITEMS_RESET, payload };
};


// Delete
export const submitFormItemRemove = payload => {
    return { type: DELETE_FORM_ITEM, payload };
};

export const submitFormItemRemoveSuccess = payload => {
    return { type: DELETE_FORM_ITEM_SUCCESS, payload };
};

export const submitFormItemRemoveFailed = payload => {
    return { type: DELETE_FORM_ITEM_FAILED, payload };
};

//fetch item category

// Delete
export const fetchItemCategory = payload => {
    return { type: FETCH_CATEGORY, payload };
};

export const fetchItemCategorySuccess = payload => {
    return { type: FETCH_CATEGORY_SUCCESS, payload };
};

export const fetchItemCategoryFailed = payload => {
    return { type: FETCH_CATEGORY_FAILED, payload };
};

export const fetchItemCategoryRest = payload => {
    return { type: FETCH_CATEGORY_RESET, payload };
};