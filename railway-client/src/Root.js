import React from 'react';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import createSagaMiddleware from 'redux-saga';
import { createLogger } from 'redux-logger';

import reducers from './reducers';
import registerSagasWithMiddleware from './sagas';

const loggerMiddleware = createLogger();
const sagaMiddleware = createSagaMiddleware();
export const store = createStore(
    reducers,
    {},
    applyMiddleware(sagaMiddleware, loggerMiddleware)
);
registerSagasWithMiddleware(sagaMiddleware);

export default ({ children }) => {
    return <Provider store={store}>{children}</Provider>;
};
