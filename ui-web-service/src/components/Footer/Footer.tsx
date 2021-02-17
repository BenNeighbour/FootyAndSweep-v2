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

const Footer: FunctionComponent<Props> = (props) => {
    return (
        <FooterContainer>
            <FooterContent>
                <CopyrightText>Copyright © 2021 FootyAndSweep. All rights reserved.</CopyrightText>
                <FooterLinkContainer>
                    <FooterLink href={"/"}>Home</FooterLink>
                    <VerticalBar/>
                    <FooterLink href={"/contact"}>Contact Us</FooterLink>
                    <VerticalBar/>
                    <FooterLink href={"/pricing"}>Pricing</FooterLink>
                    <VerticalBar/>
                    <FooterLink href={"/portal?signup"}>Sign Up</FooterLink>
                </FooterLinkContainer>
            </FooterContent>
        </FooterContainer>
    );
};

export default Footer;

const FooterContainer = styled.div`
font-family: "Open Sans","Helvetica","Arial",sans-serif;
line-height: 1.42857143;
font-size: 16px;
`;

const FooterContent = styled.div`
min-height: 50px;
width: 85%;
display: flex;
margin: 0 auto;
flex-direction: column-reverse;
padding: 30px 0;
justify-content: center;
align-items: center;
`;

const CopyrightText = styled.div`
font-size: 13px;
color: #445d6e;
text-align: center;
font-family: "Open Sans","Helvetica","Arial",sans-serif;
`;

const FooterLinkContainer = styled.div`
display: flex;
flex-wrap: wrap;
justify-content: center;
align-items: center;
margin: 0 0 10px 0;
`;

const FooterLink = styled.a`
font-size: 14px;
padding: 3px 0;
cursor: pointer;
text-decoration: none !important;
font-family: "Open Sans","Helvetica","Arial",sans-serif;
color: #445d6e !important;
`

const VerticalBar = styled.div`
background-color: #445d6e87;
margin: 0 15px;
height: 15px;
width: 2px;
`;