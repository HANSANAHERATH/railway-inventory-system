import { combineReducers } from 'redux';
import signinReducer from './signin';
import goodsReducer from './goods';
import tokenReducer from './token';
import ItemInventoryReducer from './ItemInventory';

/**
 * Redux Store
 */

// list the reducers list
export default combineReducers({
    signin: signinReducer,
    goods: goodsReducer,
    token: tokenReducer,
    itemInventory: ItemInventoryReducer,
});
