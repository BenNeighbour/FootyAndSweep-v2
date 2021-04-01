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


import React, {Fragment, FunctionComponent} from 'react';
import "./Modal.scss";
import CreateSweepstake from "../../pages/CreateSweepstake/CreateSweepstake";
import Button from "../Button/Button";

interface OwnProps {
    showing: boolean;
    setShowing: (value: boolean) => void;
}

type Props = OwnProps;

const Modal: FunctionComponent<Props> = (props) => {
    return (
        <Fragment>
            {
                props.showing ? <div className={"modalContainer"} onClick={() => props.setShowing(false)}>
                    <div className={"modalWrapper"}>
                        <div className={"modal"} onClick={e => e.stopPropagation()}>
                            <div className={"titleSection"}>
                                <span className={"modalTitle"}>Create a Sweepstake</span>
                                <span
                                    className={"modalSubtitle"}>Fill the following fields to create a new sweepstake</span>
                            </div>

                            <div className={"fieldSection"}>
                                <CreateSweepstake/>
                            </div>

                            <div className={"bottomSection"}>
                                <Button onClick={() => {
                                }} style={{
                                    // padding: "5px 20px"
                                }} title={"Create Sweepstake"}/>
                            </div>
                        </div>
                    </div>
                </div> : null
            }
        </Fragment>
    );
};

export default Modal;