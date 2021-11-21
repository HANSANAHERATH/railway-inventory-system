import {
    FETCH_REGISTERED_USERS,
    FETCH_REGISTERED_USERS_SUCCESS,
    FETCH_REGISTERED_USERS_FAILED,
    RESET_REGISTERED_USERS,
  //  FETCH_SELECTED_SESSIONS,
   // FETCH_SELECTED_SESSIONS_SUCCESS,
   // FETCH_SELECTED_SESSIONS_FAILED,
   // FETCH_SELECTED_SESSIONS_RESET,
    UPDATE_STATE_REGISTER,
} from './types';

export const fetchRegisteredUsers = payload => {
    return { type: FETCH_REGISTERED_USERS, payload };
};

export const fetchRegisteredUsersSuccess = payload => {
    return { type: FETCH_REGISTERED_USERS_SUCCESS, payload };
};

export const fetchRegisteredUsersFailed = payload => {
    return { type: FETCH_REGISTERED_USERS_FAILED, payload };
};

export const fetchRegisteredUsersReset = payload => {
    return { type: RESET_REGISTERED_USERS, payload };
};

export const forceUpdate = payload => {
    return { type: UPDATE_STATE_REGISTER, payload };
};

/** Fetch selected session for dashboard */
/* export const fetchSelectedSession = payload => {
    return { type: FETCH_SELECTED_SESSIONS, payload };
};
 */
/* export const fetchSelectedSessionSuccess = payload => {
    return { type: FETCH_SELECTED_SESSIONS_SUCCESS, payload };
};

export const fetchSelectedSessionFailed = payload => {
    return { type: FETCH_SELECTED_SESSIONS_FAILED, payload };
}; */

/* export const fetchSelectedSessionReset = payload => {
    return { type: FETCH_SELECTED_SESSIONS_RESET, payload };
};
 */