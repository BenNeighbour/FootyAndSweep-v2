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
import styled from "styled-components";
import LoginForm from "./Login/LoginForm";
import SignupForm from "./Signup/SignupForm";
import {useHistory} from "react-router-dom";
import Footer from "../../components/Footer/Footer";

interface OwnProps {
}

type Props = OwnProps;

const PortalPage: FunctionComponent<Props> = (props) => {
    const history = useHistory<any>();

    const [historyPreviousState] = useState(history.location.state);
    const [isLoggingIn, setIsLoggingIn] = useState(true);

    useEffect(() => {
        if (history.location.state && history.location.state.errors) {
            let state = {...history.location.state};
            delete state.errors;

            history.replace({...history.location, state});
        }
    }, [history]);

    return (
        <>
            <Container>
                <LeftSection/>
                <RightSection>
                    {isLoggingIn ?
                        <LoginForm error={historyPreviousState !== undefined ? historyPreviousState.errors : null}
                                   setIsLoggingIn={(value: boolean) => setIsLoggingIn(value)}/> :
                        <SignupForm
                            error={historyPreviousState !== undefined ? historyPreviousState.errors : null}
                            setIsLoggingIn={(value: boolean) => setIsLoggingIn(value)}/>}
                    <footer>
                        <Footer/>
                    </footer>
                </RightSection>
            </Container>
        </>
    );
};

const Container = styled.div`
width: 100vw;
height: 100%;
display: flex;
flex-wrap: wrap;
box-sizing: border-box;
`;

const LeftSection = styled.div`
height: 100%;
box-sizing: border-box;
@media (min-width: 1280px) {
    flex-grow: 0;
    max-width: 50%;
    flex-basis: 50%;
}
`;

const RightSection = styled.div`
height: 100%;
margin: 0;
display: flex;
flex-direction: column;
width: 100%;
box-sizing: border-box;
@media (min-width: 1280px) {
    flex-grow: 0;
    max-width: 50%;
    flex-basis: 50%;
}
`;

export default PortalPage;
