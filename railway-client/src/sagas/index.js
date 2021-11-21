import signInSaga from './signIn';
import sessionSaga from './session';
import tokenSaga from './token';
import ItemInventory from './ItemInventory';

/**
 * Add new sagas here
 */
const sagas = [signInSaga, sessionSaga, tokenSaga, ItemInventory];

/**
 * Combine sagas for redux middleware
 */
export default sagaMiddleware => {
    sagas.forEach(saga => {
        sagaMiddleware.run(saga);
    });
};
