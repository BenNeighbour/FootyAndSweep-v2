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
import styled from "styled-components";

interface OwnProps {
    setIsLoggingIn: (value: boolean) => void;
    isLoggingIn: true | false;
}

type Props = OwnProps;

const DontHaveAccount: FunctionComponent<Props> = (props) => {
        return (
            <DontHaveAccountText>{props.isLoggingIn ? "Don't have an account?" : "Already have an account?"} &#160;
                <DontHaveAccountLink
                    onClick={() => props.setIsLoggingIn(!props.isLoggingIn)}>{props.isLoggingIn ? "Sign up" : "Sign In"}</DontHaveAccountLink></DontHaveAccountText>
        );
    }
;

export default DontHaveAccount;

const DontHaveAccountText = styled.p`
text-align: left;
font-family: 'Open Sans', sans-serif;
color: #b5b5b5;
font-size: 14px;
font-weight: 400;
line-height: 21px;
`;

const DontHaveAccountLink = styled.a`
font-family: 'Open Sans', sans-serif;
color: #2d50cf;
font-size: 14px;
font-weight: 600;
line-height: 21px;
cursor: pointer;

&:hover
{
    text-decoration: underline;
    color: #2d50cf;
}
`;