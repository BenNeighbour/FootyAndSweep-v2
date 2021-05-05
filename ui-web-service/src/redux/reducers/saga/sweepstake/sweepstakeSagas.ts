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

import {call, fork, put, take, takeLatest} from 'redux-saga/effects';
import {ActionType, SweepstakeData} from "../../../model";
import {Client} from "@stomp/stompjs";
import {
    buySweepstakeTickets,
    getMySweepstakes,
    joinSweepstake,
    saveSweepstake,
    getProfileInfo
} from "../../../../services/sweepstakeService";
import {channel} from "redux-saga";

const sweepstakeChannel = channel();

function* saveSweepstakeSaga({payload}: { payload: SweepstakeData }) {
    const client = new Client();
    client.brokerURL = "ws://api.footyandsweep-dev.com:30389/sweepstake-socket/socket";
    const socketChannel = yield call(saveSweepstake, client, payload)

    while (true) {
        try {
            let response = yield take(socketChannel);

            yield put({
                type: response.status === "COMPLETED" ? ActionType.SAVE_SWEEPSTAKE_SUCCESS : ActionType.SAVE_SWEEPSTAKE_ERROR,
                payload: response.payload
            });

            window.location.reload();
        } catch (err) {
            yield put({
                type: ActionType.SAVE_SWEEPSTAKE_ERROR,
                payload: "Hmm, Something's not quite right! Try again later!"
            })
        }
    }
}

function* joinSweepstakeSaga({payload}: { payload: String }) {
    const client = new Client();
    client.brokerURL = "ws://api.footyandsweep-dev.com:30389/sweepstake-socket/socket";
    const socketChannel = yield call(joinSweepstake, client, payload)

    while (true) {
        try {
            let response = yield take(socketChannel);

            yield put({
                type: response.status === "COMPLETED" ? ActionType.JOIN_SWEEPSTAKE_REQUEST : ActionType.JOIN_SWEEPSTAKE_ERROR,
                payload: response.payload
            });
        } catch (err) {
            yield put({
                type: ActionType.JOIN_SWEEPSTAKE_ERROR,
                payload: "Hmm, Something's not quite right! Try again later!"
            })
        }
    }
}

function* buySweepstakeTicketsSaga({payload}: { payload: { sweepstake: any, numberOfTickets: number } }) {
    const client = new Client();
    client.brokerURL = "ws://api.footyandsweep-dev.com:30389/ticket-socket/socket";
    const socketChannel = yield call(buySweepstakeTickets, client, payload)
    while (true) {
        try {
            let response = yield take(socketChannel);

            yield put({
                type: response.status === "COMPLETED" ? ActionType.BUY_SWEEPSTAKE_TICKET_SUCCESS : ActionType.BUY_SWEEPSTAKE_TICKET_FAILED,
                payload: response.payload
            });
        } catch (err) {
            yield put({
                type: ActionType.BUY_SWEEPSTAKE_TICKET_FAILED,
                payload: "Hmm, Something's not quite right! Try again later!"
            })
        }
    }
}

function getMySweepstakesSaga({payload}: { payload: String }) {
    getMySweepstakes().then(value => {
        sweepstakeChannel.put({
            type: ActionType.GET_MY_SWEEPSTAKES_SUCCESS,
            payload: value.data
        })
    }).catch((reason: any) => {
        sweepstakeChannel.put({
            type: ActionType.GET_MY_SWEEPSTAKES_ERROR,
            payload: null
        });
    })
}

function getProfileInfoSaga() {
    getProfileInfo().then(value => {
        sweepstakeChannel.put({
            type: ActionType.GET_PROFILE_INFO_SUCCESS,
            payload: value.data
        })
    }).catch((reason: any) => {
        sweepstakeChannel.put({
            type: ActionType.GET_PROFILE_INFO_ERROR,
            payload: null
        });
    });
}

function* watchSweepstakeChannel() {
    const action = yield take(sweepstakeChannel);
    yield put(action)
}

function* onSaveSweepstakeWatcher() {
    yield takeLatest(ActionType.SAVE_SWEEPSTAKE_REQUEST as any, saveSweepstakeSaga);
}

function* onJoinSweepstakeWatcher() {
    yield takeLatest(ActionType.JOIN_SWEEPSTAKE_REQUEST as any, joinSweepstakeSaga);
}

function* onBuySweepstakeTicketsWatcher() {
    yield takeLatest(ActionType.BUY_SWEEPSTAKE_TICKET_REQUEST as any, buySweepstakeTicketsSaga);
}


function* onGetMySweepstakesWatcher() {
    yield takeLatest(ActionType.GET_MY_SWEEPSTAKES_REQUEST as any, getMySweepstakesSaga);
}

function* onGetProfileDetailsWatcher() {
    yield takeLatest(ActionType.GET_PROFILE_INFO_REQUEST as any, getProfileInfoSaga);
}

let sweepstakeSagas = [
    fork(watchSweepstakeChannel),
    fork(onGetMySweepstakesWatcher),
    fork(onSaveSweepstakeWatcher),
    fork(onJoinSweepstakeWatcher),
    fork(onBuySweepstakeTicketsWatcher),
    fork(onGetProfileDetailsWatcher)
];

export default sweepstakeSagas;