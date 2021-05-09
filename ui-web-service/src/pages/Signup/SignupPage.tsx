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
import {useHistory} from "react-router-dom";
import SignupForm from "../../views/SignupForm/SignupForm";
import "./SignupPage.scss";
import Logo from "../../icons/logo.png";

interface OwnProps {
}

type Props = OwnProps;

const SignupPage: FunctionComponent<Props> = (props) => {
    const history = useHistory<any>();

    const [historyPreviousState] = useState<any>(history.location.state);

    useEffect(() => {
        if (history.location.state && history.location.state.errors) {
            let state = {...history.location.state};
            delete state.errors;

            history.replace({...history.location, state});
        }
    }, [history]);

    return (
        <div className={`signupContainer`}>
            <div className={"mainSection"}>
                <div className={"brandSection"}>
                    <img src={Logo} alt={""} className={"smallLogo"}/>
                    <div>
                        <span className={"description"}>The best Sweepstakes Platform on the Planet.</span>
                    </div>
                </div>
                <SignupForm history={history}
                            error={historyPreviousState !== undefined ? historyPreviousState.errors : null}/>
            </div>
        </div>
    );
};

export default SignupPage;
