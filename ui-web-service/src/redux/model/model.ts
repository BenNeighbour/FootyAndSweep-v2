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

export interface Action<T> {
    type: ActionType;
    payload: T;
}

export enum ActionType {
    /* Login */
    AUTHENTICATE_LOGIN_REQUEST = 'action/AUTHENTICATE_LOGIN_REQUEST',
    AUTHENTICATE_LOGIN_SUCCESS = 'action/AUTHENTICATE_LOGIN_SUCCESS',
    AUTHENTICATE_LOGIN_ERROR = 'action/AUTHENTICATE_LOGIN_ERROR',

    /* Signup */
    AUTHENTICATE_SIGNUP_REQUEST = 'action/AUTHENTICATE_SIGNUP_REQUEST',
    AUTHENTICATE_SIGNUP_SUCCESS = 'action/AUTHENTICATE_SIGNUP_SUCCESS',
    AUTHENTICATE_SIGNUP_ERROR = 'action/AUTHENTICATE_SIGNUP_ERROR'
}

export interface UserData {
    id: string | null;
    username: string | null;
    credits: number | null;
}

export interface LoginData {
    email: string | null;
    password: string | null;
}

export interface SignupData {
    username: string | null;
    email: string | null;
    dateOfBirth: Date | null;
    password: string | null;
    confirmPassword: string | null;
}