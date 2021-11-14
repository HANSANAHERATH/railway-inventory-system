import {
    FETCH_LOCATION,
    FETCH_LOCATION_SUCCESS,
    FETCH_LOCATION_FAILED,
    RESET_LOCATION,
} from '../actions/types';

const initialState = {
    loading : false,
    location : {
        loading: false,
        error: undefined,
        data: [],
    },
};

const locationReducer = (state = initialState, action) => {
    switch (action.type) {
        case FETCH_LOCATION:
            return {
                ...state,
                loading: true,
                location: {
                    loading: false,
                    error: undefined,
                    data: [],
                }
            };
        case FETCH_LOCATION_SUCCESS:
            return {
                ...state,
                loading: false,
                location: {
                    loading: false,
                    error: undefined,
                    data: action.payload.data,
                } 
            };
        case FETCH_LOCATION_FAILED:
            return {
                ...state,
                loading: false,
                location: {
                    loading: false,
                    error: action.payload.error_msg,
                    data: [],
                }
            };
        case RESET_LOCATION:
            return {
                ...state,
                loading: false,
                location: {
                    loading: false,
                    error: undefined,
                    data: [],
                }
            };

        default:
            return state;
    }
};

export default locationReducer;

