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


import React, {Fragment, FunctionComponent, useState} from 'react';
import "./Switch.scss";

interface OwnProps {
    value: boolean;
    label: string;
    showCurrentStatus?: boolean | false;
    statusText: string;
    falseText: string;
    trueText: string;
}

type Props = OwnProps;

const Switch: FunctionComponent<Props> = (props) => {
    const [isSwitchedOn, setIsSwitchedOn] = useState<boolean>(props.value);

    return (
        <Fragment>
            <span className={"switchLabel"}>{props.label}</span>
            <div className={"switchArea"}>
                <label className={"switch"}>
                    <input onChange={() => setIsSwitchedOn(!isSwitchedOn)} checked={isSwitchedOn} type={"checkbox"}/>
                    <span className={"slider"}/>
                </label>

                <span className={"statusText"}>{props.statusText}&#32;
                    <b>{isSwitchedOn ? props.trueText : props.falseText}</b></span>
            </div>
        </Fragment>
    );
};

export default Switch;
