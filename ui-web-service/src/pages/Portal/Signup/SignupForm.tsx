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
import {RootState} from "../../../redux/rootReducer";
import {bindActionCreators} from "redux";
import * as AuthenticateActions from "../../../redux/reducers/saga/authenticate/authenticateActions";
import {connect} from "react-redux";
import {SignupAuthenticationReducerType} from "../../../redux/reducers/saga/authenticate";
import LoadingPage from "../../Loading/LoadingPage";
import * as yup from 'yup';
import InputField from "../../../components/InputField/InputField";
import Checkbox from "../../../components/Checkbox/Checkbox";
import Button from "../../../components/Button/Button";
import SigninWithGoogle from "../../../components/SocialButton/Google/SigninWithGoogle";
import SigninWithFacebook from "../../../components/SocialButton/Facebook/SigninWithFacebook";

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
        <div className={"outerLoginArea"}>
            <div className={"loginFormContainer"}>
                <div className={"fieldSection"}>
                    <InputField type={"text"} onChange={() => {
                    }} style={{width: "85%"}} touched={false} errors={""} value={""} label={"Full Name"}
                                name={"fullname"}/>

                    <InputField type={"text"} onChange={() => {
                    }} style={{width: "85%"}} touched={false} errors={""} value={""} label={"Username"}
                                name={"username"}/>

                    <InputField type={"password"} onChange={() => {
                    }} style={{width: "85%"}} touched={false} errors={""} value={""} label={"Password"}
                                name={"password"}/>

                    <InputField type={"password"} onChange={() => {
                    }} style={{width: "85%"}} touched={false} errors={""} value={""} label={"Comfirm Password"}
                                name={"confirmpassword"}/>

                    <Checkbox textLinkTo={"http://www.footyandsweep-dev.com:3000/terms"} textLink={"Terms & Conditions"}
                              text={"I agree to FootyAndSweep"}/>

                    <Button className={"submitButton"} style={{
                        fontSize: "20px",
                        lineHeight: "20px",
                        padding: "12.5px",
                        width: "100%",
                        borderRadius: "10px",
                    }}
                            onClick={() => {
                            }} type={"submit"} title={"Sign Up"}/>
                </div>

                <div className={"socialButtonSection"}>
                    <SigninWithGoogle
                        href={"http://api.footyandsweep-dev.com:30389/oauth2/authorization/google?redirect_uri=http://www.footyandsweep-dev.com:3000/oauth/login"}/>
                    <SigninWithFacebook
                        href={"http://api.footyandsweep-dev.com:30389/oauth2/authorization/facebook?redirect_uri=http://www.footyandsweep-dev.com:3000/oauth/login"}/>
                </div>
            </div>

            <div className={"bottomSection"}>
                <span className={"text"}><b>Already Got an Account? </b></span><a
                href={""}
                className={"textLink"}><b>Get Logged In!</b></a>
            </div>
        </div>
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