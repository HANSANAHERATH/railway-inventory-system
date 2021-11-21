import { combineReducers } from 'redux';
import signinReducer from './signin';
import sessionReducer from './session';
import tokenReducer from './token';
import ItemInventoryReducer from './ItemInventory';

/**
 * Redux Store
 */

// list the reducers list
export default combineReducers({
    signin: signinReducer,
    session: sessionReducer,
    token: tokenReducer,
    itemInventory: ItemInventoryReducer,
});
