import {
    FETCH_UNIT_TYPE,
    FETCH_UNIT_TYPE_SUCCESS,
    FETCH_UNIT_TYPE_FAILED,
    SUBMIT_FORM_ITEM_SUCCESS,
    SUBMIT_FORM_ITEM_FAILED,
    UPDATE_FORM_ITEM_SUCCESS,
    UPDATE_FORM_ITEM_FAILED,
    RESET_FORM_ITEM_SUCCESS,
    FETCH_ITEMS,
    FETCH_ITEMS_SUCCESS,
    FETCH_ITEMS_FAILED,
    DELETE_FORM_ITEM,
    DELETE_FORM_ITEM_SUCCESS,
    DELETE_FORM_ITEM_FAILED,
} from '../actions/types';

const initialState = {
    loading: false,
    error: undefined,

    unitTypes:{
        data: [],
        loading: false,
    },
    confirmation: {
        payload: {},
        error: undefined,
    },
    items: {
        data: []
    }
};

const sessionReducer = (state = initialState, action) => {
    switch (action.type) {
        case FETCH_UNIT_TYPE:
            return {
                ...state,
                loading :true,
            };
        case FETCH_UNIT_TYPE_SUCCESS:
            return {
                ...state,
                loading: false,
                unitTypes: {
                    ...state.unitTypes,
                    data : action.payload.body,
                    loading: false,
                }
            };
        case FETCH_UNIT_TYPE_FAILED:
            return {
                ...state,
                loading: false,
                error: "Fetch unit types failed.",
                unitTypes: {
                    ...state.unitTypes,
                    data : [],
                    loading: false,
                }
        };

        case SUBMIT_FORM_ITEM_SUCCESS:
            return {
                ...state,
                loading: false,
                error: undefined,
                confirmation: {
                    ...state.confirmation,
                    loading: false,
                    payload: action.payload,
                    error: undefined,
                },
            };
        case SUBMIT_FORM_ITEM_FAILED:
            return {
                ...state,
                loading: false,
                error: action.payload.error_msg,
                confirmation: {
                    ...state.confirmation,
                    loading: false,
                    error: action.payload.error_msg,
                    payload: {},
                },
            };

        case UPDATE_FORM_ITEM_SUCCESS:
            return {
                ...state,
                loading: false,
                confirmation: {
                    ...state.confirmation,
                    loading: false,
                    payload: action.payload,
                    error: undefined,
                },
            };
        case UPDATE_FORM_ITEM_FAILED:
            return {
                ...state,
                confirmation: {
                    ...state.confirmation,
                    loading: false,
                    error: action.payload.error_msg,
                    payload: action.payload,
                },
            };

        case RESET_FORM_ITEM_SUCCESS:
            return {
                ...state,
                confirmation: {
                    ...state.confirmation,
                    error:undefined,
                    loading: false,
                    payload: '',
                },
            };
        
        case FETCH_ITEMS:
            return {
                ...state,
                loading :true,
            };
        case FETCH_ITEMS_SUCCESS:
            return {
                ...state,
                loading: false,
                items: {
                    ...state.items,
                    data : action.payload.body,
                }
            };
        case FETCH_ITEMS_FAILED:
            return {
                ...state,
                loading: false,
                error: "Fetch items failed.",
                items: {
                    ...state.items,
                    data : [],
                }
        };

        case DELETE_FORM_ITEM:
            return {
                ...state,
                loading :true,
            };
        case DELETE_FORM_ITEM_SUCCESS:
            return {
                ...state,
                loading: false,
                error: undefined,
                confirmation: {
                    ...state.confirmation,
                    loading: false,
                    payload: action.payload,
                },
            };
        case DELETE_FORM_ITEM_FAILED:
            return {
                ...state,
                confirmation: {
                    ...state.confirmation,
                    loading: false,
                    payload: action.payload,
                },
            };
        default:
            return state;
    }
};

export default sessionReducer;
