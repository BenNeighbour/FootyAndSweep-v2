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
import {Button, Box, Flex} from "@chakra-ui/react";
import {FcGoogle, SiFacebook, SiGoogle} from "react-icons/all";

interface OwnProps {
}

type Props = OwnProps;

const PortalPage: FunctionComponent<Props> = (props) => {
    return (
        <Flex minHeight='100vh' width='full' align='center' justifyContent='center'>
            <Box
                px={4}
                width='full'
                maxWidth='600px'
                textAlign='center'
                borderRadius={3}
                style={{
                    boxShadow: "rgba(0, 0, 0, 0.3) 0px 0px 10px"
                }}
                w="100%" p={4}>
                <Button textAlign='center'
                        borderRadius={3}
                        style={{
                            boxShadow: "rgba(0, 0, 0, 0.3) 0px 0px 10px",
                            width: "25vw",
                            marginBottom: "2vh"
                        }} onClick={() => {
                    window.location.replace(process.env.REACT_APP_API_AUTHENTICATION_SERVICE + "/oauth2/authorize/google?redirect_uri=" + process.env.REACT_APP_OAUTH_REDIRECT_URI);
                }} isLoading={false}
                        loadingText="Loading"
                        leftIcon={window.localStorage.getItem("chakra-ui-color-mode") === "dark" ? <SiGoogle/> : <FcGoogle />}>
                    Continue with Google
                </Button>

                <br />

                <Button textAlign='center'
                        borderRadius={3}
                        style={{
                            boxShadow: "rgba(0, 0, 0, 0.5) 0px 0px 10px",
                            width: "25vw",
                            marginBottom: "2vh"
                        }} onClick={() => {
                    window.location.replace(process.env.REACT_APP_API_AUTHENTICATION_SERVICE + "/oauth2/authorize/facebook?redirect_uri=" + process.env.REACT_APP_OAUTH_REDIRECT_URI);
                }} isLoading={false}
                        loadingText="Loading"
                        leftIcon={<SiFacebook/>}>
                    Continue with Facebook
                </Button>
            </Box>
        </Flex>
    );
};

export default PortalPage;
