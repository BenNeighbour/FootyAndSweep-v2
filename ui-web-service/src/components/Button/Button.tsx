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
    title: string;
    type?: 'submit' | null;
    onClick?: (value: any) => void;
    disabled?: boolean;
    style?: React.CSSProperties | {}
}

type Props = OwnProps;

const Button: FunctionComponent<Props> = (props) => {
    return (
        <ButtonContainer type={props.type || undefined} style={props.style} disabled={props.disabled || false}
                         onClick={props.onClick}>
            {props.title}
        </ButtonContainer>
    );
};

const ButtonContainer = styled.button`
border: 0;
outline: 0;
display: 'flex';
cursor: pointer;
width: 121px;
height: 40px;
border-radius: 5px;
place-items: 'center';
text-align: 'center';
background-color: #3e66fb;
line-height: 2;
display: inline-block;
vertical-align: middle;
box-shadow: 0 2px 5px 0 rgb(0 0 0 / 50%);
transition: background-color .2s,padding .4s,box-shadow .2s,border .2s;
place-items: 'center';
text-align: 'center';
font-size: 20px;
font-weight: 600;
color: #ffffff;
font-family: 'Open Sans', sans-serif;
&:disabled {
    color: rgba(0, 0, 0, 0.26);
    transition: none;
    box-shadow: none;
    background-color: rgba(0, 0, 0, 0.12);
    cursor: default;
}
&:hover:enabled {
    background-color: #2d50cf;
    box-shadow: 0 2px 10px 0 rgb(0 0 0 / 50%);
}
background-color: #3e66fb;
transition: background-color .2s,padding .4s,box-shadow .2s,border .2s;
`;


export default Button;
