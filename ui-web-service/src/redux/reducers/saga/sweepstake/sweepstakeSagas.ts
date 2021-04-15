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

import {channel} from 'redux-saga';
import {fork, put, take, takeLatest} from 'redux-saga/effects';
import {ActionType, SweepstakeData} from "../../../model";
import {Client} from "@stomp/stompjs";
import {getMySweepstakes, saveSweepstake} from "../../../../services/sweepstakeService";

const sweepstakeChannel = channel();
const client = new Client();
client.brokerURL = "ws://api.footyandsweep-dev.com:30389/sweepstake-socket/socket";

function* saveSweepstakeSaga({payload}: { payload: SweepstakeData }) {
    try {
        saveSweepstake(client, sweepstakeChannel, payload);
    } catch (error) {
        yield put({
            type: ActionType.SAVE_SWEEPSTAKE_ERROR,
            payload: "Hmm, Something's not quite right! Try again later!"
        })
    }
}

function* getMySweepstakesSaga({payload}: { payload: String }) {
    getMySweepstakes(sweepstakeChannel)
}

function* watchSweepstakeChannel() {
    const action = yield take(sweepstakeChannel);
    yield put(action)
}

function* onSaveSweepstakeWatcher() {
    yield takeLatest(ActionType.SAVE_SWEEPSTAKE_REQUEST as any, saveSweepstakeSaga);
}

function* onGetMySweepstakesWatcher() {
    yield takeLatest(ActionType.GET_MY_SWEEPSTAKES_REQUEST as any, getMySweepstakesSaga);
}

let sweepstakeSagas = [
    fork(onSaveSweepstakeWatcher),
    fork(onGetMySweepstakesWatcher),
    fork(watchSweepstakeChannel)
];

export default sweepstakeSagas;