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

const NavbarStyled = styled.div`
  height: 4.375rem;
  //background: linear-gradient(90deg, rgba(0, 155, 255, 1) 0%, rgba(0, 155, 255, 0.8211659663865546) 100%) left top #ffffff no-repeat;
  //background-size: 100% 5px;
  background-color: #fff !important;
  box-shadow: 0 .15rem 1.75rem 0 rgba(58, 59, 69, .15) !important;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
`;

interface OwnProps {
}

type Props = OwnProps;

const NavBar: FunctionComponent<Props> = (props) => {
    return (
        <NavbarStyled>
        </NavbarStyled>
    );
};

export default NavBar;