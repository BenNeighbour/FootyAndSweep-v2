/*
 *   Copyright 2020 FootyAndSweep
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
import Button from "../Button/Button";
import styled from "styled-components";

const PStyled = styled.p`
  margin-top: 0;
  margin-bottom: 1rem;
`;

const CardContainerStyled = styled.div`
  position: relative;
  width: 100%;
  padding-right: 15px;
  padding-left: 15px;
`;

const CardOutlineStyled = styled.div`
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0,0,0,.075);
  background: linear-gradient(90deg, rgba(0, 155, 255, 1) 0%, rgba(0, 155, 255, 0.8211659663865546) 100%) left top #ffffff no-repeat;
  background-size: 100% 6px;
  padding: 30px;
  overflow: hidden;
  position: relative;
  text-align: center;
  border-bottom: 1px solid rgba(0,0,0,.1);
`;

const SweepstakeTitleStyled = styled.h4`
  margin-top: 0;
  margin-bottom: .5rem;
  font-weight: 500;
  line-height: 1.2;
`;

interface OwnProps {
}

type Props = OwnProps;

const SweepstakeCard: FunctionComponent<Props> = (props) => {
    return (
        <CardContainerStyled>
            <CardOutlineStyled>
                <SweepstakeTitleStyled>Jon's Corner Crazy</SweepstakeTitleStyled>
                <PStyled>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</PStyled>
                <Button label={"Buy Tickets"}/>
            </CardOutlineStyled>
        </CardContainerStyled>
    );
};

export default SweepstakeCard;
