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
import SweepstakeCard from "../../components/SweepstakeCard/SweepstakeCard";
import "./Sweepstakes.scss";
import AdvertisementCard from "../../components/AdvertisementCard/AdvertisementCard";
import SearchBar from "../../components/SearchBar/SearchBar";
import {useMediaQuery} from 'react-responsive';
import Button from "../../components/Button/Button";
import ProfileCard from "../../components/ProfileCard/ProfileCard";
import {RouteComponentProps} from "react-router-dom";
import Modal from "../../components/Modal/Modal";

interface OwnProps extends RouteComponentProps {
}

type Props = OwnProps;

const Sweepstakes: FunctionComponent<Props> = (props) => {
    /* TODO: Change this! */
    const [sweepstakes,] = useState<number[]>([1, 2, 3, 4, 5, 6, 7, 8, 9]);
    const isMobile = useMediaQuery({query: `(max-width: 768px)`});

    const [isJoiningSweepstake, setIsJoiningSweepstake] = useState<boolean>(false);

    return (
        <div className={"container"}>
            <Modal setShowing={setIsJoiningSweepstake} showing={isJoiningSweepstake}/>
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
                    <Button onClick={() => setIsJoiningSweepstake(true)} style={{
                        padding: "5px 20px"
                    }} title={"Join Sweepstake"}/>
                    <Button style={{
                        padding: "5px 20px"
                    }} title={"Earn FootyCoins"}/>
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
                        <Button onClick={() => {
                            props.history.push("/sweepstakes/create")
                        }} title={"Create Sweepstake"} className={"createSweepstakeButton"}/>
                    </div>

                    {sweepstakes.map((value, index) => {
                        return (
                            <React.Fragment key={`sweepstake-${index}`}>
                                <SweepstakeCard
                                    isMobile={isMobile}
                                    sweepstakeHashTags={["#bhawhu", "#firstscorer"]}
                                    sweepstakeMetadata={"Jon Neighbour, Ben Neighbour, SwaggrMcJaggr..."}
                                    sweepstakeName={"Jon’s Epic Sweepstake"} sweepstakeStatus={"Open"}
                                    totalAmountOfTickets={8} ticketsPurchasedSoFar={2}/>
                                {(index % 2) === 0 && isMobile ? <AdvertisementCard advertiserLink={"https://www.algoexpert.io"} isMobile={true} /> : undefined}
                            </React.Fragment>
                        );
                    })}
                </div>
                <div className={"rightSweepstakeSection"}>
                    <AdvertisementCard advertiserLink={"https://www.algoexpert.io"} isMobile={false}/>
                    <AdvertisementCard advertiserLink={"https://www.algoexpert.io"} isMobile={false}/>
                </div>
            </div>

        </div>
    );
};

export default Sweepstakes;
