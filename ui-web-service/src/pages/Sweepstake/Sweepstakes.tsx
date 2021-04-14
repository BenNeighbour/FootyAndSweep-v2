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
import {useMediaQuery} from 'react-responsive';
import Button from "../../components/Button/Button";
import ProfileCard from "../../components/ProfileCard/ProfileCard";
import {RouteComponentProps} from "react-router-dom";
import Modal from "../../components/Modal/Modal";
import CreateSweepstake from "../../forms/CreateSweepstake/CreateSweepstake";
import {bindActionCreators} from "redux";
import * as SweepstakePageActions from "../../redux/reducers/saga/sweepstakePage/sweepstakePageActions";
import {Form, Formik} from "formik";
import * as yup from "yup";
import {RootState} from "../../redux/rootReducer";
import {connect} from "react-redux";
import * as SweepstakeActions from "../../redux/reducers/saga/sweepstake/sweepstakeActions";
import SweepstakeTopSection from "./SweepstakeTopSection/SweepstakeTopSection";

interface OwnProps extends RouteComponentProps {
    state: RootState;
    sweepstakePageActions: typeof SweepstakePageActions;
    saveSweepstakeActions: typeof SweepstakeActions;
}

const schema = yup.object().shape({
    name: yup
        .string()
        .required("You must enter a Sweepstake Name.")
        .label("Sweepstake Name"),
    description: yup
        .string()
        .required("You must enter a Sweepstake Description.")
        .label("Sweepstake Description"),
    type: yup
        .string()
        .required("You must enter a valid Sweepstake Type.")
        .label("Sweepstake Type"),
    event: yup
        .string()
        .required("You must enter a valid Football Match.")
        .label("Football Match"),
    isPrivate: yup
        .boolean()
        .required("Your sweepstake must be Public or Private.")
        .label("Sweepstake Privacy"),
    minimumPlayers: yup
        .number()
        .required("You must enter the number of Minimum Players.")
        .label("Minimum Players"),
    stake: yup
        .number()
        .required("You must enter a valid Stake.")
        .label("Stake")
});

type Props = OwnProps;

const Sweepstakes: FunctionComponent<Props> = (props) => {
    /* TODO: Change this! */
    const [sweepstakes,] = useState<number[]>([1, 2, 3, 4, 5, 6, 7, 8, 9]);
    const isMobile = useMediaQuery({query: `(max-width: 768px)`});

    return (
        <div className={"container"}>
            <Formik
                onSubmit={(formValues) => {
                    props.saveSweepstakeActions.saveSweepstakeAction(formValues);
                }}
                validationSchema={schema}
                initialValues={{
                    name: "",
                    isPrivate: true,
                    ownerId: "2c91808878b827550178b82b37840000",
                    minimumPlayers: 2,
                    stake: 0,
                    description: "",
                    type: "Correct Score H/T",
                    event: "sdf"
                }}
            >
                {({values, handleChange, errors, touched}) => (
                    <Form>
                        <Modal isForm setShowing={props.sweepstakePageActions.setIsCreatingSweepstake}
                               title={"Create a Sweepstake"}
                               description={"Fill the following fields to create a new sweepstake"}
                               showing={props.state.sweepstakesPage.creatingSweepstake}>
                            <CreateSweepstake values={values} handleChange={handleChange} errors={errors}
                                              touched={touched}/>
                        </Modal>
                    </Form>
                )}
            </Formik>
            <Modal setShowing={props.sweepstakePageActions.setIsJoiningSweepstake} title={"Join a Sweepstake"}
                   description={"Enter a sweepstake code to join!"}
                   showing={props.state.sweepstakesPage.joiningSweepstake}>
            </Modal>
            <Modal setShowing={() => {
            }} title={"Buy Tickets from Jon’s Epic Sweepstake"}
                   description={"Enter the number of tickets you would like to buy"}
                   showing={props.state.sweepstakesPage.buyingTickets.isBuyingTickets}>
            </Modal>

            <Fragment>
                <SweepstakeTopSection setJoiningSweepstake={props.sweepstakePageActions.setIsJoiningSweepstake}
                                      setCreatingSweepstake={props.sweepstakePageActions.setIsCreatingSweepstake}
                                      isMobile={isMobile}/>

                <div className={"sweepstakesContainer"}>
                    <div className={"leftSweepstakeSection"}>
                        <ProfileCard className={"profileCard"}/>
                    </div>
                    <div className={"sweepstakes"}>
                        <div className={"titleDesktopSection"}>
                            <div className={"titleBound"}>
                                <h1 className={"title"}>Your Sweepstakes</h1>
                            </div>
                            <Button onClick={() => props.sweepstakePageActions.setIsCreatingSweepstake(true)}
                                    title={"Create Sweepstake"}
                                    className={"createSweepstakeButton"}/>
                        </div>

                        {sweepstakes.map((value, index) => {
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
        saveSweepstakeActions: bindActionCreators(SweepstakeActions as any, dispatch),
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(Sweepstakes);
