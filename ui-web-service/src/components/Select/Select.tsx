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


import React, {FunctionComponent, useState, useEffect, useRef} from 'react';
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
    const [isCollapsed, setIsCollapsed] = useState<boolean>(false);

    const selectRef = useRef(null);
    useOutsideOfComponentClick(selectRef, setIsCollapsed);

    return (
        <div ref={selectRef} role={"button"} aria-haspopup={"listbox"} className={"selectArea"}>
            <div className={"selectContainer"}>
                <select className={`select${props.errors ? "-invalid" : ""}${isCollapsed ? " collapsed" : ""}`}>
                    <option value={"Sweepstake Type"} disabled={true} hidden={true}>Sweepstake Type</option>
                    <option value={"Correct Score H/T"}>Correct Score H/T</option>
                    <option value={"Correct Score F/T"}>Correct Score F/T</option>
                    <option value={"Score at H/T"}>Score at H/T</option>
                    <option value={"Score at F/T"}>Score at F/T</option>
                </select>
            </div>
        </div>
    );
};

function useOutsideOfComponentClick(ref: any, setState: (value: any) => void) {
    useEffect(() => {
        function handleClickOutside(event: any) {
            /* Reset the state */
            if (ref.current && !ref.current.contains(event.target)) setState(false);
        }

        document.addEventListener("mousedown", handleClickOutside);
        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, [ref]);
}

export default Select;
