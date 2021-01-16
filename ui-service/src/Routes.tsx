/*
 *   Copyright 2020 FootyAndSweep
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
import {ConnectedRouter} from 'connected-react-router';
import {Route, Switch} from 'react-router';
import {history, initialize} from './store';
import {Provider} from "react-redux";
import HomePage from "./pages/HomePage";
import Button from "./components/Button/Button";

interface OwnProps {
}

type Props = OwnProps;

const Routes: FunctionComponent<Props> = (props) => {
    return (
        <Provider store={initialize()}>
            <ConnectedRouter history={history}>
                <Switch>
                    <Route exact path={"/home"} render={HomePage}/>
                    <Route render={() => <div><Button label={"Login"} onClick={() => {
                        window.location.replace("http://api.footyandsweep-dev.com:30077/oauth2/authorize/google?redirect_uri=http://www.footyandsweep-dev.com:3000/home")
                    }}/></div>}/>
                </Switch>
            </ConnectedRouter>
        </Provider>
    );
};

export default Routes;
