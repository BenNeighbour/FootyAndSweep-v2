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
import PortalPage from "./pages/Portal/PortalPage";
import Sweepstakes from "./pages/Sweepstake/Sweepstakes";
import OAuthRedirect from "./pages/Portal/OAuth/OAuthRedirect";
import CreateSweepstake from "./pages/CreateSweepstake/CreateSweepstake";
import LoadingPage from "./pages/Loading/LoadingPage";

interface OwnProps {
}

type Props = OwnProps;

const Routes: FunctionComponent<Props> = (props) => {
    return (
        <Route>
            <Switch>
                <Route>
                    <Route
                        component={LoadingPage} exact path="/"/>
                    <Route
                        component={OAuthRedirect} exact path="/oauth/login"/>
                    <Route
                        component={PortalPage} exact path="/portal"/>
                    <Route
                        component={Sweepstakes} exact path="/sweepstakes"/>
                    <Route
                        component={CreateSweepstake} exact path="/sweepstakes/create"/>
                </Route>
            </Switch>
        </Route>
    );
};

export default Routes;

