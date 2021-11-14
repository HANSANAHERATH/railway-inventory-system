import {
    FETCH_DASHBOARD,
    FETCH_DASHBOARD_SUCCESS,
    FETCH_DASHBOARD_FAILED,
    RESET_DASHBOARD,
} from '../actions/types';

const initialState = {
    loading: false,
    error: undefined,
    data: []
};

const dashboardReducer = (state = initialState, action) => {
    switch (action.type) {
        case FETCH_DASHBOARD:
            return {
                ...state,
                loading: true,
            };
        case FETCH_DASHBOARD_SUCCESS:
            return {
                ...state,
                loading: false,
                data: action.payload.data,
            };
        case FETCH_DASHBOARD_FAILED:
            return {
                ...state,
                loading: false,
                error: action.payload.error_msg,
            };
        case RESET_DASHBOARD:
            return {
                ...state,
                loading: false,
                error: undefined,
                data: [],
            };

        default:
            return state;
    }
};

export default dashboardReducer;

