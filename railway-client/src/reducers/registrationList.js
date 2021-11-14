import {
    FETCH_REGISTERED_USERS,
    FETCH_REGISTERED_USERS_SUCCESS,
    FETCH_REGISTERED_USERS_FAILED,
    RESET_REGISTERED_USERS,
    FETCH_SELECTED_SESSIONS,
    FETCH_SELECTED_SESSIONS_SUCCESS,
    FETCH_SELECTED_SESSIONS_FAILED,
    FETCH_SELECTED_SESSIONS_RESET,
    UPDATE_STATE_REGISTER,
} from '../actions/types';

const initialState = {
    loading: false,
    error: undefined,
    registeredUsers: {
        data: [],
        error: undefined,
        loading: false,
    },
    sessionsSelected: {
        selected: [],
        loading: false,
        error: undefined,
    }
};

const registrationListReducer = (state = initialState, action) => {
    switch (action.type) {
        case FETCH_REGISTERED_USERS:
            return {
                ...state,
                loading: true,
            };
        case FETCH_REGISTERED_USERS_SUCCESS:
            return {
                ...state,
                loading: false,
                registeredUsers: {
                    ...state.registeredUsers,
                    data: action.payload.data,
                    loading: false
                },
                error: undefined,
            };
        case FETCH_REGISTERED_USERS_FAILED:
            return {
                ...state,
                loading: false,
                registeredUsers: {
                    ...state.registeredUsers,
                    data: [],
                    loading: false
                },
                error: 'Error occurred when fetching the registered users',
            };
        case RESET_REGISTERED_USERS:
            return {
                ...state,
                loading: false,
                registeredUsers: {
                    ...state.registeredUsers,
                    data: [],
                },
                error: undefined,
            };
        case UPDATE_STATE_REGISTER:
            return {
                ...state,
                loading: false,
            };

        /** Selected Session for Dashboard */
        case FETCH_SELECTED_SESSIONS:
            return {
                ...state,
                loading: true,
                sessionsSelected: {
                    ...state.sessions,
                    loading: true,
                }
            };
        case FETCH_SELECTED_SESSIONS_SUCCESS:
            return {
                ...state,
                loading: false,
                sessionsSelected: {
                    ...state.sessions,
                    loading: false,
                    selected: action.payload.data,
                    error: undefined,
                }
            };
        case FETCH_SELECTED_SESSIONS_FAILED:
            return {
                 ...state,
                loading: false,
                sessionsSelected: {
                    ...state.sessions,
                    loading: false,
                    selected: action.payload.data,
                    error: "",
                }
            };
        case FETCH_SELECTED_SESSIONS_RESET:
            return {
                ...state,
                loading: false,
                sessionsSelected: {
                    ...state.sessions,
                    loading: false,
                    selected: [],
                    error: undefined,
                }
            };
        default:
            return state;
    }
};

export default registrationListReducer;
