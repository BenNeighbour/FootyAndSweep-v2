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

import createReducer from "../../createReducer";
import {Action, ActionType, SweepstakeData} from "../../../model";

export interface SweepstakeReducerType {
    sweepstake?: SweepstakeData;
    isLoading: boolean;
    error?: string;
}

const defaultSweepstakeState: SweepstakeReducerType = {
    sweepstake: undefined,
    isLoading: false,
    error: undefined,
}

export const sweepstakeReducer = createReducer<SweepstakeReducerType>(defaultSweepstakeState, {
    [ActionType.SAVE_SWEEPSTAKE_REQUEST](state: SweepstakeReducerType) {
        return {
            ...state,
            isLoading: true,
        };
    },

    [ActionType.SAVE_SWEEPSTAKE_SUCCESS](state: SweepstakeReducerType) {
        console.log(state);
        return {
            ...state,
            sweepstake: state.sweepstake,
            isLoading: false,
            error: null
        };
    },

    [ActionType.SAVE_SWEEPSTAKE_ERROR](state: SweepstakeReducerType, action: Action<number>) {
        return {
            ...state,
            isLoading: false,
            error: action.payload,
        };
    },


    [ActionType.JOIN_SWEEPSTAKE_REQUEST](state: SweepstakeReducerType) {
        return {
            ...state,
            isLoading: true,
        };
    },

    [ActionType.JOIN_SWEEPSTAKE_SUCCESS](state: SweepstakeReducerType) {
        return {
            ...state,
            sweepstake: state.sweepstake,
            isLoading: false,
            error: null
        };
    },

    [ActionType.JOIN_SWEEPSTAKE_ERROR](state: SweepstakeReducerType, action: Action<number>) {
        return {
            ...state,
            isLoading: false,
            error: action.payload,
        };
    },
});