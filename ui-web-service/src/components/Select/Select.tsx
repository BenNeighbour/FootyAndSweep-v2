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
import "./Select.scss"

interface OwnProps {
    includePasswordStrengthChecker?: boolean;
    value: any;
    type: 'text' | 'number' | 'email' | 'password';
    name: string;
    label: string;
    onChange: (value: any) => void;
    disabled?: boolean;
    style?: React.CSSProperties | {},
    errors: any | null;
    touched: any | null;
}

type Props = OwnProps;

const Select: FunctionComponent<Props> = (props) => {
    return (
        <div role={"button"} aria-haspopup={"listbox"} className={"selectArea"}>
            <div className={"selectContainer"}>
                <select className={`select${props.errors ? "-invalid" : ""}`} defaultValue={""}>
                    <option value={""} disabled>{props.label}</option>
                    {props.children}
                </select>
            </div>
            {props.errors && props.touched ? (
                <span className={"errorTextMessage"}>{props.errors}</span>
            ) : undefined}
            <br/>
        </div>
    );
};

export default Select;
