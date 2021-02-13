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
    value: string;
    placeholder: string;
    type: 'text' | 'number' | 'password';
    size: string;
    name: string;
    onChange: () => void;
    disabled?: boolean;
}

type Props = OwnProps;

const LargeInputField: FunctionComponent<Props> = (props) => {
    return (
        <InputContainer>
            <FieldName>{props.name}</FieldName>
            <Input disabled={props.disabled || false} value={props.value} onChange={props.onChange}
                   placeholder={props.placeholder}/>
                   <InputLine/>
        </InputContainer>
    );
};

export default LargeInputField;

const InputLine = styled.div`
position: absolute;
height: 2px;
width: calc(100% - 20px);
bottom: 17%;
left: 10px;
background: #6772e5;
transform: scaleX(0);
transition: transform .5s;
transform-origin: 0% 50%;
`;

const Input = styled.input`
box-sizing: border-box;
width: 100%;
border-top: 0;
border-left: 0;
border-right: 0;
outline: 0;
padding: 6.8px 0 0 10.9px;
font-family: 'Open Sans', sans-serif;
font-size: 20px;
font-weight: 600;
font-stretch: normal;
font-style: normal;
line-height: 1.5;
letter-spacing: normal;
text-align: left;
&:after {
    content: '';
    border-bottom: 2px solid #898989;
    // display: 'block';
    width: 50%;
    position: relative;
}
`;

const InputContainer = styled.div`
object-fit: fill;
width: 472px;
height: 83px;
border-radius: 5px;
background-color: #ffffff;
box-shadow: 0px 4px 10px 0px rgba(0,0,0,0.25);
-webkit-box-shadow: 0px 4px 10px 0px rgba(0,0,0,0.25);
-moz-box-shadow: 0px 4px 10px 0px rgba(0,0,0,0.25);
`;

const FieldName = styled.div`
width: 161.9px;
height: 20.4px;
padding-top: 15px;
padding-left: 10px;
font-family: 'Open Sans', sans-serif;
font-size: 17px;
font-weight: 600;
font-stretch: normal;
font-style: normal;
line-height: normal;
letter-spacing: normal;
text-align: left;
color: #b5b5b5;
`;

