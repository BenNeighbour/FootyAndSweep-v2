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
import Navbar from "../../components/NavBar/Navbar";
import Footer from "../../components/Footer/Footer";

interface OwnProps {
}

type Props = OwnProps;

const Sweepstakes: FunctionComponent<Props> = (props) => {
    return (
        <SweepstakesContainer>
            <TopSection>
                <Navbar/>
                <TitleSection>
                    <TitleText>Your Sweepstakes</TitleText>
                </TitleSection>
            </TopSection>
            <CardContainer>
                <Card/>
                <Card/>
                <Card/>
                <Card/>
            </CardContainer>
            <Footer>

            </Footer>
        </SweepstakesContainer>
    );
};

export default Sweepstakes;

const SweepstakesContainer = styled.div`
width: auto;
height: auto;
object-fit: contain;
`;

const CardContainer = styled.div`
display: flex;
padding: 8px 16px 16px;
width: 100%;
height: 100%;
flex-wrap: wrap;
place-items: center;
justify-content: center;
box-sizing: border-box;
`;

const TitleText = styled.h1`
font-family: 'Open Sans', sans-serif;
color: #fff;
font-size: 42px;
font-weight: 400;
line-height: 1.2;
`;

const TopSection = styled.div`
background: linear-gradient(-90deg, #3e66fb, #2d50cf);
position: relative;
min-height: 370px;
padding: 0 0 60px 0;
color: #02203c;
display: flex;
flex-direction: column;
`;

const TitleSection = styled.div`
text-align: center;
place-items: center;
color: #fff;
margin: 0 auto;
padding: 0 20px;
flex: 1 1 auto;
display: flex;
align-items: center;  
`;
