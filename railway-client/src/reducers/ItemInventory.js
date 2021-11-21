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
    lookups: {
        data: [],
    },
    confirmation: {
        payload: {},
        error: undefined,
    },
    inventory: {
        balanceDto: {
            balance: 0,
            totalQuantity: 0,
        },
        data: []
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
                lookups: {
                    ...state.lookups,
                    data: action?.payload?.body,
                }
            };
        case FETCH_LOOKUP_ITEMS_FAILED:
            return {
                ...state,
                loading: false,
                error: 'Fetch items failed.',
                lookups: {
                    ...state.lookups,
                    data: [],
                }
            };
        case FETCH_LOOKUP_ITEMS_RESET:
            return {
                ...state,
                loading: false,
                error: undefined,
                lookups: {
                    ...state.lookups,
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
            return {
                ...state,
                loading: false,
                inventory: {
                    ...state.inventory,
                    balanceDto: action?.payload?.body?.balanceDto,
                    data: action?.payload?.body?.getAllInventoryResponseDtos,
                }
            };
        case FETCH_INVENTORY_FAILED:
            return {
                ...state,
                loading: false,
                error: 'Fetch items failed.',
                inventory: {
                    ...state.inventory,
                    balanceDto: {
                        balance: 0,
                        totalQuantity: 0,
                    },
                    data: [],
                }
            };
        case FETCH_INVENTORY_RESET:
            return {
                ...state,
                loading: false,
                error: undefined,
                inventory: {
                    ...state.inventory,
                    balanceDto: {
                        balance: 0,
                        totalQuantity: 0,
                    },
                    data: [],
                }
            };
        default:
            return state;
    }
};

export default ItemInventoryReducer;
