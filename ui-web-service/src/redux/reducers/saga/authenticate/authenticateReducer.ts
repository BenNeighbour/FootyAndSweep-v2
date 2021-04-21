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
export interface LoginAuthenticationReducerType {
    username: string;
    password: string;
    isLoading: boolean;
    error?: string;
}

const defaultLoginState: LoginAuthenticationReducerType = {
    username: '',
    password: '',
    isLoading: false,
    error: undefined,
}


/* Types for the reducer to modify (the payload that comes in through the saga) */
export interface SignupAuthenticationReducerType {
    username: string;
    email: string;
    password: string;
    confirmPassword: string;
    isLoading: boolean;
    error?: string;
}

const defaultSignupState: SignupAuthenticationReducerType = {
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
    isLoading: false,
    error: undefined,
}


/* Authentication Reducer cases */
export const loginAuthenticationReducer = createReducer<LoginAuthenticationReducerType>(defaultLoginState, {
    [ActionType.AUTHENTICATE_LOGIN_REQUEST](state: LoginAuthenticationReducerType) {
        return {
            ...state,
            isLoading: true,
        };
    },

    [ActionType.AUTHENTICATE_LOGIN_SUCCESS](state: LoginAuthenticationReducerType, action: Action<any>) {
        localStorage.setItem("user_id", action.payload.id);

        return {
            ...state,
            isLoading: false,
            error: null
        };
    },

    [ActionType.AUTHENTICATE_OAUTH_SUCCESS](state: LoginAuthenticationReducerType, action: Action<any>) {
        /* Put the user id inside localstorage */
        if (action.payload !== null) {
            localStorage.setItem("user_id", action.payload.toString());
        }

        return {
            ...state,
            isLoading: false,
            error: null
        };
    },

    [ActionType.AUTHENTICATE_LOGIN_ERROR](state: LoginAuthenticationReducerType, action: Action<number>) {
        return {
            ...state,
            isLoading: false,
            error: action.payload,
        };
    },
});

export const signupAuthenticationReducer = createReducer<SignupAuthenticationReducerType>(defaultSignupState, {
    [ActionType.AUTHENTICATE_SIGNUP_REQUEST](state: SignupAuthenticationReducerType) {
        return {
            ...state,
            isLoading: true,
        };
    },

    [ActionType.AUTHENTICATE_SIGNUP_SUCCESS](state: SignupAuthenticationReducerType) {
        return {
            ...state,
            isLoading: false,
            error: null
        };
    },

    [ActionType.AUTHENTICATE_SIGNUP_ERROR](state: SignupAuthenticationReducerType, action: Action<number>) {
        return {
            ...state,
            isLoading: false,
            error: action.payload,
        };
    },
});