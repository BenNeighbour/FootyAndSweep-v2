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

import {History} from "history";
import {combineReducers} from "redux";
import {routerReducer, RouterState} from 'react-router-redux';
import {authenticationReducer, AuthenticationReducerType} from "./reducers/saga/authenticate";

export interface RootState {
    loginForm: AuthenticationReducerType
    routerReducer: RouterState,
}

const combinedReducers = (history: History) =>
    combineReducers({
        loginForm: authenticationReducer,
        routerReducer,
    });

export default combinedReducers;