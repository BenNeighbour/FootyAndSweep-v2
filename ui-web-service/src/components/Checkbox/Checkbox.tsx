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


import React, {FunctionComponent} from 'react';
import "./Checkbox.scss";
import {useLocation} from "react-router-dom";

interface OwnProps {
    text: string;
    textLink?: string | null;
    textLinkTo?: string | null;
}

type Props = OwnProps;

const Checkbox: FunctionComponent<Props> = (props) => {
    const currentLocation = useLocation();

    return (
        <div className={"checkboxArea"}>
            <label className="checkboxContainer"><span className={"text"}>{props.text} <a
                href={props.textLinkTo || currentLocation.pathname}
                className={"textLink"}><b>{props.textLink}</b></a></span>
                <input type="checkbox"/>
                <span className="checkmark"/>
            </label>
        </div>
    );
};

export default Checkbox;
