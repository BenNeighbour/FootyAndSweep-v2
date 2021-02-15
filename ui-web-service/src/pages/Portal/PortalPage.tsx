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

import React, {FunctionComponent, useState} from 'react';
import styled from "styled-components";
import LoginForm from "./LoginForm";
import SignupForm from "./SignupForm";

interface OwnProps {
}

type Props = OwnProps;

const PortalPage: FunctionComponent<Props> = (props) => {
    const [isLoggingIn, setIsLoggingIn] = useState(true);

    return (
        <Container>
            <LeftSection/>
            <RightSection>
                {!isLoggingIn ? <LoginForm onSubmit={() => setIsLoggingIn(true)}/> :
                    <SignupForm onSubmit={() => setIsLoggingIn(false)}/>}
            </RightSection>
        </Container>
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
width: 100%;
box-sizing: border-box;
@media (min-width: 1280px) {
    flex-grow: 0;
    max-width: 50%;
    flex-basis: 50%;
}
`;

const RightSection = styled.div`
height: 100%;
width: 100%;
box-sizing: border-box;
@media (min-width: 1280px) {
    flex-grow: 0;
    max-width: 50%;
    flex-basis: 50%;
}
`;

export default PortalPage;
