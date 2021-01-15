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

const {SweepstakeServiceClient} = require('../client/SweepstakeService_grpc_web_pb');
const {JoinCode} = require('../client/SweepstakeService_pb');

export const joinSweepstake = (action: any) => {
    const client = new SweepstakeServiceClient('http://api.footyandsweep-dev.com:31175', null, {
        withCredentials: true
    });
    const request = new JoinCode();

    request.setJoincode("sdfsdf");

    client.findSweepstakeByJoinCode(request, {}, (response: any, err: any) => {
        /* TODO: Add this as an interceptor on ALL grpc requests rather than hardcoding it into each service method */
        if (response.code === 16) {
            console.log("Unauthorized");
            window.location.replace("http://api.footyandsweep-dev.com:30876/oauth2/authorize/google?redirect_uri=http://www.footyandsweep-dev.com:3000/home")
        }
    });

}
