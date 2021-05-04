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
import "./ProfileCard.scss";
import Tooltip from "../Tooltip/Tooltip";
import {ProfileData} from "../../redux/model";
import CountUp from "react-countup";

interface OwnProps {
    className?: string | "";
    profile: ProfileData;
}

type Props = OwnProps;

const ProfileCard: FunctionComponent<Props> = (props) => {
    return (
        <div className={props.className}>
            <div className={"profileCardContainer"}>
                <div className={"mainSection"}>
                    <div className={"profilePictureSection"}>
                        <img alt={""} src={props.profile.profilePicture} />
                    </div>
                    <div className={"metadataSection"}>
                        <div className={"usernameSection"}>
                            <span className={"username"}>{props.profile.username}</span>
                            <span className={"tag"}>@{props.profile.username.replace(" ", "")}</span>
                            <div className={"medals"}>
                                <Tooltip className={"bronzeMedal"} text={"FS"}/>
                                <Tooltip className={"silverMedal"} text={"FS"}/>
                                <Tooltip className={"goldMedal"} text={"FS"}/>
                            </div>
                        </div>
                        <div className={"balanceSection"}>
                            <span className={"balance"}><CountUp end={props.profile.balance} duration={2} /></span>
                            <br/>
                            <span className={"currency"}>FootyCoins</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ProfileCard;
