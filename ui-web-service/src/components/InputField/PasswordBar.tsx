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
import zxcvbn from 'zxcvbn';

interface OwnProps {
    password: string;
}

type Props = OwnProps;

const PasswordBar: FunctionComponent<Props> = (props) => {
    const validationResult = zxcvbn(props.password);
    const scoreThreshold = validationResult.score * 100 / 4;

    const funcProgressColor = (): string => {
        switch (validationResult.score) {
            case 0 | 1:
                return '#EA1111';
            case 2:
                return '#FFAD00';
            case 3:
                return '#9bc158';
            case 4:
                return '#00b500';
            default:
                return '#EA1111';
        }
    }

    const changePasswordColor = () => ({
        width: scoreThreshold > 25 ? `${scoreThreshold}%` : "25%",
        background: funcProgressColor(),
        height: '4px'
    })

    return (
        <div>
            <div className="progress" style={{height: '7px'}}>
                <div className="progress-bar" style={changePasswordColor()}/>
            </div>
        </div>
    );
};

export default PasswordBar;
