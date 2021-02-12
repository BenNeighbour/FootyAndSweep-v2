import React, {FunctionComponent} from 'react';
import {Route, Switch} from "react-router";

interface OwnProps {
}

type Props = OwnProps;

const Routes: FunctionComponent<Props> = (props) => {
    return (
        <Route>
            <Switch>
                <Route>
                    <Route component={() => <h1>Hello</h1>} exact path="/"/>
                </Route>
            </Switch>
        </Route>
    );
};

export default Routes;

