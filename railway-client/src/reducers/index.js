import { combineReducers } from 'redux';
import signinReducer from './signin';
import registrationListReducer from './registrationList';
import sessionReducer from './session';
import tokenReducer from './token';
import dashboardReducer from './dashboard';
import locationReducer from './location';

/**
 * Redux Store
 */

// list the reducers list
export default combineReducers({
    signin: signinReducer,
    registrationList: registrationListReducer,
    session: sessionReducer,
    token: tokenReducer,
    dashboard: dashboardReducer,
    location: locationReducer,
});
