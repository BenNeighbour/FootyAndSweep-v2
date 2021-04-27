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
import {Form, Formik} from "formik";
import Modal from "../../components/Modal/Modal";
import CreateSweepstakeForm from "../../forms/CreateSweepstake/CreateSweepstakeForm";
import * as yup from "yup";
import {RootState} from "../../redux/rootReducer";
import {bindActionCreators} from "redux";
import * as SweepstakeActions from "../../redux/reducers/saga/sweepstake/sweepstakeActions";
import {connect} from "react-redux";
import * as SweepstakePageActions from "../../redux/reducers/saga/sweepstakePage/sweepstakePageActions";

interface OwnProps {
    state: RootState;
    saveSweepstakeActions: typeof SweepstakeActions;
    sweepstakePageActions: typeof SweepstakePageActions;
}

const schema = yup.object().shape({
    name: yup
        .string()
        .required("You must enter a Sweepstake Name.")
        .label("Sweepstake Name"),
    description: yup
        .string()
        .required("You must enter a Sweepstake Description.")
        .label("Sweepstake Description"),
    type: yup
        .string()
        .required("You must enter a valid Sweepstake Type.")
        .label("Sweepstake Type"),
    event: yup
        .string()
        .required("You must enter a valid Football Match.")
        .label("Football Match"),
    isPrivate: yup
        .boolean()
        .required("Your sweepstake must be Public or Private.")
        .label("Sweepstake Privacy"),
    minimumPlayers: yup
        .number()
        .required("You must enter the number of Minimum Players.")
        .label("Minimum Players"),
    stake: yup
        .number()
        .required("You must enter a valid Stake.")
        .label("Stake")
});

type Props = OwnProps;

const CreateSweepstakeModal: FunctionComponent<Props> = (props) => {
    return (
        <Formik
            onSubmit={(formValues) => {
                props.saveSweepstakeActions.saveSweepstakeAction(formValues);
            }}
            validateOnBlur={true}
            validateOnChange={true}
            validationSchema={schema}
            initialValues={{
                name: "",
                isPrivate: true,
                ownerId: `${localStorage.getItem("user_id")}`,
                minimumPlayers: 2,
                stake: "",
                description: "",
                type: "Correct Score H/T",
                event: "sdf"
            }}
        >
            {(formik) => {
                const {
                    values,
                    handleChange,
                    errors,
                    touched,
                    handleBlur,
                } = formik;
                return (
                    <Form>
                        <Modal isForm setShowing={props.sweepstakePageActions.setIsCreatingSweepstake}
                               title={"Create a Sweepstake"}
                               description={"Fill the following fields to create a new sweepstake"}
                               showing={props.state.sweepstakesPage.creatingSweepstake}>
                            <CreateSweepstakeForm handleBlur={handleBlur} values={values} handleChange={handleChange} errors={errors}
                                                  touched={touched}/>
                        </Modal>
                    </Form>
                )
            }}
        </Formik>
    );
};

const mapStateToProps = (state: RootState) => {
        return {
            state
        }
    }
;

function mapDispatchToProps(dispatch: any) {
    return {
        saveSweepstakeActions: bindActionCreators(SweepstakeActions as any, dispatch),
        sweepstakePageActions: bindActionCreators(SweepstakePageActions as any, dispatch),
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(CreateSweepstakeModal);