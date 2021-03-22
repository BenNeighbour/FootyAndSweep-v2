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


import React, {FunctionComponent, useState} from 'react';
import SweepstakeCard from "../../components/Card/SweepstakeCard";
import "./Sweepstakes.scss";
import AdvertisementCard from "../../components/AdvertisementCard/AdvertisementCard";

interface OwnProps {
}

type Props = OwnProps;

const Sweepstakes: FunctionComponent<Props> = (props) => {
    /* TODO: Change this! */
    const [sweepstakes,] = useState([1, 2, 3, 4, 5, 6, 7, 8, 9]);

    return (
        <div className={"container"}>
            <nav className={"topSection"}>
                <div className={"searchSection"}>

                </div>
                <div className={"opacitySection"}>
                    <h1 className={"title"}>Your Sweepstakes</h1>
                </div>
            </nav>

            <div className={"sweepstakesContainer"}>
                <div className={"sweepstakes"}>
                    {/*For each sweepstake*/}
                    {sweepstakes.map((value, index) => {
                        return (
                            <React.Fragment key={`sweepstake-${index}`}>
                                <SweepstakeCard
                                    sweepstakeHashTags={["#bhawhu", "#firstscorer"]}
                                    sweepstakeMetadata={"Jon Neighbour, Ben Neighbour, SwaggrMcJaggr..."}
                                    sweepstakeName={"Jon’s Epic Sweepstake"} sweepstakeStatus={"Open"}
                                    totalAmountOfTickets={8} ticketsPurchasedSoFar={2}/>
                                {(index % 2) === 0 ? <AdvertisementCard/> : undefined}
                            </React.Fragment>
                        );
                    })}
                </div>
            </div>
        </div>
    );
};

export default Sweepstakes;