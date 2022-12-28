import React, {Fragment} from "react";
import {Paper} from "@mui/material";
import {MainPageProps} from "../models/main/MainPageProps";
import {MainPageStates} from "../models/main/MainPageStates";

class Main extends React.Component<MainPageProps, MainPageStates> {


    render() {
        return <Fragment>
            <Paper elevation={3}>
                <h1>Hello</h1>
            </Paper>

            <Paper elevation={3} >
                <h1>Hello</h1>
            </Paper>
        </Fragment>
    }

}

export default Main;