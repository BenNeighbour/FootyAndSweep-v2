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
import {joinSweepstake} from "../../services/SweepstakeService";
import NavBar from "../../components/NavBar/NavBar";

interface OwnProps {
}

type Props = OwnProps;

const HomePage: FunctionComponent<Props> = (props) => {
    joinSweepstake(undefined);
    return (
        <>
            <NavBar/>
            <div className={"container"}>

            </div>
        </>
    );
};


export default HomePage;
