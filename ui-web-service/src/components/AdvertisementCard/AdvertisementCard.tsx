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

interface OwnProps {
}

type Props = OwnProps;

const AdvertisementCard: FunctionComponent<Props> = (props) => {
    return (
        <React.Fragment>
            <div className={"border-line"} />
            <div className={"adContainer"}>
                <div className={"mainSection"}>
                    <div className={"pictureSection"}>
                        <img alt={""} src={AdImage}/>
                    </div>
                    <div className={"detailsSection"}>
                        <span className={"advertisementTitle"}>The Ultimate SWE Interview Prep</span>
                        <span className={"advertisementSubtitle"}>AlgoExpert | Ace the Coding Interviews with absolute flying colors</span>
                    </div>
                </div>
            </div>
            <hr className={"border-line"} />
        </React.Fragment>
    );
};

export default AdvertisementCard;
