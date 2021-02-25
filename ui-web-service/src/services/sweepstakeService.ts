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

import {ActionType, SweepstakeData} from "../redux/model";
import {Client} from "@stomp/stompjs";

export function saveSweepstake(client: Client, sweepstakeChannel: any, payload: SweepstakeData) {
    client.onConnect = () => {
        client.publish({
            destination: '/sweepstakes/save', body: JSON.stringify(payload)
        });

        client.subscribe("/sweepstake-topic/save", (message: any) => {
            if (JSON.parse(message.body).status !== "PENDING") {
                sweepstakeChannel.put({
                    type: JSON.parse(message.body).status === "COMPLETED" ? ActionType.SAVE_SWEEPSTAKE_SUCCESS : ActionType.SAVE_SWEEPSTAKE_ERROR,
                    payload: JSON.parse(message.body)
                })
            }
        });
    };
    client.onDisconnect = () => {
        sweepstakeChannel.put({
            type: ActionType.SAVE_SWEEPSTAKE_ERROR,
            payload: "Sorry, something went wrong on our end. Please try again later."
        })
    }

    client.activate();
}