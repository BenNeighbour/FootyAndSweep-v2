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

import React, { FunctionComponent } from 'react';
import styled, {keyframes} from "styled-components";

interface OwnProps {
    color: string;
}

type Props = OwnProps;

const Spinner: FunctionComponent<Props> = (props) => {
  return (
      <Wrapper>
        <RingSpinner color={props.color} />
      </Wrapper>
  );
};

const motion = (p: any) => keyframes`
  0% {
      transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
`

const Wrapper = styled.div`
  display: inline-block;
  position: relative;
  width: 100px;
height: 100px;
`

const RingSpinner = styled.div`
box-sizing: border-box;
display: block;
position: absolute;
width: 100px;
height: 100px;
margin: 6px;
border: 7px solid ${p => p.color};
border-radius: 50%;
animation: ${p => motion(p)} 1.2s cubic-bezier(0.5, 0, 0.5, 1) infinite;
border-color: ${p => p.color} transparent transparent transparent;
:nth-child(1) {
animation-delay: -0.45s;
}
:nth-child(2) {
animation-delay: -0.3s;
}
:nth-child(3) {
animation-delay: -0.15s;
}
`

export default Spinner;
