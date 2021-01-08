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
import {joinSweepstake} from "../services/SweepstakeService";
import NavBar from "../components/NavBar/NavBar";
import "./HomePage.css";
import SweepstakeCard from "../components/SweepstakeCard/SweepstakeCard";
import Button from "../components/Button/Button";
import {Col, Row} from "react-bootstrap";

interface OwnProps {
}

type Props = OwnProps;

const HomePage: FunctionComponent<Props> = (props) => {
    joinSweepstake(undefined);
    return (
        <>
            <NavBar/>
            {/*Tabs Here*/}
            <div className={"container"}>
                <Row className={"row"}>
                    <Col sm>
                        <div className="d-sm-flex justify-content-between align-items-center mb-4">
                            <h1 className="text-dark mb-0">Home</h1>
                        </div>
                    </Col>
                    <Col sm>
                        <Button label={"Create Sweepstake"}/>
                    </Col>
                    <Col sm>
                        <Button label={"Join Sweepstake"}/>
                    </Col>
                </Row>
                <Row className={"row"}>
                    <Col>
                        <div className="row">
                            <SweepstakeCard/>
                        </div>
                    </Col>
                </Row>
            </div>
        </>
    );
};


export default HomePage;
