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
import {SweepstakeReducerType} from "../../redux/reducers/saga/sweepstake";
import {RootState} from "../../redux/rootReducer";
import {connect} from "react-redux";
import InputField from "../../components/InputField/InputField";
import "./JoinSweepstakeForm.scss";
import Spinner from "../../components/Spinner/Spinner";

interface OwnProps {
    state: SweepstakeReducerType;
    errors: any;
    touched: any;
    values: any;
    handleChange: any;
    handleBlur: any;
}

type Props = OwnProps;

const JoinSweepstakeForm: FunctionComponent<Props> = (props) => {
    if (props.state.isLoading) return (
        <div style={{
            textAlign: "center"
        }} className={"loading"}>
            <Spinner/>
        </div>
    );

    return (
        <div className={"buyTicketForm"}>
            <div className={"fieldSection"}>
                <InputField handleBlur={props.handleBlur} name={"code"} touched={props.touched.code} label={"Sweepstake Code"}
                            errors={props.errors} type={"text"} onChange={props.handleChange}
                            value={props.values.code} />
            </div>
        </div>
    );
};

const mapStateToProps = (state: RootState) => {
    return {
        state: state.sweepstake
    }
};

const mapDispatchToProps = (state: RootState) => {
    return {}
};

export default connect(mapStateToProps, mapDispatchToProps)(JoinSweepstakeForm);