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
import {Redirect, RouteComponentProps, useLocation} from "react-router-dom";
import {RootState} from "../../redux/rootReducer";
import {bindActionCreators} from "redux";
import * as UserAuthenticationActions from "../../redux/reducers/saga/authenticate/authenticateActions";
import {connect} from "react-redux";

interface OwnProps extends RouteComponentProps<any> {
    state: RootState;
    userAuthenticationActions: typeof UserAuthenticationActions;
}

type Props = OwnProps;

function useQuery() {
    return new URLSearchParams(useLocation().search);
}

const OAuthRedirect: FunctionComponent<Props> = (props) => {
    let query = useQuery();

    if (query.get("error") !== null) {
        return (
            <Redirect to={{
                pathname: "/login",
                state: {errors: query.get("error")}
            }}/>
        );
    }

    if (query.get("user_id") !== null) {
        /* Dispatch login success */
        props.userAuthenticationActions.oauthAuthenticationSuccessAction(query.get("user_id"));

        return (
            <Redirect to={{
                pathname: "/sweepstakes",
            }}/>
        )
    }

    return (
        <Redirect to={{
            pathname: "/login",
            state: {errors: "Something's not right... Try again later!"}
        }}/>
    );
};

const mapStateToProps = (state: RootState) => {
        return {
            state
        }
    }
;

function mapDispatchToProps(dispatch: any) {
    return {
        userAuthenticationActions: bindActionCreators(UserAuthenticationActions as any, dispatch),
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(OAuthRedirect);
