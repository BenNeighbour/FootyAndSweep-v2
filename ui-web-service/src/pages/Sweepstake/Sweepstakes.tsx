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


import React, {Fragment, FunctionComponent, useEffect} from 'react';
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
import JoinSweepstakeModal from "../../views/SweepstakesPage/JoinSweepstakeModal";
import CreateSweepstakeModal from "../../views/SweepstakesPage/CreateSweepstakeModal";
import BuyTicketsModal from "../../views/SweepstakesPage/BuyTicketsModal";
import LoadingPage from "../Loading/LoadingPage";
import Spinner from "../../components/Spinner/Spinner";

interface OwnProps extends RouteComponentProps {
    state: RootState;
    sweepstakePageActions: typeof SweepstakePageActions;
}

type Props = OwnProps;

const Sweepstakes: FunctionComponent<Props> = (props) => {
    const isMobile = useMediaQuery({query: `(max-width: 768px)`});

    useEffect(() => {
        props.sweepstakePageActions.getMySweepstakesAction("");
        props.sweepstakePageActions.getProfileInfoAction();
    }, [props.sweepstakePageActions]);

    if (props.state.sweepstakesPage.isLoading) return <LoadingPage/>

    return (
        <div className={"container"}>
            <CreateSweepstakeModal/>
            <JoinSweepstakeModal/>
            <BuyTicketsModal sweepstake={props.state.sweepstakesPage.buyingTickets.sweepstake}/>

            <Fragment>
                <SweepstakeTopSection setJoiningSweepstake={props.sweepstakePageActions.setIsJoiningSweepstake}
                                      setCreatingSweepstake={props.sweepstakePageActions.setIsCreatingSweepstake}
                                      isMobile={isMobile}/>

                <div className={`sweepstakesContainer`}>
                    <div className={"leftSweepstakeSection"}>
                        <ProfileCard profile={props.state.sweepstakesPage.profileInfo} className={"profileCard"}/>
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

                            {props.state.sweepstakesPage.sweepstakes.length > 0 ? props.state.sweepstakesPage.sweepstakes.map((value: any, index: any) => {
                                return (
                                    <React.Fragment key={`sweepstake-${index}`}>
                                        <SweepstakeCard
                                            setIsBuyingTickets={(sweepstake) => {
                                                props.sweepstakePageActions.setIsBuyingTickets({
                                                    isBuyingTickets: true,
                                                    sweepstake: sweepstake
                                                })
                                            }}
                                            sweepstakeJoinCode={value.joinCode}
                                            isMobile={isMobile}
                                            sweepstakeHashTags={["#bhawhu", "#firstscorer"]}
                                            sweepstakeMetadata={"Jon Neighbour, Ben Neighbour, SwaggrMcJaggr..."}
                                            sweepstakeName={value.name} sweepstakeStatus={value.status}
                                            totalAmountOfTickets={8} ticketsPurchasedSoFar={2}/>
                                        {(index % 2) === 0 && isMobile ?
                                            <AdvertisementCard advertiserLink={"https://www.algoexpert.io"}
                                                               isMobile={true}/> : undefined}
                                    </React.Fragment>
                                );
                            }) : props.state.sweepstakesPage.isLoading ? (
                                <div>
                                    <Spinner/>
                                </div>
                            ) : null}
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
        sweepstakePageActions: bindActionCreators(SweepstakePageActions as any, dispatch),
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(Sweepstakes);
