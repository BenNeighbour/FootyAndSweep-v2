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

const InputField: FunctionComponent<Props> = (props) => {
    return (
        <InputContainer>
            <FieldName>{props.name}</FieldName>
            <Input disabled={props.disabled || false} value={props.value} onChange={props.onChange}
                   placeholder={props.placeholder}/>
        </InputContainer>
    );
};

export default InputField;


const Input = styled.input`
box-sizing: border-box;
width: 100%;
border: 0;
outline: 0;
padding-left: 6.8px;
font-family: 'Open Sans', sans-serif;
font-size: 15px;
font-weight: 600;
font-stretch: normal;
font-style: normal;
line-height: normal;
letter-spacing: normal;
text-align: left;
color: #8e8e8e;
border-bottom: 3px solid red;
`;

const InputContainer = styled.div`
object-fit: fill;
width: 347px;
height: 61px;
padding: 0 68px 0 0;
border-radius: 5px;
background-color: #ffffff;
box-shadow: 0px 4px 10px 0px rgba(0,0,0,0.25);
-webkit-box-shadow: 0px 4px 10px 0px rgba(0,0,0,0.25);
-moz-box-shadow: 0px 4px 10px 0px rgba(0,0,0,0.25);
`;

const FieldName = styled.div`
width: 119px;
height: 15px;
margin: 11px 147px 5px 8px;
font-family: 'Open Sans', sans-serif;
font-size: 12px;
font-weight: 600;
font-stretch: normal;
font-style: normal;
line-height: normal;
letter-spacing: normal;
text-align: left;
color: #b5b5b5;
`;

