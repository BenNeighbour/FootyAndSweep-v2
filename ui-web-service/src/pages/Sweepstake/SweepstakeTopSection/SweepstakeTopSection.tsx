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
import SearchBar from "../../../components/SearchBar/SearchBar";
import Button from "../../../components/Button/Button";
import "./SweepstakeTopSection.scss";

interface OwnProps {
    isMobile: boolean;
    setJoiningSweepstake: (value: boolean) => void;
    setCreatingSweepstake: (value: boolean) => void;
}

type Props = OwnProps;

const SweepstakeTopSection: FunctionComponent<Props> = (props) => {
    return (
        <div className={"topSection"}>
            <nav className={"navigationSection"}>
            </nav>
            <div className={"logoSection"}>
                <span>Logo Here</span>
            </div>
            <div className={"searchSection"}>
                <SearchBar onChange={() => {
                }} value={""}/>
            </div>
            <div className={"buttonSection"}>
                {props.isMobile ?
                    <Button onClick={() => props.setJoiningSweepstake(true)}
                            title={"Create Sweepstake"}
                            className={"createSweepstakeButton"}/> : undefined}

                <Button className={"joinSweepstakeButton"}
                        onClick={() => props.setJoiningSweepstake(true)}
                        title={"Join Sweepstake"}/>
                <Button className={"earnCoinsButton"} title={"Earn FootyCoins"}/>

                <div className={"settingsLink"}/>
            </div>
            <div className={"opacitySection"}>
                <h1 className={"title"}>Your Sweepstakes</h1>
            </div>
        </div>
    );
};

export default SweepstakeTopSection;
