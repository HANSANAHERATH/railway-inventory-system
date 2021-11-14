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
        /* case FETCH_CENTERS_SUCCESS:
            return {
                ...state,
                loading: false,
                centers: {
                    ...state.centers,
                    loading: false,
                    data: action.payload.organisationUnits,
                },
            };
        case FETCH_CENTERS_FAILED:
            return {
                ...state,
                centers: {
                    ...state.centers,
                    loading: false,
                    error:
                        'Error occurred when fetching the vaccination centers. Please refresh and try again',
                },
            };
        case FETCH_DISTRICT_SUCCESS:
            return {
                ...state,
                loading: false,
                districts: {
                    ...state.districts,
                    loading: false,
                    data: action.payload.data,
                },
            };
        case FETCH_DISTRICT_FAILED:
            return {
                ...state,
                districts: {
                    ...state.districts,
                    loading: false,
                    data: [],
                    error: 'Error occurred when fetching the Districts. Please refresh and try again',
                },
            };
        case FETCH_SESSIONS_SUCCESS:
            return {
                ...state,
                loading: false,
                sessions: {
                    ...state.sessions,
                    loading: false,
                    data: action.payload.data,
                    error: undefined,
                },
            };
        case FETCH_SESSIONS_FAILED:
            return {
                ...state,
                sessions: {
                    ...state.sessions,
                    loading: false,
                    error:
                        'Error occurred when fetching the sessions. Please refresh and try again',
                    data:[],
                },
            };
        
        
        
        

        case NOTIFY_ALL:
            return {
                ...state,
                loading :true,
            };
        case NOTIFY_ALL_SUCCESS:
            return {
                ...state,
                loading: false,
                notify: {
                    ...state.notify,
                    data : action.payload,
                    loading: true,
                }
            };
        case NOTIFY_ALL_FAILED:
            return {
                ...state,
                loading: false,
                notify: {
                    ...state.notify,
                    data : action.payload,
                    loading: true,
                }
        };
        case NOTIFY_ALL_RESET:
            return {
                ...state,
                loading: false,
                notify: {
                    ...state.notify,
                    data : {},
                    loading: false,
                }
        };
        case DISABLED_NOTIFY_BUTTON:
            return {
                ...state,
                disabledNotifyButton: {
                    ...state.disabledNotifyButton,
                    isDisabled : action.payload,
                    error : "",
                    loading: false,
                }
        };
        case CANCEL_SESSION:
            return {
                ...state,
                loading :true,
            };
        case CANCEL_SESSION_SUCCESS:
            return {
                ...state,
                loading: false,
                cancelSession: {
                    ...state.cancelSession,
                    data : action.payload,
                    loading: true,
                }
            };
        case CANCEL_SESSION_FAILED:
            return {
                ...state,
                loading: false,
                cancelSession: {
                    ...state.cancelSession,
                    data : action.payload,
                    loading: true,
                }
        };
        case CANCEL_SESSION_RESET:
            return {
                ...state,
                loading: false,
                cancelSession: {
                    ...state.cancelSession,
                    data : {},
                    loading: false,
                }
        };
        
 */
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
