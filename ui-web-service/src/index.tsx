import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Routes from './Routes';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter} from "react-router-dom";
import {PersistGate} from "redux-persist/integration/react";
import {Provider} from "react-redux";
import configureStore from "./redux/configureStore";

const { persistor, store } = configureStore();

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter>
            <Provider store={store}>
                <PersistGate
                    loading={<h1>Loading...</h1>}
                    persistor={persistor}
                >
            <Routes/>
                </PersistGate>
            </Provider>
        </BrowserRouter>
    </React.StrictMode>,
    document.getElementById('app')
);

reportWebVitals();
