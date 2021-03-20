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
import PasswordBar from "./PasswordBar";
import "./InputField.scss"

interface OwnProps {
    includePasswordStrengthChecker?: boolean;
    value: any;
    placeholder?: string | null;
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

const InputField: FunctionComponent<Props> = (props) => {
    return (
        <>
            <div className={"inputContainer"}>
                <span className={"fieldName"}>{props.label}</span>
                <input className={"input"} name={props.name} disabled={props.disabled || false} type={props.type}
                       value={props.value}
                       onChange={props.onChange}
                       placeholder={props.placeholder || ""}/>
            </div>
            {props.type === "password" && props.includePasswordStrengthChecker ?
                <div><PasswordBar password={props.value}/></div> : undefined}
            {props.errors && props.touched ? (
                <span className={"errorTextMessage"}>{props.errors}</span>
            ) : null}
            <br/>
        </>
    );
};

export default InputField;
