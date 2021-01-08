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
    const client = new SweepstakeServiceClient('http://localhost:9091', null, null);
    const request = new JoinCode();

    request.setJoincode("sdfsdf");

    client.findSweepstakeByJoinCode(request, {}, (err: any, response: any) => {
        if (response == null) {
            console.log(err);
        } else {
            console.log(response);
        }
    });

}
