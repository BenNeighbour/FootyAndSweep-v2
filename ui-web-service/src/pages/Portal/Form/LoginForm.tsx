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
import {bindActionCreators} from "redux";
import Button from "../../../components/Button/Button";
import InputField from "../../../components/InputField/InputField";
import SocialButtonSection from "../SocialButton/SocialButtons";
import * as AuthenticateActions from "./../../../redux/reducers/saga/authenticate/authenticateActions";
import {Form, Formik} from "formik";
import styled from "styled-components";
import {connect} from "react-redux";
import {RootState} from "../../../redux/rootReducer";
import DontHaveAccount from "./DontHaveAccount";

interface OwnProps {
    actions: typeof AuthenticateActions;
    setIsLoggingIn: (value: boolean) => void;
}

type Props = OwnProps;

const LoginForm: FunctionComponent<Props> = (props) => {
    return (
        <FormContainer>
            <FormDiv>
                <Formik
                    onSubmit={(formValues) => {
                        props.actions.loginUserAction(formValues);
                    }}
                    initialValues={{username: "", password: ""}}
                >
                    {({values, handleChange}) => (
                        <Form>
                            <InputField label={"Username"} name={"username"}
                                        type={"text"}
                                        onChange={handleChange}
                                        value={values.username}/>

                            <InputField label={"Password"} name={"password"}
                                        type={"password"}
                                        onChange={handleChange}
                                        value={values.password}/>

                            <Button title={"Sign In"} type={"submit"} style={{
                                width: "100%",
                                fontSize: "15px",
                                height: "45px",
                                marginBottom: "1em"
                            }}/>

                            <DontHaveAccount isLoggingIn={true} setIsLoggingIn={props.setIsLoggingIn}/>

                            <OrSignInWithText>OR</OrSignInWithText>

                            <SocialButtonSection/>
                        </Form>
                    )}
                </Formik>
            </FormDiv>
        </FormContainer>
    );
};

const mapStateToProps = (state: RootState) => (
    {
        loginForm: state.loginForm
    }
);

function mapDispatchToProps(dispatch: any) {
    return {
        actions: bindActionCreators(AuthenticateActions as any, dispatch),
    };
}


export default connect(mapStateToProps, mapDispatchToProps)(LoginForm);

const FormContainer = styled.div`
display: flex;
flex-grow: 1;
height: 100%;
align-items: center;
place-items: center;
`;

const FormDiv = styled.div`
margin: auto;
flex-basis: 725px;
padding-left: 1em;
padding-right: 1em;
padding-top: 5em;
`;

const OrSignInWithText = styled.div`
text-align: center;
font-family: 'Open Sans', sans-serif;
color: #b5b5b5;
`;
