import { createStore, applyMiddleware } from 'redux';
import { createBrowserHistory } from 'history';
import { routerMiddleware } from 'connected-react-router';
import rootReducer from './RootReducer';
import createSagaMiddleware from 'redux-saga';
import testSaga from "./sagas/test/TestSaga";

export const history = createBrowserHistory();

export const initialize = () => {
    const sagaMiddleware = createSagaMiddleware();
    const middlewares = [routerMiddleware(history), sagaMiddleware]

    const initialState = {};
    const store = createStore(rootReducer(history), initialState, applyMiddleware(...middlewares));

    sagaMiddleware.run(testSaga);
    return store;
}

