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
import {Box, Button, Flex, Input, Stack} from "@chakra-ui/react";
import {SiFacebook, SiGoogle} from "react-icons/all";
import {RouteComponentProps} from "react-router-dom";

interface OwnProps extends RouteComponentProps<any> {
}

type Props = OwnProps;

const PortalPage: FunctionComponent<Props> = (props) => {
    return (
        <Flex minHeight='100vh' width='full' align='center' justifyContent='center' style={{
            textAlign: "center",
            display: 'flex',
            placeItems: 'center',
        }}>
            <Box
                px={4}
                width='full'
                maxWidth='500px'
                textAlign='center'
                borderRadius={3}
                style={{
                    boxShadow: "rgba(0, 0, 0, 0.3) 0px 0px 10px",
                    minHeight: "55vh",
                    margin: 'auto',
                }}
                w="100%" p={4}>

                <Stack spacing={3}>
                    <Input style={{
                        width: "22vw",
                        margin: 'auto',
                        marginBottom: "1.5vh",
                        marginTop: "2.5vh"
                    }} placeholder={"Email or Username"} variant={"filled"} type={"text"} size="md"/>

                    <Input style={{
                        width: "22vw",
                        margin: 'auto',
                        marginBottom: "2vh"
                    }} placeholder={"Password"} variant={"filled"} type={"password"} size="md"/>

                    <Button colorScheme="blue" textAlign='center'
                            borderRadius={3}
                            style={{
                                width: "22vw",
                                margin: 'auto',
                                boxShadow: "rgba(0, 0, 0, 0.3) 0px 0px 10px"
                            }} onClick={() => {
                        /* Execute Redux event to log in */
                    }} isLoading={false}
                            loadingText="Logging in">
                        Log into FootyAndSweep
                    </Button>

                    <div className={"or"} style={{
                        color: "rgb(151, 160, 175)",
                        content: "attr(data-i18n-or)",
                        display: "block",
                        fontSize: "11px",
                        lineHeight: 2,
                        marginBottom: "16px",
                        textAlign: "center",
                        textTransform: "uppercase"
                    }}>OR
                    </div>

                    <Button textAlign='center'
                            borderRadius={3}
                            style={{
                                boxShadow: "rgba(0, 0, 0, 0.3) 0px 0px 10px",
                                width: "22vw",
                                margin: 'auto',
                                marginBottom: "2.5vh"
                            }} onClick={() => {
                        window.location.replace(process.env.REACT_APP_API_AUTHENTICATION_SERVICE + "/oauth2/authorize/google?redirect_uri=" + process.env.REACT_APP_OAUTH_REDIRECT_URI);
                    }} isLoading={false}
                            loadingText="Loading"
                            leftIcon={<SiGoogle/>
                                // :
                                //     <FcGoogle/>
                            }>
                        Continue with Google
                    </Button>

                    <Button textAlign='center'
                            borderRadius={3}
                            style={{
                                boxShadow: "rgba(0, 0, 0, 0.3) 0px 0px 10px",
                                width: "22vw",
                                margin: 'auto',
                                marginBottom: "2.5vh"
                            }} onClick={() => {
                        window.location.replace(process.env.REACT_APP_API_AUTHENTICATION_SERVICE + "/oauth2/authorize/facebook?redirect_uri=" + process.env.REACT_APP_OAUTH_REDIRECT_URI);
                    }} isLoading={false}
                            loadingText="Loading"
                            leftIcon={<SiFacebook/>}>
                        Continue with Facebook
                    </Button>
                </Stack>
            </Box>
        </Flex>
    );
};

export default PortalPage;
