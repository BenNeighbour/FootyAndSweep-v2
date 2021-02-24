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
import {Text, View} from "../Themed";
import {StyleSheet} from "react-native";

interface OwnProps {
}

type Props = OwnProps;

const Button: FunctionComponent<Props> = (props) => {
    return (
        <View style={styles.helpContainer}>
            <View onTouchStart={() => console.log("clicked")} style={styles.button}><Text style={styles.buttonText}>Hello,
                World</Text></View>
        </View>
    );
};

const styles = StyleSheet.create({
    button: {
        elevation: 3,
        borderRadius: 15,
        padding: 20,
        backgroundColor: "#0154ff",
        shadowColor: "black",
        shadowOpacity: 0.8,
        shadowRadius: 2,
        shadowOffset: {
            height: 1,
            width: 1
        }
    },
    buttonText: {
        color: "#fff",
        fontWeight: "600",
        fontSize: 17
    },
    container: {
        flex: 1,
        backgroundColor: '#fff',
    },
    helpContainer: {
        marginTop: 15,
        marginHorizontal: 20,
        alignItems: 'center',
    },
});


export default Button;
