/*
 *   Copyright 2021 FootyAndSweep
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import { createBrowserHistory } from "history";
import * as localforage from "localforage";
import { applyMiddleware, createStore } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import { createLogger } from "redux-logger";
import createSagaMiddleware from 'redux-saga';
import { PersistConfig, persistReducer, persistStore } from "redux-persist";
import rootReducer from "./rootReducer";
import rootSaga from './reducers/saga/rootSaga';

const sagaMiddleware = createSagaMiddleware();

const persistConfig: PersistConfig<any> = {
    key: "root",
    version: 1,
    storage: localforage,
    blacklist: ['loginForm'],
};

const logger = (createLogger as any)();
const history = createBrowserHistory();

const dev = process.env.NODE_ENV === "development";

let middleware = dev ? applyMiddleware(logger, sagaMiddleware) : applyMiddleware(sagaMiddleware);

if (dev) {
    middleware = composeWithDevTools(middleware);
}

const persistedReducer = persistReducer(persistConfig, rootReducer(history));

const configureStore = () => {
    const store = createStore(persistedReducer, middleware);
    const persist = persistStore(store);

    return {
        store, persistor: persist, runSaga: sagaMiddleware.run(rootSaga)
    };
};

export { history };

export default configureStore;