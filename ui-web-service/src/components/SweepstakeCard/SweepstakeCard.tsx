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
import "./SweepstakeCard.scss";
import Tooltip from "../Tooltip/Tooltip";
import CountdownTooltip from "../CountdownTooltip/CountdownTooltip";

interface OwnProps {
    isMobile: boolean;

    sweepstakeStatus: string;
    sweepstakeName: string;
    sweepstakeMetadata: string;
    sweepstakeHashTags: string[];

    totalAmountOfTickets: number;
    ticketsPurchasedSoFar?: number;
}

type Props = OwnProps;

const SweepstakeCard: FunctionComponent<Props> = (props) => {
    return (
        <div className={"sweepstakeCardContainer"}>
            <div className={"mainSection"}>
                <div className={"leftSection"}>
                    <div className={"topLeftSection"}>
                        {/*  Sweepstake Name  */}
                        <div className={"sweepstakeName"}>
                            <span className={"text"}>{props.sweepstakeName}</span>
                            {props.isMobile ? <CountdownTooltip startingDate={new Date(2021, 3, 23)} text={"1:18:45"}
                                                                colorCode={"#46566B"}/> : undefined}
                        </div>

                        {/*  Other Metadata  */}
                        <p>{props.sweepstakeMetadata}</p>
                    </div>

                    <div className={"bottomLeftSection"}>
                        {/*  Hashtag Tooltips  */}
                        {props.sweepstakeHashTags.map((value, index) => (
                            <Tooltip text={value} key={index}/>
                        ))}
                        {!props.isMobile ? <CountdownTooltip startingDate={new Date(2021, 3, 23)} text={"1:18:45"}
                                                            colorCode={"#46566B"}/> : undefined}
                    </div>
                </div>
                <div className={"rightSection"}>
                    <div className={"topRightSection"}>
                        {/*  Status with circle color indicator  */}
                        <span><span className={"circleIndicator"}/>{props.sweepstakeStatus}</span>
                        <br/>

                        {/*  Number of Tickets (with purchased at the end if it's open)  */}
                        <p>{props.ticketsPurchasedSoFar === 1 ? `${props.ticketsPurchasedSoFar} Ticket` : `${props.ticketsPurchasedSoFar} Tickets` || undefined} Purchased</p>
                        <br/>

                        {/*  How many tickets can still be purchased (if it's open)  */}
                        <p className={"smallText"}>{props.ticketsPurchasedSoFar === 1 ? `${props.ticketsPurchasedSoFar} Ticket` : `${props.ticketsPurchasedSoFar} Tickets` || undefined} Left</p>
                    </div>

                    <div className={"bottomRightSection"}>
                        {/*  Buy Tickets call-to-action  */}
                        <span onClick={() => console.log("Pressed")}>Buy Tickets →</span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default SweepstakeCard;