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

import {handleErrors} from "./CommonsService";
import {SweepstakeServiceClient} from "../client/SweepstakeServiceServiceClientPb";
import {JoinCode} from "../client/SweepstakeService_pb";

const client = new SweepstakeServiceClient(`${process.env.REACT_APP_API_GATEWAY_SERVICE}`, null, {withCredentials: true});

export const joinSweepstake = (action: any) => {
    const request = new JoinCode();

    request.setJoincode("sdfsdf");

    client.findSweepstakeByJoinCode(request, {}, (error, response) => {
        handleErrors(error);
        console.log(response, error);
    });
}