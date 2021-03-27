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
import "./AdvertisementCard.scss";
import AdImage from "./dummy-ad.png";
import Tooltip from "../Tooltip/Tooltip";

interface OwnProps {
    isMobile?: boolean | true;
    advertiserLink: string;
}

type Props = OwnProps;

const AdvertisementCard: FunctionComponent<Props> = (props) => {
    return (
        <div className={`adArea${!props.isMobile ? "-desktop" : ""}`}>
            <div className={"borderContainer"}>
                <div className={"borderLine"}/>
            </div>
            <div className={"adContainer"}>
                <div className={"mainSection"}>
                    <div className={"pictureSection"}>
                        {!props.isMobile ? <Tooltip className={"advertisementDesktop"} text={"ADVERTISEMENT"} colorCode={"#242C37"}/> : undefined}
                        <img alt={""} src={AdImage}/>
                    </div>
                    <div className={"detailsSection"}>
                        <div className={"titleSection"}>
                            <span className={"advertisementTitle"}>{props.isMobile ? "AlgoExpert" : "The Ultimate SWE Interview Preparation Kit"}</span>
                            {props.isMobile ? <Tooltip text={"AD"} colorCode={"#242C37"}/> : undefined}
                        </div>
                        <span className={"advertisementSubtitle"}>{props.isMobile ? "AlgoExpert | Ace the Coding Interviews with absolute flying colors" : "AlgoExpert | Ace the Coding Interviews"}</span>
                        <span className={"advertisementDetailsDesktop"}>The sponsor/advertiser can put whatever bullshit they would like here as long as it satifies the sponsor/advertiser’s needs which is to get traffic which is why a good description is here and a link to their homepage is below</span>
                        <div className={"desktopCallToAction"}>
                            <span onClick={() => document.location.href = props.advertiserLink} className={"advertisementLink"}>Go to AlgoExpert →</span>
                        </div>
                    </div>
                </div>
            </div>
            <div className={"borderContainer"}>
                <div className={"borderLine"}/>
            </div>
        </div>
    );
};

export default AdvertisementCard;
