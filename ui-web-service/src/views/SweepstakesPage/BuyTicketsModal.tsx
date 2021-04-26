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
import * as SweepstakePageActions from "../../redux/reducers/saga/sweepstakePage/sweepstakePageActions";
import Modal from "../../components/Modal/Modal";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {SweepstakeData} from "../../redux/model";

interface OwnProps {
    state: RootState;
    sweepstakePageActions: typeof SweepstakePageActions;
    sweepstake: SweepstakeData | null;
}

type Props = OwnProps;

const BuyTicketsModal: FunctionComponent<Props> = (props) => {
    if (props.sweepstake !== null) {
        return (
            <Modal setShowing={(value: boolean) => props.sweepstakePageActions.setIsBuyingTickets({
                isBuyingTickets: value,
                sweepstake: null
            })} title={`Buy Tickets from ${props.sweepstake.name}`}
                   description={"Enter the number of tickets you would like to buy"}
                   showing={props.state.sweepstakesPage.buyingTickets.isBuyingTickets}>
            </Modal>
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
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(BuyTicketsModal);