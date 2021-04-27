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


import React, {FunctionComponent, useEffect, useState} from 'react';
import {Redirect, Route} from 'react-router-dom';
import {checkIsAuthenticated} from "../services/sweepstakeService";
import LoadingPage from "../pages/Loading/LoadingPage";

const PrivateRoute: FunctionComponent<any> = ({component, ...rest}) => {
    const [isAuthenticated, setIsAuthenticated] = useState<null | boolean>(null);

    useEffect(() => {
        const callback = async () => {
            await checkIsAuthenticated().then(response => {
                setIsAuthenticated(response.status === 200);
            }).catch(_ => setIsAuthenticated(false));
        };

        callback();
    });

    if (isAuthenticated === null) {
        return <LoadingPage />
    } else if (!isAuthenticated) {
        return <Redirect to={"/login"} />
    }

    return <Route {...rest} render={(props) => React.createElement(component, props)}/>;
};

export default PrivateRoute;
