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


import React, { FunctionComponent } from 'react';
import {RootState} from "../../redux/rootReducer";
import * as SweepstakePageActions from "../../redux/reducers/saga/sweepstakePage/sweepstakePageActions";
import Modal from "../../components/Modal/Modal";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";

interface OwnProps {
    state: RootState;
    sweepstakePageActions: typeof SweepstakePageActions;
}

type Props = OwnProps;

const JoinSweepstakeModal: FunctionComponent<Props> = (props) => {
  return (
      <Modal setShowing={props.sweepstakePageActions.setIsJoiningSweepstake} title={"Join a Sweepstake"}
             description={"Enter a sweepstake code to join!"}
             showing={props.state.sweepstakesPage.joiningSweepstake}>
      </Modal>
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

export default connect(mapStateToProps, mapDispatchToProps)(JoinSweepstakeModal);