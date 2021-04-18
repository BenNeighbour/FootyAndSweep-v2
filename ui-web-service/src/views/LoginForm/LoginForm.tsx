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
import {connect} from "react-redux";
import {RootState} from "../../redux/rootReducer";
import {LoginAuthenticationReducerType} from "../../redux/reducers/saga/authenticate";
import LoadingPage from "../../pages/Loading/LoadingPage";
import * as yup from "yup";
import InputField from "../../components/InputField/InputField";
import "./LoginForm.scss";
import Button from "../../components/Button/Button";
import SigninWithGoogle from "../../components/SocialButton/Google/SigninWithGoogle";
import SigninWithFacebook from "../../components/SocialButton/Facebook/SigninWithFacebook";

interface OwnProps {
    state: LoginAuthenticationReducerType;
    actions: typeof AuthenticateActions;
    error?: string | null;
    history: any;
}

const schema = yup.object().shape({
    email: yup
        .string()
        .email()
        .required("You must enter a username.")
        .label("Username"),
    password: yup
        .string()
        .required("You must enter a Password.")
        .label("Password")
});

type Props = OwnProps;

const LoginForm: FunctionComponent<Props> = (props) => {
    if (props.state.isLoading) return <LoadingPage/>

    return (
        <div className={"outerLoginArea"}>
            <div className={"loginFormContainer"}>
                <div className={"fieldSection"}>
                    <InputField large type={"text"} onChange={() => {
                    }} style={{width: "85%"}} touched={false} errors={""} value={""} label={"Username"}
                                name={"username"}/>

                    <InputField large type={"password"} onChange={() => {
                    }} style={{width: "85%"}} touched={false} errors={""} value={""} label={"Password"}
                                name={"password"}/>

                    <span className={"errorMessage"}>{props.error}</span>

                    <Button className={"submitButton"} style={{
                        fontSize: "15px",
                        lineHeight: "17.5px",
                        padding: "12.5px",
                        width: "100%",
                        borderRadius: "10px",
                    }}
                            onClick={() => {
                            }} type={"submit"} title={"Sign In"}/>
                </div>

                <div className={"socialButtonSection"}>
                    <SigninWithGoogle
                        href={"http://api.footyandsweep-dev.com:30389/oauth2/authorization/google?redirect_uri=http://www.footyandsweep-dev.com:3000/oauth/login"}/>
                    <SigninWithFacebook
                        href={"http://api.footyandsweep-dev.com:30389/oauth2/authorization/facebook?redirect_uri=http://www.footyandsweep-dev.com:3000/oauth/login"}/>
                </div>
            </div>

            <div className={"bottomSection"}>
                <span className={"text"}><b>No Account Yet? </b></span><span
                onClick={() => props.history.push("/signup")}
                className={"textLink"}><b>Create an Account Now!</b></span>
            </div>
        </div>
    );
};

const mapStateToProps = (state: RootState) => {
    return {
        state: state.loginForm
    }
};

function mapDispatchToProps(dispatch: any) {
    return {
        actions: bindActionCreators(AuthenticateActions as any, dispatch),
    };
}


export default connect(mapStateToProps, mapDispatchToProps)(LoginForm);