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

import {Action, ActionType} from "../../../model";
import createReducer from "../../createReducer";


/* Types for the reducer to modify (the payload that comes in through the saga) */
export interface AuthenticationReducerType {
    username: string;
    password: string;
    isLoading: boolean;
    error?: string;
}

const defaultState: AuthenticationReducerType = {
    username: '',
    password: '',
    isLoading: false,
    error: undefined,
}


/* Authentication Reducer cases */
export const authenticationReducer = createReducer<AuthenticationReducerType>(defaultState, {
    [ActionType.AUTHENTICATE_LOGIN_REQUEST](state: AuthenticationReducerType) {
        return {
            ...state,
            isLoading: true,
        };
    },

    [ActionType.AUTHENTICATE_LOGIN_SUCCESS](state: AuthenticationReducerType) {
        return {
            ...state,
            isLoading: false,
            error: null
        };
    },

    [ActionType.AUTHENTICATE_LOGIN_ERROR](state: AuthenticationReducerType, action: Action<number>) {
        return {
            ...state,
            loading: false,
            error: action.payload,
        };
    },
});