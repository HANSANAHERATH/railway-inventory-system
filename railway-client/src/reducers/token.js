import {
    FETCH_REFRESH_TOKEN,
    FETCH_REFRESH_TOKEN_SUCCESS,
    FETCH_REFRESH_TOKEN_FAILED,
    RESET_REFRESH_TOKEN,
} from '../actions/types';

const initialState = {
    loading: false,
    error: undefined,
    token: undefined
};

const tokenReducer = (state = initialState, action) => {
    switch (action.type) {
        case FETCH_REFRESH_TOKEN:
            return {
                ...state,
                loading: true,
            };
        case FETCH_REFRESH_TOKEN_SUCCESS:
            sessionStorage.setItem("token" ,action.payload.token )
            return {
                ...state,
                loading: false,
              //  token: action.payload,
            };
        case FETCH_REFRESH_TOKEN_FAILED:
            return {
                ...state,
                loading: false,
                error:
                    'Request Token Failed',
            };
        case RESET_REFRESH_TOKEN:
            return {
                ...state,
                loading: true,
                error: undefined,
                token: undefined,
            };

        default:
            return state;
    }
};

export default tokenReducer;

