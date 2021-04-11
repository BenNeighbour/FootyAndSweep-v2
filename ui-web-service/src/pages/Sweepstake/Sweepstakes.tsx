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
import {RootState} from "../../redux/rootReducer";
import {bindActionCreators} from "redux";
import * as SweepstakePageActions from "../../redux/reducers/saga/sweepstakePage/sweepstakePageActions";
import {SweepstakesPageReducerType} from "../../redux/reducers/saga/sweepstakePage";
import {connect} from "react-redux";

interface OwnProps extends RouteComponentProps {
    state: SweepstakesPageReducerType;
    actions: typeof SweepstakePageActions;
}

type Props = OwnProps;

const Sweepstakes: FunctionComponent<Props> = (props) => {
    /* TODO: Change this! */
    const [sweepstakes,] = useState<number[]>([1, 2, 3, 4, 5, 6, 7, 8, 9]);
    const isMobile = useMediaQuery({query: `(max-width: 768px)`});

    return (
        <div className={"container"}>
            <Modal setShowing={props.actions.setIsCreatingSweepstake} title={"Create a Sweepstake"}
                   description={"Fill the following fields to create a new sweepstake"}
                   showing={props.state.creatingSweepstake.isCreatingSweepstake}>
                <CreateSweepstake/>
            </Modal>
            <Modal setShowing={props.actions.setIsJoiningSweepstake} title={"Join a Sweepstake"}
                   description={"Enter a sweepstake code to join!"}
                   showing={props.state.joiningSweepstake}>
                {/*<CreateSweepstake/>*/}
            </Modal>
            <Modal setShowing={() => {
            }} title={"Buy Tickets from Jon’s Epic Sweepstake"}
                   description={"Enter the number of tickets you would like to buy"}
                   showing={props.state.buyingTickets.isBuyingTickets}>
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
                            <Button onClick={() => props.actions.setIsCreatingSweepstake(true)}
                                    title={"Create Sweepstake"}
                                    className={"createSweepstakeButton"}/> : undefined}

                        <Button className={"joinSweepstakeButton"}
                                onClick={() => props.actions.setIsJoiningSweepstake(true)}
                                title={"Join Sweepstake"}/>
                        <Button className={"earnCoinsButton"} title={"Earn FootyCoins"}/>

                        <div className={"settingsLink"}/>
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
                            <Button onClick={() => props.actions.setIsCreatingSweepstake(true)}
                                    title={"Create Sweepstake"}
                                    className={"createSweepstakeButton"}/>
                        </div>

                        {sweepstakes.map((value, index) => {
                            return (
                                <React.Fragment key={`sweepstake-${index}`}>
                                    <SweepstakeCard
                                        setIsBuyingTickets={(value: boolean) => props.actions.setIsBuyingTickets({
                                            isBuyingTickets: value,
                                            sweepstake: null
                                        })}
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

const mapStateToProps = (state: RootState) => {
    return {
        state: state.sweepstakesPage
    }
};

function mapDispatchToProps(dispatch: any) {
    return {
        actions: bindActionCreators(SweepstakePageActions as any, dispatch),
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(Sweepstakes);