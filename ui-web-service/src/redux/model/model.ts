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

    AUTHENTICATE_OAUTH_SUCCESS = 'action/AUTHENTICATE_OAUTH_SUCCESS',

    /* Signup */
    AUTHENTICATE_SIGNUP_REQUEST = 'action/AUTHENTICATE_SIGNUP_REQUEST',
    AUTHENTICATE_SIGNUP_SUCCESS = 'action/AUTHENTICATE_SIGNUP_SUCCESS',
    AUTHENTICATE_SIGNUP_ERROR = 'action/AUTHENTICATE_SIGNUP_ERROR',

    /* Save Sweepstake */
    SAVE_SWEEPSTAKE_REQUEST = 'action/SAVE_SWEEPSTAKE_REQUEST',
    SAVE_SWEEPSTAKE_SUCCESS = 'action/SAVE_SWEEPSTAKE_SUCCESS',
    SAVE_SWEEPSTAKE_ERROR = 'action/SAVE_SWEEPSTAKE_ERROR',

    /* Sweepstake Page states */
    SET_IS_SAVING_SWEEPSTAKE = 'action/SET_IS_SAVING_SWEEPSTAKE',
    SET_IS_JOINING_SWEEPSTAKE = 'action/SET_IS_JOINING_SWEEPSTAKE',
    SET_IS_BUYING_SWEEPSTAKE_TICKET = 'action/SET_IS_BUYING_SWEEPSTAKE_TICKET',

    GET_MY_SWEEPSTAKES_REQUEST = 'action/GET_MY_SWEEPSTAKES_REQUEST',
    GET_MY_SWEEPSTAKES_SUCCESS = 'action/GET_MY_SWEEPSTAKES_SUCCESS',
    GET_MY_SWEEPSTAKES_ERROR = 'action/GET_MY_SWEEPSTAKES_ERROR',

    SET_IS_LOGGING_IN = 'action/SET_IS_LOGGING_IN',
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
    password: string | null;
    confirmPassword: string | null;
}


enum SweepstakeStatus {
    OPEN,
    ALLOCATED,
    CLOSED
}

enum SweepstakeType {
    Correct_Score_FT,
    Correct_Score_HT
}

export interface SweepstakeData {
    id?: string;
    name: string;
    ownerId: string;
    status?: SweepstakeStatus;
    isPrivate: boolean;
    sweepstakeEventId?: string;
    sweepstakeType?: SweepstakeType;
    minimumPlayers: number;
    minimumPlayerTickets?: number;
    stake: number;
    totalNumberOfTickets?: number;
}

export interface BuyingTickets {
    isBuyingTickets: boolean;
    sweepstake: SweepstakeData | null;
}