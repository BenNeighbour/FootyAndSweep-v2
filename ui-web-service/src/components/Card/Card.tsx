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

const Card: FunctionComponent<Props> = (props) => {
    return (
        <CardContainer>
            <CardOutline>
                <EventSection>
                    <EventText>Aston Villa vs Wolves</EventText>
                </EventSection>

                <br />

                {/* Metadata section here */}
                <SweepstakeMetadataSection>
                    <SweepstakeNameText>Sweepstake Name</SweepstakeNameText>
                    <SweepstakeTypeText>Number of Corners F/T</SweepstakeTypeText>
                    <OtherMetadataText>Stake: 1.00 | Prize: 12.00</OtherMetadataText>
                </SweepstakeMetadataSection>
            </CardOutline>
        </CardContainer>
    );
};

export default Card;

const CardContainer = styled.div`
margin: 0;
padding: 8px;
box-sizing: border-box;
    
@media (min-width: 960px) {
    flex-grow: 0;
    max-width: 33.333333%;
    flex-basis: 33.333333%;
}
@media (min-width: 1280px) {
    flex-grow: 0;
    max-width: 25%;
    flex-basis: 25%;
}

flex-grow: 0;
max-width: 100%;
flex-basis: 100%;
`;

const CardOutline = styled.div`
display: block;
background-color: #fff;
position: relative;
box-sizing: border-box;
height: fit-content;
border-radius: 5px;
font-family: 'Open Sans', sans-serif;
box-shadow: 0px 4px 10px 0px rgba(0,0,0,0.25);
-webkit-box-shadow: 0px 4px 10px 0px rgba(0,0,0,0.25);
-moz-box-shadow: 0px 4px 10px 0px rgba(0,0,0,0.25);
padding: 0 1em;
`;

const SweepstakeMetadataSection = styled.div`
display: block;
text-align: center;
`;

const SweepstakeNameText = styled.div`
font-weight: 400;
font-size: 19px;
height: fit-content;
padding: 2.5px  0 0 0;
`;

const SweepstakeTypeText = styled.div`   
font-weight: 600;
font-size: 15px;
padding: 2.5px 0;
`;

const OtherMetadataText = styled.div`
font-weight: 400;
font-size: 13px;
padding: 2.5px 0;
`;

const EventSection = styled.div`
display: flex;
padding: 16px;
align-items: center;
`;

const EventText = styled.div`
font-size: 14px;
font-weight: 400;
`;