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
}

type Props = OwnProps;

const Button: FunctionComponent<Props> = (props) => {
    return (
        <ButtonContainer>
            <ButtonText>Hello</ButtonText>
        </ButtonContainer>
    );
};

const ButtonContainer = styled.div`
display: 'flex';
cursor: pointer;
width: 121px;
height: 40px;
margin: 70px 17px 42px;
padding: 11px 15.1px;
border-radius: 5px;
place-items: 'center';
text-align: 'center';
background-color: #3e66fb;
line-height: 2;
display: inline-block;
vertical-align: middle;
transition: background-color .2s,padding .4s,box-shadow .2s,border .2s;
&:hover {
    box-shadow: 0 2px 10px 2px rgba(0, 0, 0, 0.25);
}
`;

const ButtonText = styled.div`
place-items: 'center';
text-align: 'center';
height: 100%;
font-family: 'Open Sans', sans-serif;
font-size: 20px;
font-weight: 600;
font-stretch: normal;
font-style: normal;
text-align: center;
color: #ffffff;
`;

export default Button;
