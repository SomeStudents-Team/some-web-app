import React, {Fragment} from "react";
import {Paper} from "@mui/material";
import {MainPageProps} from "../models/main/MainPageProps";
import {MainPageStates} from "../models/main/MainPageStates";
import ModalFormAddBattle from "../components/Modal";
import BattleMemberCard from "../components/MemberCard";
import BattleController from "../rest/controllers/BattleController";
import {ToNormalSuccessResponse} from "../models/rest/IResponse";

class Main extends React.Component<MainPageProps, MainPageStates> {

    constructor(props: MainPageProps) {
        super(props);

        this.state = {
            battleInProgress: {
                isSuccess: false,
                error: null,
                data: null,
                code: ""
            }
        }

        this.requestBattleInProgress();
    }

    componentDidMount() {
        setTimeout(() => this.requestBattleInProgress(), 60000);
    }

    requestBattleInProgress = () =>
        new BattleController().getBattleInProgress()
            .then(data => this.setState({
                battleInProgress: data
            }))

    render() {
        return <Fragment>
            <ModalFormAddBattle  isOpened = {false}/>

            <Paper elevation={3} sx={{display: 'flex', width: '100%', alignItems: 'flex-start'}}>
                {this.state.battleInProgress.isSuccess && <Fragment>
                    <BattleMemberCard  member={ToNormalSuccessResponse(this.state.battleInProgress).data.members[0]} sx={{left: "0px"}}/>
                    <h1 style={{textAlign: 'center'}}>Текущая битва</h1>
                    <BattleMemberCard  member={ToNormalSuccessResponse(this.state.battleInProgress).data.members[1]} sx={{right: "0px"}}/>
                </Fragment>}
            </Paper>
        </Fragment>
    }

}

export default Main;