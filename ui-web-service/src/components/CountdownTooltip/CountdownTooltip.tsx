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


import React, {FunctionComponent, useEffect, useState} from 'react';
import "./CountdownTooltip.scss";

interface OwnProps {
    text: string;
    colorCode?: string;
    startingDate: Date;
}

type Props = OwnProps;

const CountdownTooltip: FunctionComponent<Props> = (props) => {
    const [timeLeft, setTimeLeft] = useState(props.startingDate);

    useEffect(() => {
        let countdown = setTimeout(() => {
            if (timeLeft !== new Date()) {
                setTimeLeft(new Date());
            }
        }, 1000);

        return function cleanup() {
            clearTimeout(countdown);
        };
    }, [timeLeft])

    return (
        <div className={"countdownTooltipContainer"} style={{backgroundColor: props.colorCode}}>
            <span className={"tooltipText"}>{timeLeft.getHours()}:{timeLeft.getMinutes()}:{timeLeft.getSeconds()}</span>
        </div>
    );
};

export default CountdownTooltip;
