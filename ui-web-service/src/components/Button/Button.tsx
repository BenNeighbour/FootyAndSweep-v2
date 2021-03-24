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
import "./Button.scss";

interface OwnProps {
    title: string;
    type?: 'submit' | null;
    onClick?: (value: any) => void;
    disabled?: boolean;
    style?: React.CSSProperties | {};
    className?: string | "";
}

type Props = OwnProps;

const Button: FunctionComponent<Props> = (props) => {
    return (
        <div className={props.className}>
            <button className={"buttonContainer"} type={props.type || undefined} style={props.style}
                    disabled={props.disabled || false}
                    onClick={props.onClick}>
                {props.title}
            </button>
        </div>
    );
};

export default Button;
