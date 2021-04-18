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
import * as AuthenticateActions from "../../redux/reducers/saga/authenticate/authenticateActions";
import {SignupAuthenticationReducerType} from "../../redux/reducers/saga/authenticate";
import LoadingPage from "../../pages/Loading/LoadingPage";
import * as yup from 'yup';
import InputField from "../../components/InputField/InputField";
import Checkbox from "../../components/Checkbox/Checkbox";
import Button from "../../components/Button/Button";
import SigninWithGoogle from "../../components/SocialButton/Google/SigninWithGoogle";
import SigninWithFacebook from "../../components/SocialButton/Facebook/SigninWithFacebook";
import "./SignupForm.scss";
import {Form, Formik} from "formik";
import {RootState} from "../../redux/rootReducer";
import {connect} from "react-redux";

interface OwnProps {
    state: SignupAuthenticationReducerType;
    actions: typeof AuthenticateActions;
    error?: any | null;
    history: any;
}

const schema = yup.object().shape({
    username: yup
        .string()
        .required("You must enter a Username.")
        .label("Username"),
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
            <div className={"outerSignupArea"}>
                <Formik
                    onSubmit={(formValues) => {
                        props.actions.signupUserAction(formValues);
                    }}
                    validationSchema={schema}
                    initialValues={{
                        username: "",
                        email: "",
                        password: "",
                        confirmPassword: ""
                    }}
                >
                    {({values, handleChange, errors, touched}) => (
                        <Form className={"form"}>
                            <div className={"signupFormContainer"}>
                                <div className={"fieldSection"}>
                                    <InputField type={"text"} onChange={() => {
                                    }} style={{width: "85%"}} touched={false} errors={""} value={""} label={"Full Name"}
                                                name={"fullname"}/>

                                    <InputField type={"text"} onChange={handleChange} style={{width: "85%"}}
                                                touched={touched.username} errors={errors.username} value={values.username}
                                                label={"Username"}
                                                name={"username"}/>

                                    <InputField type={"password"} onChange={handleChange} style={{width: "85%"}}
                                                touched={touched.password} errors={errors.password} value={values.password}
                                                label={"Password"}
                                                name={"password"}/>

                                    <InputField type={"password"} onChange={handleChange} style={{width: "85%"}}
                                                touched={touched.confirmPassword} errors={errors.confirmPassword}
                                                value={values.confirmPassword}
                                                label={"Comfirm Password"}
                                                name={"confirmPassword"}/>

                                    <span className={"errorMessage"}>{props.error}</span>

                                    <Checkbox textLinkTo={"http://www.footyandsweep-dev.com:3000/terms"}
                                              textLink={"Terms & Conditions"}
                                              text={"I agree to FootyAndSweep"}/>

                                    <Button className={"submitButton"} style={{
                                        fontSize: "15px",
                                        lineHeight: "17.5px",
                                        padding: "12.5px",
                                        width: "100%",
                                        borderRadius: "10px",
                                    }}
                                            onClick={() => {
                                            }} type={"submit"} title={"Sign Up"}/>
                                </div>

                                <div className={"socialButtonSection"}>
                                    <SigninWithGoogle
                                        href={"http://api.footyandsweep-dev.com:30389/oauth2/authorization/google?redirect_uri=http://www.footyandsweep-dev.com:3000/oauth/login?signup=true"}/>
                                    <SigninWithFacebook
                                        href={"http://api.footyandsweep-dev.com:30389/oauth2/authorization/facebook?redirect_uri=http://www.footyandsweep-dev.com:3000/oauth/login?signup=true"}/>
                                </div>
                            </div>

                            <div className={"bottomSection"}>
                                <span className={"text"}><b>Already Got an Account? </b></span><span
                                onClick={() => props.history.push("/login")}
                                className={"textLink"}><b>Get Logged In!</b></span>
                            </div>
                        </Form>
                    )}
                </Formik>
            </div>
        );
    }
;

const mapStateToProps = (state: RootState) => {
        return {
            state: state.signupForm
        }
    }
;


function mapDispatchToProps(dispatch: any) {
    return {
        actions: bindActionCreators(AuthenticateActions as any, dispatch),
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(SignupForm);