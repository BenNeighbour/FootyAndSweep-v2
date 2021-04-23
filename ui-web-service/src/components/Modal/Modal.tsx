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


import React, {Fragment, FunctionComponent, useEffect, useState} from 'react';
import "./Modal.scss";
import Button from "../Button/Button";

interface OwnProps {
    showing: boolean;
    setShowing: (value: boolean) => void;
    title: string;
    description: string;
    isForm?: boolean | false;
    shrinksOnMobile?: boolean | false;
}

type Props = OwnProps;

const Modal: FunctionComponent<Props> = (props) => {
    const [shouldRender, setRender] = useState<boolean>(props.showing);

    useEffect(() => {
        if (props.showing) setRender(true);
    }, [props.showing]);

    const onAnimationEnd = () => {
        if (!props.showing) setRender(false);
    };

    return (
        <Fragment>
            {
                shouldRender ? <div className={`modalContainer${props.shrinksOnMobile ? "-unshrinked" : ""}`}
                                    style={{animation: `${props.showing ? "fadeIn" : "fadeOut"} 0.35s`}}
                                    onAnimationEnd={onAnimationEnd} onClick={() => props.setShowing(false)}>
                    <div className={"modalWrapper"}>
                        <div className={"modal"} onClick={e => e.stopPropagation()}>
                            <div className={"titleSection"}>
                                <div className={"titleOpacity"}>
                                    <span className={"modalTitle"}>{props.title}</span>
                                </div>
                                <span className={"modalSubtitle"}>{props.description}</span>
                            </div>

                            <div className={"fieldSection"}>
                                {props.children}
                            </div>

                            <div className={"bottomSection"}>
                                <Button className={"submitButton"} type={props.isForm ? "submit" : null}
                                        title={props.title}/>
                            </div>
                        </div>
                    </div>
                </div> : null
            }
        </Fragment>
    );
};

export default Modal;
