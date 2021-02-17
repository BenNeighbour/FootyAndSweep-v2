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
import Spinner from "../../components/Spinner/Spinner";
import styled from "styled-components";

interface OwnProps {
    message?: string;
}

type Props = OwnProps;

const LoadingPage: FunctionComponent<Props> = (props) => {
    return (
        <Container>
            <Spinner color={"#2d50cf"}/>
        </Container>
    );
};

export default LoadingPage;

const Container = styled.div`
display: flex;  
height: 80vh;
justify-content: center;
place-items: center;
`;