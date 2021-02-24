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

const Navbar: FunctionComponent<Props> = (props) => {
    return (
        <NavbarContainer>
            <NavbarLinkSection>
                {/*<NavbarLink href={"/home"}>Home</NavbarLink>*/}
                {/*<NavbarLink href={"/sweepstakes"}>Your Sweepstakes</NavbarLink>*/}
                {/*<NavbarLink href={"/sweepstakes/create"}>Create a Sweepstake</NavbarLink>*/}
                {/*<NavbarLink href={"/settings"}>Account Settings</NavbarLink>*/}
            </NavbarLinkSection>
        </NavbarContainer>
    );
};

const NavbarContainer = styled.div`
display: flex;
justify-content: center;
margin: 20px;
align-items: flex-start;
`;

const NavbarLinkSection = styled.div`
display: flex;
align-items: center;
flex-direction: row;
flex: 0 0 auto;
margin: 10px 80px 0;
`;

const NavbarLink = styled.a`
display: inline-block;
color: #fff;
font-size: 16px;
text-decoration: none;
font-family: 'Open Sans', sans-serif;
padding: 0;
background-color: transparent;
border: none;
padding: 0 15px;
cursor: pointer;
`;;

export default Navbar;
