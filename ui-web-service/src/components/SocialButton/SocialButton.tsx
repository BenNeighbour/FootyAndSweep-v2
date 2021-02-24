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
    style?: React.CSSProperties | {};
    href: string;
}

type Props = OwnProps;

const SocialButton: FunctionComponent<Props> = (props) => {
    return (
        <ButtonContainer onClick={() => window.location.replace(props.href)} style={props.style}>
            {props.children}
        </ButtonContainer>
    );
};

export default SocialButton;

const ButtonContainer = styled.div`
overflow: hidden;
position: relative;
height: fit-content;
width: fit-content;
object-fit: fill;
border-radius: 20px;
border: 3px #f2f2f2 solid;
font-family: "Open Sans","Helvetica","Arial",sans-serif;
margin: 0 0.75em;
cursor: pointer;
background-color: transparent;

&:hover {
    border: 3px transparent solid;
    background-color: transparent;
    box-shadow: 1px 0px 400px 1px rgba(0,0,0,0.61);
    -webkit-box-shadow: 1px 0px 400px 1px rgba(0,0,0,0.61);
    -moz-box-shadow: 1px 0px 400px 1px rgba(0,0,0,0.61);
}
    transition: background-color .2s,padding .2s,box-shadow .2s,border 2s;
`;