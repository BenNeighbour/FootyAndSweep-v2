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
import LoadingPage from "../../pages/Loading/LoadingPage";
import {SweepstakeReducerType} from "../../redux/reducers/saga/sweepstake";
import {RootState} from "../../redux/rootReducer";
import {connect} from "react-redux";
import InputField from "../../components/InputField/InputField";
import "./CreateSweepstakeForm.scss";
import TextArea from "../../components/TextArea/TextArea";
import Switch from "../../components/Switch/Switch";

interface OwnProps {
    state: SweepstakeReducerType;
    errors: any;
    touched: any;
    values: any;
    handleChange: any;
}

type Props = OwnProps;

const CreateSweepstakeForm: FunctionComponent<Props> = (props) => {
    if (props.state.isLoading) return <LoadingPage/>

    return (
        <div className={"form"}>
            <div className={"leftFieldSection"}>
                <Switch statusText={"Your Sweepstake is"} falseText={"Public"} trueText={"Private"}
                        value={props.values.isPrivate}
                        label={"Sweepstake Privacy"}/>

                <InputField name={"name"} touched={props.touched.name} label={"Sweepstake Name"}
                            errors={props.errors.name} type={"text"} onChange={props.handleChange}
                            value={props.values.name}/>

                <TextArea name={"description"} touched={props.touched.description}
                          label={"Sweepstake Description"}
                          errors={props.errors.description} onChange={props.handleChange}
                          value={props.values.description}/>

                {/*Select Field*/}
                {/*<Select name={"type"} touched={touched.type} label={"Sweepstake Type"}*/}
                {/*        errors={errors.type} type={"text"} onChange={handleChange} value={values.type}>*/}
                {/*    <option value={"Correct Score H/T"}>Correct Score H/T</option>*/}
                {/*    <option value={"Correct Score F/T"}>Correct Score F/T</option>*/}
                {/*    <option value={"Score at H/T"}>Score at H/T</option>*/}
                {/*    <option value={"Score at F/T"}>Score at F/T</option>*/}
                {/*</Select>*/}

                {/*/!*Select Field*!/*/}
                {/*<Select name={"event"} touched={touched.event} label={"Football Match"}*/}
                {/*        errors={errors.event} type={"text"} onChange={handleChange} value={values.event}>*/}
                {/*    <option value={"BHA vs WOL"}>Brighton & Hove Albion vs Wolves</option>*/}
                {/*</Select>*/}
            </div>

            <div className={"rightFieldSection"}>
                <InputField name={"stake"} touched={props.touched.stake} label={"Stake (eg. £1.00, £1.75)"}
                            errors={props.errors.stake} type={"number"} onChange={props.handleChange}
                            value={props.values.stake}/>
            </div>
        </div>
    );
};

const mapStateToProps = (state: RootState) => {
    return {
        state: state.saveSweepstake
    }
};

const mapDispatchToProps = (state: RootState) => {
    return {}
};

export default connect(mapStateToProps, mapDispatchToProps)(CreateSweepstakeForm);