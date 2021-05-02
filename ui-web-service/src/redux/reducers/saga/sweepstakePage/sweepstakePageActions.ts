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


import {ActionType, BuyingTickets} from "../../../model";

export const setIsCreatingSweepstake = (state: boolean) => {
    return {
        type: ActionType.SET_IS_SAVING_SWEEPSTAKE,
        payload: {
            creatingSweepstake: state
        }
    }
};

export const setIsJoiningSweepstake = (state: boolean) => {
    return {
        type: ActionType.SET_IS_JOINING_SWEEPSTAKE,
        payload: {
            joiningSweepstake: state
        }
    }
};

export const setIsBuyingTickets = (payload: BuyingTickets) => {
    return {
        type: ActionType.SET_IS_BUYING_SWEEPSTAKE_TICKET,
        payload
    }
};

export const getMySweepstakesAction = (payload: String) => {
    return {
        type: ActionType.GET_MY_SWEEPSTAKES_REQUEST,
        payload
    }
};

export const getProfileInfoAction = () => {
    return {
        type: ActionType.GET_PROFILE_INFO_REQUEST
    }
};
