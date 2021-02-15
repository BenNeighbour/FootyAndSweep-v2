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

import {grpc} from "@improbable-eng/grpc-web";
import {JoinCode} from "../client/SweepstakeService_pb";
import {SweepstakeService} from "../client/SweepstakeService_pb_service";

const transportConfig = grpc.CrossBrowserHttpTransport({withCredentials: true});
grpc.setDefaultTransport(transportConfig);

export const joinSweepstake = (action: any) => {
    /* TODO: Fill this in with the action payload data */
    const joinCode = new JoinCode();
    joinCode.setJoincode("sdfsdf");

    grpc.invoke(SweepstakeService.findSweepstakeByJoinCode, {
        request: joinCode,
        host: `http://api.footyandsweep-dev.com:32427`,
        onEnd: (code: grpc.Code, msg: string | undefined, trailers: grpc.Metadata) => {
            console.log(msg)
        }
    })
}
