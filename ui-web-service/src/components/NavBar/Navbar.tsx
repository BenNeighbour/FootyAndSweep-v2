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
import Button from "../Button/Button";
import DummyLogo from "./dummy-logo.jpg";

interface OwnProps {
}

type Props = OwnProps;

const Navbar: FunctionComponent<Props> = (props) => {
    return (
        <NavbarContainer>
            <NavbarLogo alt={""} src={DummyLogo}/>
            <NavbarLinkSectionLeft>
                <NavbarLink>Home</NavbarLink>
                <NavbarLink>Contact Us</NavbarLink>
            </NavbarLinkSectionLeft>

            <NavbarLinkSectionRight>
                <NavbarLink><Button title={"Login"} style={{
                    fontSize: "16px",
                    width: "fit-content",
                    height: "fit-content",
                    padding: "3px 16px"
                }}/></NavbarLink>
                <NavbarLink>Signup</NavbarLink>
            </NavbarLinkSectionRight>
        </NavbarContainer>
  );
};

const NavbarContainer = styled.div`
display: inline-block;
height: 60px;
align-items: center;
padding 0 3em;
margin: 7px 0;
position: fixed;
width: 100%;
box-sizing: border-box;
object-fit: contain;
overflow: hidden;
`;

const NavbarLink = styled.a`
cursor: pointer;
margin: 0 2em 0 0;
color: #3e66fb;
&:hover {
    border-bottom: 1px solid #3e66fb;
    transform: scaleX(1);
}   
    transition: transform 200ms ease-in-out;
`;

const NavbarLinkSectionLeft = styled.div`
display: flex;
align-items: center;
font-family: 'Open Sans', sans-serif;
font-size: 15px;
font-weight: 600;
color: #808080;
height: 100%;
float: left;
`;

const NavbarLogo = styled.img`
display: flex;
align-items: center;
color: #808080;
height: 90%;
float: left;
padding-right: 3em;
`;

const NavbarLinkSectionRight = styled.div`
display: flex;
align-items: center;
font-family: 'Open Sans', sans-serif;
font-size: 15px;
font-weight: 600;
color: #808080;
height: 100%;
float: right;
`;

export default Navbar;
