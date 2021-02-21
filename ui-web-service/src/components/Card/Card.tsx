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
            dfd
        </CardContainer>
    );
};

export default Card;

const CardContainer = styled.div`
background-color: #fff;
position: relative;
transition: box-shadow .2s;
border-radius: 5px;
box-shadow: 0 0 50px -10px rgb(0 0 0 / 10%);
display: flex;  
flex-direction: column;
align-items: center;
border-radius: 5px;
font-family: 'Open Sans', sans-serif;

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