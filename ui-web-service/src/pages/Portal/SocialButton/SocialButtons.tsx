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
import SocialButton from "../../../components/SocialButton/SocialButton";
import {FcGoogle} from "react-icons/all";
import Facebook from "../../../icons/Facebook";
import styled from "styled-components";

interface OwnProps {
}

type Props = OwnProps;

const SocialButtonSection: FunctionComponent<Props> = (props) => {
    return (
        <SocialButtons>
            <SocialButton href={"http://api.footyandsweep-dev.com:30389/oauth2/authorize/google?redirect_uri=http://www.footyandsweep-dev.com:3000/oauth/login"}><FcGoogle style={{
                padding: "5px 25px"
            }} size={75}/>
            </SocialButton>
            <SocialButton href={"http://api.footyandsweep-dev.com:30389/oauth2/authorize/facebook?redirect_uri=http://www.footyandsweep-dev.com:3000/oauth/login"}>
                <Facebook style={{
                    padding: "5px 25px"
                }} width={"75px"} height={"75px"}/>
            </SocialButton>
        </SocialButtons>
    );
};

const SocialButtons = styled.div`
display: flex;
margin: auto;
justify-content: center;
`;

export default SocialButtonSection;
