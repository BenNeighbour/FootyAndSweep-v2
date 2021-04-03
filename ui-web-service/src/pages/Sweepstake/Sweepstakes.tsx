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


import React, {Fragment, FunctionComponent, useState} from 'react';
import SweepstakeCard from "../../components/SweepstakeCard/SweepstakeCard";
import "./Sweepstakes.scss";
import AdvertisementCard from "../../components/AdvertisementCard/AdvertisementCard";
import SearchBar from "../../components/SearchBar/SearchBar";
import {useMediaQuery} from 'react-responsive';
import Button from "../../components/Button/Button";
import ProfileCard from "../../components/ProfileCard/ProfileCard";
import {RouteComponentProps} from "react-router-dom";
import Modal from "../../components/Modal/Modal";
import CreateSweepstake from "../CreateSweepstake/CreateSweepstake";

interface OwnProps extends RouteComponentProps {
}

type Props = OwnProps;

const Sweepstakes: FunctionComponent<Props> = (props) => {
    /* TODO: Change this! */
    const [sweepstakes,] = useState<number[]>([1, 2, 3, 4, 5, 6, 7, 8, 9]);
    const isMobile = useMediaQuery({query: `(max-width: 768px)`});

    const [isCreatingSweepstake, setIsCreatingSweepstake] = useState<boolean>(false);
    const [isJoiningSweepstake, setIsJoiningSweepstake] = useState<boolean>(false);
    const [isBuyingTickets, setIsBuyingTickets] = useState<boolean>(false);

    console.log(!isMobile && !isBuyingTickets || !isJoiningSweepstake || !isCreatingSweepstake);

    return (
        <div className={"container"}>
            <Modal setShowing={setIsCreatingSweepstake} title={"Create a Sweepstake"}
                   description={"Fill the following fields to create a new sweepstake"} showing={isCreatingSweepstake}>
                <CreateSweepstake/>
            </Modal>
            <Modal setShowing={setIsJoiningSweepstake} title={"Join a Sweepstake"}
                   description={"Enter a sweepstake code to join!"} showing={isJoiningSweepstake}>
                {/*<CreateSweepstake/>*/}
            </Modal>
            <Modal setShowing={setIsBuyingTickets} title={"Buy Tickets from Jon’s Epic Sweepstake"}
                   description={"Enter the number of tickets you would like to buy"} showing={isBuyingTickets}>
                {/*<CreateSweepstake/>*/}
            </Modal>

            <Fragment>
                <div className={"topSection"}>
                    <nav className={"navigationSection"}>
                    </nav>
                    <div className={"logoSection"}>
                        <span>Logo Here</span>
                    </div>
                    <div className={"searchSection"}>
                        <SearchBar onChange={() => {
                        }} value={""}/>
                    </div>
                    <div className={"buttonSection"}>
                        {isMobile ?
                            <Button onClick={() => setIsCreatingSweepstake(true)} title={"Create Sweepstake"}
                                    className={"createSweepstakeButton"}/> : undefined}

                        <Button onClick={() => setIsJoiningSweepstake(true)} title={"Join Sweepstake"}/>
                        <Button title={"Earn FootyCoins"}/>

                        <div className={"settingsLink"}>
                        </div>
                    </div>
                    <div className={"opacitySection"}>
                        <h1 className={"title"}>Your Sweepstakes</h1>
                    </div>
                </div>

                <div className={"sweepstakesContainer"}>
                    <div className={"leftSweepstakeSection"}>
                        <ProfileCard className={"profileCard"}/>
                    </div>
                    <div className={"sweepstakes"}>
                        <div className={"titleDesktopSection"}>
                            <div className={"titleBound"}>
                                <h1 className={"title"}>Your Sweepstakes</h1>
                            </div>
                            <Button onClick={() => setIsCreatingSweepstake(true)} title={"Create Sweepstake"}
                                    className={"createSweepstakeButton"}/>
                        </div>

                        {sweepstakes.map((value, index) => {
                            return (
                                <React.Fragment key={`sweepstake-${index}`}>
                                    <SweepstakeCard
                                        setIsBuyingTickets={setIsBuyingTickets}
                                        isMobile={isMobile}
                                        sweepstakeHashTags={["#bhawhu", "#firstscorer"]}
                                        sweepstakeMetadata={"Jon Neighbour, Ben Neighbour, SwaggrMcJaggr..."}
                                        sweepstakeName={"Jon’s Epic Sweepstake"} sweepstakeStatus={"Open"}
                                        totalAmountOfTickets={8} ticketsPurchasedSoFar={2}/>
                                    {(index % 2) === 0 && isMobile ?
                                        <AdvertisementCard advertiserLink={"https://www.algoexpert.io"}
                                                           isMobile={true}/> : undefined}
                                </React.Fragment>
                            );
                        })}
                    </div>
                    <div className={"rightSweepstakeSection"}>
                        <AdvertisementCard advertiserLink={"https://www.algoexpert.io"} isMobile={false}/>
                        <AdvertisementCard advertiserLink={"https://www.algoexpert.io"} isMobile={false}/>
                    </div>
                </div>
            </Fragment>

        </div>
    );
};

export default Sweepstakes;
