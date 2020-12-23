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

import {call, put, takeLatest} from 'redux-saga/effects';
import * as testService from "../../../services/TestService";

function* fetchTest(action: any) {
    try {
        const user = yield call(testService.getTestThing, action.payload.test);
        yield put({type: "TEST_FETCH_SUCCEEDED", user: user});
    } catch (e) {
        yield put({type: "TEST_FETCH_FAILED", message: e.message});
    }
}

function* testSaga() {
    yield takeLatest("TEST_FETCH_PENDING", fetchTest);
}

export default testSaga;