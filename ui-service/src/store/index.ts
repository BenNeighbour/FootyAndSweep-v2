/*
 *   Copyright 2020 FootyAndSweep
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

import { createStore, applyMiddleware } from 'redux';
import { createBrowserHistory } from 'history';
import { routerMiddleware } from 'connected-react-router';
import rootReducer from './RootReducer';
import createSagaMiddleware from 'redux-saga';
import joinSweepstakeSaga from "./sagas/sweepstake/SweepstakeSaga";


export const history = createBrowserHistory();

export const initialize = () => {
    const sagaMiddleware = createSagaMiddleware();
    const middlewares = [routerMiddleware(history), sagaMiddleware]

    const initialState = {};
    const store = createStore(rootReducer(history), initialState, applyMiddleware(...middlewares));

    sagaMiddleware.run(joinSweepstakeSaga);
    return store;
}

