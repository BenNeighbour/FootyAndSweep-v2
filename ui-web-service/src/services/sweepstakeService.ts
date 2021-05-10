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

import {SweepstakeData} from "../redux/model";
import {Client} from "@stomp/stompjs";
import axios from "axios";
import {eventChannel} from "redux-saga";

export function saveSweepstake(client: Client, payload: SweepstakeData) {
    return eventChannel(emit => {
        client.onConnect = () => {
            client.publish({
                destination: '/sweepstakes/save', body: JSON.stringify(payload)
            });
            client.subscribe("/sweepstake-topic/save", (message: any) => {
                if (JSON.parse(message.body).status !== "PENDING") {
                    emit(JSON.parse(message.body));
                    client.deactivate();
                }
            })
        };

        client.activate();

        return () => {
        };
    })
}


export function getMySweepstakes() {
    /* Get the user id */
    let userId = localStorage.getItem("user_id");

    return axios.get(`http://api.footyandsweep-dev.com:30389/sweepstake/sweepstakes/${userId}`, {withCredentials: true});
}


export function getProfileInfo() {
    return axios.get(`http://api.footyandsweep-dev.com:30389/auth/me/`, {withCredentials: true});
}


export async function checkIsAuthenticated() {
    return await axios.get("http://api.footyandsweep-dev.com:30389/auth/check", {withCredentials: true});
}


export function joinSweepstake(client: Client, payload: String) {
    return eventChannel(emit => {
        client.onConnect = () => {
            client.publish({
                destination: `/sweepstakes/join`,
                body: JSON.stringify({
                    participantId: localStorage.getItem("user_id"),
                    sweepstakeJoinCode: payload,
                    sweepstakeId: "",
                    sweepstakeParticipantId: ""
                })
            });
            client.subscribe("/sweepstake-topic/join", (message: any) => {
                if (JSON.parse(message.body).status !== "PENDING") {
                    emit(JSON.parse(message.body));
                    client.deactivate();
                }
            })
        };

        client.activate();
        return () => {
        };
    });
}

export function buySweepstakeTickets(client: Client, payload: {sweepstake: any, numberOfTickets: number}) {
    return eventChannel(emit => {
        client.onConnect = () => {
            client.publish({
                destination: `/tickets/buy`,
                body: JSON.stringify({
                    ownerId: localStorage.getItem("user_id"),
                    numberOfTickets: payload.numberOfTickets,
                    joinCode: payload.sweepstake.joinCode
                })
            });
            client.subscribe("/ticket-topic/buy", (message: any) => {
                if (JSON.parse(message.body).status !== "PENDING") {
                    emit(JSON.parse(message.body));
                    client.deactivate();
                }
            })
        };

        client.activate();
        return () => {
        };
    });
}
