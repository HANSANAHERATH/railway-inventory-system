import {
    FETCH_LOOKUP_ITEMS,
    FETCH_LOOKUP_ITEMS_SUCCESS,
    FETCH_LOOKUP_ITEMS_FAILED,
    FETCH_LOOKUP_ITEMS_RESET,
    CREATE_INVENTORY_SUCCESS,
    CREATE_INVENTORY_FAILED,
    CREATE_INVENTORY_RESET,
    FETCH_INVENTORY,
    FETCH_INVENTORY_SUCCESS,
    FETCH_INVENTORY_FAILED,
    FETCH_INVENTORY_RESET,
} from '../actions/types';

const initialState = {
    loading: false,
    error: undefined,
    nameLookups: {
        data: [],
    },
    confirmation: {
        payload: {},
        error: undefined,
    },
    inventory: {
        content: [],
        page: 0,
        pageSize: 0,
        totalCount: 0,
        totalPage: 0,
    }

};

const ItemInventoryReducer = (state = initialState, action) => {
    switch (action.type) {
        case FETCH_LOOKUP_ITEMS:
            return {
                ...state,
                loading: true,
            };
        case FETCH_LOOKUP_ITEMS_SUCCESS:
            return {
                ...state,
                loading: false,
                nameLookups: {
                    ...state.nameLookups,
                    data: action?.payload?.body,
                }
            };
        case FETCH_LOOKUP_ITEMS_FAILED:
            return {
                ...state,
                loading: false,
                error: 'Fetch items failed.',
                nameLookups: {
                    ...state.nameLookups,
                    data: [],
                }
            };
        case FETCH_LOOKUP_ITEMS_RESET:
            return {
                ...state,
                loading: false,
                error: undefined,
                nameLookups: {
                    ...state.nameLookups,
                    data: [],
                }
            };

        case CREATE_INVENTORY_SUCCESS:
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
        case CREATE_INVENTORY_FAILED:
            return {
                ...state,
                loading: false,
                error: action.payload.statusMessage,
                confirmation: {
                    ...state.confirmation,
                    loading: false,
                    error: action.payload.statusMessage,
                    payload: {},
                },
            };
        case CREATE_INVENTORY_RESET:
            return {
                ...state,
                loading: false,
                error: undefined,
                confirmation: {
                    ...state.confirmation,
                    loading: false,
                    payload: {},
                },
            };

        case FETCH_INVENTORY:
            return {
                ...state,
                loading: true,
            };
        case FETCH_INVENTORY_SUCCESS:
            console.log(action?.payload)
            return {
                ...state,
                loading: false,
                inventory: {
                    ...state.inventory,
                    content: action?.payload?.body?.content,
                    page: action?.payload?.body?.page,
                    pageSize: action?.payload?.body?.pageSize,
                    totalCount: action?.payload?.body?.totalCount,
                    totalPage: action?.payload?.body?.totalPage,
                }
            };
        case FETCH_INVENTORY_FAILED:
            return {
                ...state,
                loading: false,
                error: 'Fetch items failed.',
                inventory: {
                    ...state.inventory,
                    content: [],
                    page: 0,
                    pageSize: 0,
                    totalCount: 0,
                    totalPage: 0,
                }
            };
        case FETCH_INVENTORY_RESET:
            return {
                ...state,
                loading: false,
                error: undefined,
                inventory: {
                    ...state.inventory,
                    content: [],
                    page: 0,
                    pageSize: 0,
                    totalCount: 0,
                    totalPage: 0,
                }
            };
        default:
            return state;
    }
};

export default ItemInventoryReducer;
