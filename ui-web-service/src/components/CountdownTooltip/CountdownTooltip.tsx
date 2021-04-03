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
    colorCode?: string;
    startingDate: Date;
}

type Props = OwnProps;

const CountdownTooltip: FunctionComponent<Props> = (props) => {
    /* Updating state for the sweepstake countdown */
    const [timeLeft, setTimeLeft] = useState<any>(getTimeRemaining(props.startingDate));

    useEffect(() => {
        let countdown = setTimeout(() => {
            /* Decrement the time */
            setTimeLeft(getTimeRemaining(props.startingDate))
        }, 1000);

        /* Cleanup function to prevent JavaScript Memory Leaks - this clears the previous timeout to prevent an overflow */
        return function cleanup() {
            clearTimeout(countdown);
        };
    }, [timeLeft, props.startingDate]);

    return (
        <div className={"countdownTooltipContainer"} style={{backgroundColor: props.colorCode}}>
            <span
                className={"tooltipText"}>{timeLeft.days}:{timeLeft.hours}:{timeLeft.minutes}:{timeLeft.seconds}</span>
        </div>
    );
};

function getTimeRemaining(endDate: Date): any {
    let total: number = endDate.getTime() - new Date().getTime();

    return {
        total,
        days: Math.floor(total / (1000 * 60 * 60 * 24)),
        hours: Math.floor((total / (1000 * 60 * 60)) % 24),
        minutes: Math.floor((total / 1000 / 60) % 60),
        seconds: Math.floor((total / 1000) % 60)
    };
}

export default CountdownTooltip;
