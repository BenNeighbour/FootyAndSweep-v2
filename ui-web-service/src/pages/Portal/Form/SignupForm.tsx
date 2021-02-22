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
import Button from "../../../components/Button/Button";
import InputField from "../../../components/InputField/InputField";
import SocialButtonSection from "../SocialButton/SocialButtons";
import {Form, Formik} from "formik";
import styled from "styled-components";
import {RootState} from "../../../redux/rootReducer";
import {bindActionCreators} from "redux";
import * as AuthenticateActions from "../../../redux/reducers/saga/authenticate/authenticateActions";
import {connect} from "react-redux";
import DontHaveAccount from "./DontHaveAccount";
import {SignupAuthenticationReducerType} from "../../../redux/reducers/saga/authenticate";
import LoadingPage from "../../Loading/LoadingPage";
import * as yup from 'yup';

interface OwnProps {
    state: SignupAuthenticationReducerType;
    actions: typeof AuthenticateActions;
    setIsLoggingIn: (value: boolean) => void;
    error?: any | null;
}

const schema = yup.object().shape({
    username: yup
        .string()
        .required("You must enter a Username.")
        .label("Username"),
    email: yup.string()
        .email()
        .required("You must enter an Email Address.")
        .label("Email Address"),
    password: yup
        .string()
        .required("You must enter a Password.")
        .label("Confirm Password"),
    confirmPassword: yup
        .string()
        .oneOf([yup.ref('password'), null], 'Passwords must match')
        .oneOf([yup.ref('password'), null], 'Passwords must match')
        .required("You must enter a Confirm Password.")
        .matches(
            /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/,
            'Not strong enough'
        )
        .label("Confirm Password")
});

type Props = OwnProps;

const SignupForm: FunctionComponent<Props> = (props) => {
    if (props.state.isLoading) return <LoadingPage/>

    return (
        <FormContainer>
            <FormDiv>
                <Formik
                    onSubmit={(formValues) => {
                        props.actions.signupUserAction(formValues);
                    }}
                    validationSchema={schema}
                    initialValues={{
                        username: "",
                        email: "",
                        password: "",
                        confirmPassword: "",
                        dateOfBirth: new Date()
                    }}
                >
                    {({values, handleChange, errors, touched}) => (
                        <Form>
                            <InputField touched={touched.username} errors={errors.username} name={"username"}
                                        label={"Username"}
                                        type={"text"}
                                        onChange={handleChange}
                                        value={values.username}/>

                            <InputField touched={touched.email} errors={errors.email} label={"Email Address"}
                                        name={"email"}
                                        type={"email"}
                                        onChange={handleChange}
                                        value={values.email}/>

                            <InputField errors={errors.password} touched={touched.password}
                                        includePasswordStrengthChecker
                                        name={"password"}
                                        label={"Password"}
                                        type={"password"}
                                        onChange={handleChange}
                                        value={values.password}/>

                            <InputField errors={errors.confirmPassword} touched={touched.confirmPassword}
                                        includePasswordStrengthChecker
                                        name={"confirmPassword"}
                                        label={"Confirm Password"}
                                        type={"password"}
                                        onChange={handleChange}
                                        value={values.confirmPassword}/>

                            <PasswordConditionsText>Your password MUST contain at least: <br/> &#8226; 8
                                Characters <br/> &#8226; 1
                                Uppercase Letter <br/> &#8226; 1 Special Character <br/> &#8226; 1 Number
                            </PasswordConditionsText> <br/>

                            <Button title={"Create Account"} type={"submit"} style={{
                                width: "100%",
                                fontSize: "15px",
                                height: "45px",
                                marginBottom: "1em"
                            }}/>


                            <ErrorTextMessage>{props.state.error || props.error || undefined}</ErrorTextMessage>

                            <DontHaveAccount isLoggingIn={false} setIsLoggingIn={props.setIsLoggingIn}/>

                            <OrSignInWithText>OR</OrSignInWithText>

                            <SocialButtonSection/>
                        </Form>
                    )}
                </Formik>
            </FormDiv>
        </FormContainer>
    );
};

const mapStateToProps = (state: RootState) => {
    return {
        state: state.signupForm
    }
};


function mapDispatchToProps(dispatch: any) {
    return {
        actions: bindActionCreators(AuthenticateActions as any, dispatch),
    };
}


export default connect(mapStateToProps, mapDispatchToProps)(SignupForm);

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

const ErrorTextMessage = styled.div`
text-align: left;
font-family: 'Open Sans', sans-serif;
font-size: 14px;
color: red;
`;

const PasswordConditionsText = styled.p`
text-align: left;
font-family: 'Open Sans', sans-serif;
color: #b5b5b5;
font-size: 14px;
font-weight: 600;
line-height: 21px;
margin: 0;
padding-left: 10px;
`;