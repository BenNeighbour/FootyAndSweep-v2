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
    style?: React.CSSProperties | {}
}

type Props = OwnProps;

const LargeInputField: FunctionComponent<Props> = (props) => {
    return (
        <InputContainer>
            <FieldName>{props.name}</FieldName>
            <Input disabled={props.disabled || false} value={props.value} onChange={props.onChange}
                   placeholder={props.placeholder}/>
        </InputContainer>
    );
};

export default LargeInputField;

const Input = styled.input`
box-sizing: border-box;
border: 0;
outline: 0;
font-family: 'Open Sans', sans-serif;
font-size: 20px;
font-weight: 600;
font-stretch: normal;
font-style: normal;  
line-height: 1.5;
letter-spacing: normal;
text-align: left;
width: 100%;
color: #02203c;
border: none;
outline: none;
padding: 0 1em;
`;

const InputContainer = styled.div`
overflow: hidden;
position: relative;
height: 80px;
object-fit: contain;
border-radius: 5px;
font-family: "Open Sans","Helvetica","Arial",sans-serif;
line-height: 1.42857143;
background-color: #ffffff;
box-shadow: 0px 4px 10px 0px rgba(0,0,0,0.25);
-webkit-box-shadow: 0px 4px 10px 0px rgba(0,0,0,0.25);
-moz-box-shadow: 0px 4px 10px 0px rgba(0,0,0,0.25);
`;

const FieldName = styled.div`
padding-top: 1em;
padding-left: 1em;
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

