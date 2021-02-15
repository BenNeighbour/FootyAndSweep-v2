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
import Button from "../../components/Button/Button";
import SocialButton from "../../components/SocialButton/SocialButton";
import Facebook from "../../icons/Facebook";
import {FcGoogle} from "react-icons/all";
import InputField from "../../components/InputField/InputField";

interface OwnProps {
    onSubmit: () => void;
}

type Props = OwnProps;

const SignupForm: FunctionComponent<Props> = (props) => {
    return (
        <FormContainer>
            <Form>
                <InputField name={"Username"}
                            type={"text"}
                            onChange={() => {
                            }}
                            value={"dsfsdf"}/>

                <InputField name={"Email Address"}
                            type={"email"}
                            onChange={() => {
                            }}
                            value={"benneighbour007@gmail.com"}/>

                <InputField includePasswordStrengthChecker name={"Password"}
                            type={"password"}
                            onChange={() => {
                            }}
                            value={""}/>

                <Button title={"Create Account"} onClick={props.onSubmit} style={{
                    width: "100%",
                    fontSize: "15px",
                    height: "45px",
                    marginBottom: "1em"
                }}/>

                <OrSignInWithText>OR</OrSignInWithText>

                <SocialButtonSection>
                    <SocialButton><FcGoogle style={{
                        padding: "5px 25px"
                    }} size={75}/>
                    </SocialButton>
                    <SocialButton>
                        <Facebook style={{
                            padding: "5px 25px"
                        }} width={"75px"} height={"75px"}/>
                    </SocialButton>
                </SocialButtonSection>
            </Form>
        </FormContainer>
    );
};

export default SignupForm;

const FormContainer = styled.div`
margin: auto;
display: flex;
flex-grow: 1;
height: 100vh;
align-items: center;
place-items: center;
`;

const Form = styled.form`
margin: auto;
flex-basis: 725px;
padding-left: 1em;
padding-right: 1em;
padding-bottom: 5em;
padding-top: 5em;
`;

const SocialButtonSection = styled.div`
display: flex;
margin: auto;
justify-content: center;
`;

const OrSignInWithText = styled.div`
text-align: center;
font-family: 'Open Sans', sans-serif;
color: #b5b5b5;
`;
