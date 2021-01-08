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
import styled from "styled-components";

const ButtonStyled = styled.button`
  font-size: .8rem;
  border-radius: 10px;
  outline: none;
  border: none;
  padding: .75rem 1rem;
  margin-bottom: 2vh;
  background: rgb(0,155,255);
  background: linear-gradient(90deg, rgba(0,155,255,1) 0%, rgba(0,155,255,0.8211659663865546) 100%);
  color: #fff!important;
  box-shadow: 0 0 0 .2rem rgba(105,136,228,.5);
`

interface OwnProps {
    label: string;
}

type Props = OwnProps;

const Button: FunctionComponent<Props> = (props) => {
    return (
        <ButtonStyled>{props.label}</ButtonStyled>
    );
};

export default Button;
