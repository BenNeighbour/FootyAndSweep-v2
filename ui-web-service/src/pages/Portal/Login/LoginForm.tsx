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
import * as AuthenticateActions from "../../../redux/reducers/saga/authenticate/authenticateActions";
import {connect} from "react-redux";
import {RootState} from "../../../redux/rootReducer";
import {LoginAuthenticationReducerType} from "../../../redux/reducers/saga/authenticate";
import LoadingPage from "../../Loading/LoadingPage";
import * as yup from "yup";

interface OwnProps {
    state: LoginAuthenticationReducerType;
    actions: typeof AuthenticateActions;
    setIsLoggingIn: (value: boolean) => void;
    error?: string | null;
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
        <div>

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