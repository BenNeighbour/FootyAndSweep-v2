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
import {ActionType, LoginData} from "../../../model";
import axios from "axios";

const loginRequest = (payload: LoginData) => {
    return axios.request({
        method: "post",
        url: "https://api.footyandsweep-dev.com:30389/com.footyandsweep.AuthenticationService/login",
        data: payload,
        withCredentials: true
    });
}

function* loginSaga({payload}: { payload: LoginData }) {
    try {
        let {response} = yield call(loginRequest, payload);

        if (response.status === 200) {
            yield put({type: ActionType.AUTHENTICATE_LOGIN_SUCCESS, payload: response.username});
        } else {
            yield put({type: ActionType.AUTHENTICATE_LOGIN_ERROR, payload: 'error'})
        }
    } catch (error) {
        yield put({type: ActionType.AUTHENTICATE_LOGIN_ERROR, payload: 'error'})
    }
}


function* onLoginSubmitWatcher() {
    yield takeLatest(ActionType.AUTHENTICATE_LOGIN_REQUEST as any, loginSaga);
}

let authenticateSagas = [
    fork(onLoginSubmitWatcher),
];

export default authenticateSagas;