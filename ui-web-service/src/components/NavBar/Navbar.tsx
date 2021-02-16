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
import styled from "styled-components";

interface OwnProps {}

type Props = OwnProps;

const Navbar: FunctionComponent<Props> = (props) => {
  return (
      <NavbarContainer>
        <NavbarLinkSection>
            <NavbarLink>Home</NavbarLink>
        </NavbarLinkSection>
      </NavbarContainer>
  );
};

const NavbarContainer = styled.div`
height: 60px;
padding 0 1em;
position: fixed;
width: 100%;
box-sizing: border-box;
object-fit: contain;
overflow: hidden;
`;

const NavbarLink = styled.a`
cursor: pointer;
&:hover {
    color: black;
}
`;

const NavbarLinkSection = styled.div`
display: flex;
align-items: center;
font-family: 'Open Sans', sans-serif;
font-size: 17px;
font-weight: 600;
color: #808080;
height: 100%;
`;

export default Navbar;
