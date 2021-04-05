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


import React, {FunctionComponent, useEffect} from 'react';
import "./TextArea.scss";

interface OwnProps {
    includePasswordStrengthChecker?: boolean;
    value: any;
    name: string;
    label: string;
    onChange: (value: any) => void;
    disabled?: boolean;
    style?: React.CSSProperties | {},
    errors: any | null;
    touched: any | null;
}

type Props = OwnProps;

const TextArea: FunctionComponent<Props> = (props) => {
    useEffect(() => {
        const currentTextField: HTMLElement | null = document.getElementById("textarea");

        if (currentTextField) {
            currentTextField.setAttribute("style", "height:" + (currentTextField.scrollHeight) + "px;overflow-y:hidden;");
            currentTextField.addEventListener("input", () => {
                currentTextField.style.height = "auto";
                currentTextField.style.height = (currentTextField.scrollHeight) + "px";
            }, false);
        }
    })

    return (
        <div className={"textArea"}>
            <div className={"textContainer"}>
              <textarea id={"textarea"} className={`textarea${props.errors ? "-invalid" : ""}`} name={props.name}
                        disabled={props.disabled || false}
                        value={props.value}
                        onChange={props.onChange}
                        placeholder={props.label || ""}/>
            </div>
            {props.errors && props.touched ? (
                <span className={"errorTextMessage"}>{props.errors}</span>
            ) : undefined}
            <br/>
        </div>
    );
};

export default TextArea;
