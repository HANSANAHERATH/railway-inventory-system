import signInSaga from './signIn';
import GoodsSaga from './goods';
import tokenSaga from './token';
import ItemInventory from './ItemInventory';

/**
 * Add new sagas here
 */
const sagas = [signInSaga, GoodsSaga, tokenSaga, ItemInventory];

/**
 * Combine sagas for redux middleware
 */
export default sagaMiddleware => {
    sagas.forEach(saga => {
        sagaMiddleware.run(saga);
    });
};
