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


import React, {Fragment, FunctionComponent, useEffect, useState} from 'react';
import SweepstakeCard from "../../components/SweepstakeCard/SweepstakeCard";
import "./Sweepstakes.scss";
import AdvertisementCard from "../../components/AdvertisementCard/AdvertisementCard";
import {useMediaQuery} from 'react-responsive';
import Button from "../../components/Button/Button";
import ProfileCard from "../../components/ProfileCard/ProfileCard";
import {RouteComponentProps} from "react-router-dom";
import {bindActionCreators} from "redux";
import * as SweepstakePageActions from "../../redux/reducers/saga/sweepstakePage/sweepstakePageActions";
import {RootState} from "../../redux/rootReducer";
import {connect} from "react-redux";
import SweepstakeTopSection from "./SweepstakeTopSection/SweepstakeTopSection";
import JoinSweepstakeModal from "../../views/JoinSweepstakeModal";
import CreateSweepstakeModal from "../../views/CreateSweepstakeModal";
import BuyTicketsModal from "../../views/BuyTicketsModal";
import * as SweepstakeActions from "../../redux/reducers/saga/sweepstake/sweepstakeActions";

interface OwnProps extends RouteComponentProps {
    state: RootState;
    sweepstakePageActions: typeof SweepstakePageActions;
    getSweepstakeActions: typeof SweepstakeActions;
}

type Props = OwnProps;

const Sweepstakes: FunctionComponent<Props> = (props) => {
    /* TODO: Change this! */
    const [sweepstakes,] = useState<number[]>([1, 2, 3, 4, 5, 6, 7, 8, 9]);
    // const [sweepstakes,] = useState<any>([]);

    const isMobile = useMediaQuery({query: `(max-width: 768px)`});

    useEffect(() => {
        props.getSweepstakeActions.getMySweepstakesAction("");
    }, []);


    return (
        <div className={"container"}>
            <CreateSweepstakeModal/>
            <JoinSweepstakeModal/>
            <BuyTicketsModal/>

            <Fragment>
                <SweepstakeTopSection setJoiningSweepstake={props.sweepstakePageActions.setIsJoiningSweepstake}
                                      setCreatingSweepstake={props.sweepstakePageActions.setIsCreatingSweepstake}
                                      isMobile={isMobile}/>

                <div className={`sweepstakesContainer`}>
                    <div className={"leftSweepstakeSection"}>
                        <ProfileCard className={"profileCard"}/>
                    </div>
                    <div className={"mainSweepstakeSection"}>
                        <div className={"sweepstakes"}>
                            <div className={"titleDesktopSection"}>
                                <div className={"titleBound"}>
                                    <h1 className={"title"}>Your Sweepstakes</h1>
                                </div>
                                <Button onClick={() => props.sweepstakePageActions.setIsCreatingSweepstake(true)}
                                        title={"Create Sweepstake"}
                                        className={"createSweepstakeButton"}/>
                            </div>

                            {sweepstakes.map((value: any, index: any) => {
                                return (
                                    <React.Fragment key={`sweepstake-${index}`}>
                                        <SweepstakeCard
                                            setIsBuyingTickets={(value: boolean) => props.sweepstakePageActions.setIsBuyingTickets({
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
                </div>
            </Fragment>

        </div>
    );
};

const mapStateToProps = (state: RootState) => {
        return {
            state
        }
    }
;

function mapDispatchToProps(dispatch: any) {
    return {
        getSweepstakeActions: bindActionCreators(SweepstakeActions as any, dispatch),
        sweepstakePageActions: bindActionCreators(SweepstakePageActions as any, dispatch),
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(Sweepstakes);
