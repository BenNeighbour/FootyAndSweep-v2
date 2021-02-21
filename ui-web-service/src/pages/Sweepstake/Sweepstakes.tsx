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
import Card from "../../components/Card/Card";
import styled from "styled-components";

interface OwnProps {
}

type Props = OwnProps;

const Sweepstakes: FunctionComponent<Props> = (props) => {
    return (
        <SweepstakesContainer>
            <CardContainer>
                <Card/>
                <Card/>
                <Card/>
                <Card/>
                <Card/>
                <Card/>
                <Card/>
            </CardContainer>
        </SweepstakesContainer>
    );
};

export default Sweepstakes;

const SweepstakesContainer = styled.div`
width: auto;
height: auto;
display: flex;
overflow: hidden;
object-fit: contain;
padding: 10px;
`;

const CardContainer = styled.div`
height: 100%;
display: flex;
padding: 8px 16px 16px;
flex-wrap: wrap;
box-sizing: border-box;
width: calc(100% + 16px);
flex-wrap: wrap;
gap: 12px;
&>* {
    flex: 0 0 33.3333%;
}
`;
