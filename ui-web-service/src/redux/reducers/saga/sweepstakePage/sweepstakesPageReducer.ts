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


import {Action, ActionType, SweepstakeData} from "../../../model";
import createReducer from "../../createReducer";
import {SweepstakeReducerType} from "../sweepstake";

export interface SweepstakesPageReducerType {
    joiningSweepstake: boolean;
    creatingSweepstake: boolean;

    buyingTickets: {
        isBuyingTickets: boolean;
        sweepstake: SweepstakeData | null;
    };

    successMessage: string | null;
    failedMessage: string | null;
    isLoading: boolean;
}

const defaultSweepstakePageState: SweepstakesPageReducerType = {
    joiningSweepstake: false,
    creatingSweepstake: false,
    buyingTickets: {
        isBuyingTickets: false,
        sweepstake: null
    },

    successMessage: null,
    failedMessage: null,
    isLoading: true,
}

export const yourSweepstakesReducer = createReducer<SweepstakesPageReducerType>(defaultSweepstakePageState, {
    [ActionType.SET_IS_SAVING_SWEEPSTAKE](state: SweepstakesPageReducerType, action: Action<any>) {
        return {
            ...state,
            isLoading: false,
            creatingSweepstake: action.payload.creatingSweepstake
        };
    },

    [ActionType.SET_IS_JOINING_SWEEPSTAKE](state: SweepstakesPageReducerType, action: Action<any>) {
        return {
            ...state,
            isLoading: false,
            joiningSweepstake: action.payload.joiningSweepstake
        };
    },

    [ActionType.SET_IS_BUYING_SWEEPSTAKE_TICKET](state: SweepstakesPageReducerType, action: Action<any>) {
        return {
            ...state,
            isLoading: false,
            buyingTickets: {

            }
        };
    },

    [ActionType.SAVE_SWEEPSTAKE_SUCCESS](state: SweepstakeReducerType, action: Action<any>) {
        return {
            ...state,
            isCreatingSweepstake: false,
            isLoading: false,
            successMessage: "Sweepstake Created!"
        };
    },

    [ActionType.SAVE_SWEEPSTAKE_ERROR](state: SweepstakeReducerType, action: Action<number>) {
        return {
            ...state,
            isCreatingSweepstake: true,
            isLoading: false,
            failedMessage: action.payload
        };
    },
});