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
import LoadingPage from "../Loading/LoadingPage";
import {SweepstakeReducerType} from "../../redux/reducers/saga/sweepstake";
import * as SweepstakeActions from "../../redux/reducers/saga/sweepstake/sweepstakeActions";
import {RootState} from "../../redux/rootReducer";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Form, Formik} from "formik";
import * as yup from "yup";
import InputField from "../../components/InputField/InputField";
import "./CreateSweepstake.scss";
import TextArea from "../../components/TextArea/TextArea";
import Select from "../../components/Select/Select";
import Switch from "../../components/Switch/Switch";

interface OwnProps {
    state: SweepstakeReducerType;
    actions: typeof SweepstakeActions;
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

const CreateSweepstake: FunctionComponent<Props> = (props) => {
    if (props.state.isLoading) return <LoadingPage/>

    return (
        <div className={"form"}>
            <Formik
                onSubmit={(formValues) => {
                    props.actions.saveSweepstakeAction(formValues);
                }}
                validationSchema={schema}
                initialValues={{
                    name: "",
                    isPrivate: true,
                    ownerId: "2c91808c788455b60178845be9a80000",
                    minimumPlayers: 2,
                    stake: 10.00,
                    description: "",
                    type: "",
                    event: ""
                }}
            >
                {({values, handleChange, errors, touched}) => (
                    <Form>
                        <div className={"leftFieldSection"}>
                            <Switch statusText={"Your Sweepstake is"} falseText={"Public"} trueText={"Private"}
                                    value={values.isPrivate}
                                    label={"Sweepstake Privacy"}/>

                            <InputField name={"name"} touched={touched.name} label={"Sweepstake Name"}
                                        errors={errors.name} type={"text"} onChange={handleChange} value={values.name}/>

                            <TextArea name={"description"} touched={touched.description}
                                      label={"Sweepstake Description"}
                                      errors={errors.description} onChange={handleChange} value={values.description}/>

                            {/*Select Field*/}
                            <Select name={"type"} touched={touched.type} label={"Sweepstake Type"}
                                    errors={errors.type} type={"text"} onChange={handleChange} value={values.type}>
                                <option value={"Correct Score H/T"}>Correct Score H/T</option>
                                <option value={"Correct Score F/T"}>Correct Score F/T</option>
                                <option value={"Score at H/T"}>Score at H/T</option>
                                <option value={"Score at F/T"}>Score at F/T</option>
                            </Select>

                            {/*Select Field*/}
                            <Select name={"event"} touched={touched.event} label={"Football Match"}
                                    errors={errors.event} type={"text"} onChange={handleChange} value={values.event}>
                                <option value={"BHA vs WOL"}>Brighton & Hove Albion vs Wolves</option>
                            </Select>
                        </div>
                        <div className={"rightFieldSection"}>
                            <InputField name={"stake"} touched={touched.stake} label={"Stake (eg. £1.00, £1.75)"}
                                        errors={errors.stake} type={"number"} onChange={handleChange}
                                        value={values.stake}/>
                        </div>
                    </Form>
                )}
            </Formik>
        </div>
    );
};

const mapStateToProps = (state: RootState) => {
    return {
        state: state.saveSweepstake
    }
};

function mapDispatchToProps(dispatch: any) {
    return {
        actions: bindActionCreators(SweepstakeActions as any, dispatch),
    };
}


export default connect(mapStateToProps, mapDispatchToProps)(CreateSweepstake);