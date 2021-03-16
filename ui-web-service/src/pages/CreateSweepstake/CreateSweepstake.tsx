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
import styled from "styled-components";
import Button from "../../components/Button/Button";

interface OwnProps {
    state: SweepstakeReducerType;
    actions: typeof SweepstakeActions;
}

const schema = yup.object().shape({
    name: yup
        .string()
        .required("You must enter a Sweepstake Name.")
        .label("Sweepstake Name"),
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
        <FormContainer>
            <Formik
                onSubmit={(formValues) => {
                    props.actions.saveSweepstakeAction(formValues);
                }}
                validationSchema={schema}
                initialValues={{
                    name: "",
                    isPrivate: true,
                    ownerId: "2c918094780c244101780c26a3900000",
                    minimumPlayers: 2,
                    stake: 10.00
                }}
            >
                {({values, handleChange, errors, touched}) => (
                    <Form>
                        <FieldSection>
                            <InputField label={"Sweepstake Name"} touched={touched.name} errors={errors.name}
                                        name={"name"}
                                        type={"text"}
                                        onChange={handleChange}
                                        value={values.name}/>

                            {/*TODO: isPrivate Switch HERE */}
                            {/*TODO: sweepstakeType dropdown */}
                            {/*TODO: sweepstakeEvent dropdown */}

                            <InputField label={"Minimum Players"} touched={touched.minimumPlayers}
                                        errors={errors.minimumPlayers}
                                        name={"minimumPlayers"}
                                        type={"number"}
                                        onChange={handleChange}
                                        value={values.minimumPlayers}/>

                            <InputField label={"Stake"} touched={touched.stake}
                                        errors={errors.stake}
                                        name={"stake"}
                                        type={"number"}
                                        onChange={handleChange}
                                        value={values.stake}/>
                        </FieldSection>

                        <ButtonSection>
                            <Button title={"Create Sweepstake"} type={"submit"} style={{
                                width: "100%",
                                fontSize: "15px",
                                height: "100%",
                                marginBottom: "1em"
                            }}/>
                        </ButtonSection>
                    </Form>
                )}
            </Formik>
        </FormContainer>
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

const FormContainer = styled.div`
text-align: center;
display: block;
overflow: hidden;
`;

const FieldSection = styled.div`
padding: 10px 15px;
margin: 0px 2px;
overflow-y: scroll;
overflow-x: hidden;
&::-webkit-scrollbar {
     -webkit-appearance: none;
     width: 0;
}
`;

const ButtonSection = styled.div`
padding: 5px;
height: 45px;
position: fixed;
overflow: hidden;
`;