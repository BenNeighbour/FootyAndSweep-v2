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
import {RootState} from "../../redux/rootReducer";
import * as SweepstakePageActions from "../../redux/reducers/saga/sweepstakePage";
import * as TicketActions from "../../redux/reducers/saga/ticket";
import Modal from "../../components/Modal/Modal";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {SweepstakeData} from "../../redux/model";
import {Form, Formik} from "formik";
import * as yup from "yup";
import BuyTicketsForm from "../../forms/BuyTicketsForm/BuyTicketsForm";

interface OwnProps {
    state: RootState;
    sweepstakePageActions: typeof SweepstakePageActions;
    ticketActions: typeof TicketActions;
    sweepstake: SweepstakeData | null;
}

const schema = yup.object().shape({
    numberOfTickets: yup
        .number()
        .required("You must enter a Number of Tickets to Buy!")
        .test(
            'isPositive',
            'The Number of Tickets must be valid',
            (value: any) => value > 0)
        .label("Number Of Tickets")
});

type Props = OwnProps;

const BuyTicketsModal: FunctionComponent<Props> = (props) => {
    if (props.sweepstake !== null) {
        return (
            <Formik
                onSubmit={(formValues) => {
                    props.ticketActions.buySweepstakeTicketsAction(formValues);
                }}
                validationSchema={schema}
                initialValues={{
                    numberOfTickets: 0
                }}
            >
                {(formik) => {
                    const {
                        values,
                        handleChange,
                        errors,
                        touched,
                        handleBlur,
                    } = formik;
                    if (props.sweepstake !== null) return (
                        <Form>
                            <Modal small={true} shrinksOnMobile={true} setShowing={(value: boolean) => props.sweepstakePageActions.setIsBuyingTickets({
                                isBuyingTickets: value,
                                sweepstake: null
                            })} title={`Buy Tickets from ${props.sweepstake.name}`}
                                   description={"Enter the number of tickets you would like to buy"}
                                   showing={props.state.sweepstakesPage.buyingTickets.isBuyingTickets}>
                                <BuyTicketsForm handleBlur={handleBlur} values={values} handleChange={handleChange}
                                                    errors={errors.numberOfTickets || props.state.sweepstake.error}
                                                    touched={touched}/>
                            </Modal>
                        </Form>
                    )
                }}
            </Formik>
        );
    }

    return null;
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
        ticketActions: bindActionCreators(TicketActions as any, dispatch),
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(BuyTicketsModal);