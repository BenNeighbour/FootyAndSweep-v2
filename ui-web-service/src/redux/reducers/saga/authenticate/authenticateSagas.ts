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

import {call, fork, put, takeLatest} from 'redux-saga/effects';
import {ActionType, LoginData, SignupData} from "../../../model";
import Axios from "axios";

const loginRequest = async (payload: LoginData) => {
    try {
        let response = await Axios({
            method: "post",
            url: "http://api.footyandsweep-dev.com:30389/com.footyandsweep.AuthenticationService/login",
            data: payload,
            withCredentials: true
        });

        return response.data;
    } catch (error) {
        return error.response;
    }
}

const signupRequest = async (payload: SignupData) => {
    try {
        let response = await Axios({
            method: "post",
            url: "http://api.footyandsweep-dev.com:30389/com.footyandsweep.AuthenticationService/signup",
            data: payload,
            withCredentials: true
        });

        return response.data;
    } catch (error) {
        return error.response;
    }
}

function* loginSaga({payload}: { payload: LoginData }) {
    try {
        const response = yield call(loginRequest, payload);

        if (response.status === 200) {
            yield put({type: ActionType.AUTHENTICATE_LOGIN_SUCCESS, payload: response.username});
        } else if (response.status === 401) {
            yield put({type: ActionType.AUTHENTICATE_LOGIN_ERROR, payload: "Your Username/Password are invalid!"})
        } else {
            yield put({
                type: ActionType.AUTHENTICATE_LOGIN_ERROR,
                payload: "Hmm, Something's not quite right! Try again later!"
            })
        }
    } catch (error) {
        yield put({
            type: ActionType.AUTHENTICATE_LOGIN_ERROR,
            payload: "Hmm, Something's not quite right! Try again later!"
        })
    }
}

function* signupSaga({payload}: { payload: SignupData }) {
    try {
        let {response} = yield call(signupRequest, payload);

        if (response.status === 200) {
            yield put({type: ActionType.AUTHENTICATE_SIGNUP_SUCCESS, payload: response.username});
        } else {
            yield put({
                type: ActionType.AUTHENTICATE_SIGNUP_ERROR,
                payload: "Hmm, Something's not quite right! Try again later!"
            })
        }
    } catch (error) {
        yield put({
            type: ActionType.AUTHENTICATE_SIGNUP_ERROR,
            payload: "Hmm, Something's not quite right! Try again later!"
        })
    }
}


function* onLoginSubmitWatcher() {
    yield takeLatest(ActionType.AUTHENTICATE_LOGIN_REQUEST as any, loginSaga);
}

function* onSignupSubmitWatcher() {
    yield takeLatest(ActionType.AUTHENTICATE_SIGNUP_REQUEST as any, signupSaga);
}

let authenticateSagas = [
    fork(onLoginSubmitWatcher),
    fork(onSignupSubmitWatcher),
];

export default authenticateSagas;