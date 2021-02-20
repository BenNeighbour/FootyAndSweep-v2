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
import {Route, Switch} from "react-router";
import HomePage from "./pages/Home/HomePage";
import PortalPage from "./pages/Portal/PortalPage";
import PrivateRoute from "./other/PrivateRoute";
import Axios from "axios";
import Sweepstakes from "./pages/Sweepstake/Sweepstakes";

interface OwnProps {
}

type Props = OwnProps;

const Routes: FunctionComponent<Props> = (props) => {
    return (
        <Route>
            <Switch>
                <Route>
                    <Route
                        component={HomePage} exact path="/"/>
                    <Route
                        component={PortalPage} exact path="/portal"/>
                    <PrivateRoute
                        component={Sweepstakes} isAuthenticated={checkIsAuthenticated()} exact path="/sweepstakes"/>
                </Route>
            </Switch>
        </Route>
    );
};

const checkIsAuthenticated = (): boolean => {
    let isAuthenticated: boolean = false;

    Axios({
        method: "get",
        url: "http://api.footyandsweep-dev.com:30389/com.footyandsweep.AuthenticationService/user/me",
        withCredentials: true
    }).then(_ => isAuthenticated = true).catch(_ => isAuthenticated = false);

    return isAuthenticated;
}

export default Routes;

