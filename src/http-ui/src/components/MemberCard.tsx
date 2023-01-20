import * as React from 'react';
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import Avatar from '@mui/material/Avatar';
import IconButton, { IconButtonProps } from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import { red } from '@mui/material/colors';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ShareIcon from '@mui/icons-material/Share';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import {Component} from "react";
import {IBattleMemberEntity} from "../models/IBattleMemberEntity";
import BattleMemberController from "../rest/controllers/BattleMemberController";

interface ExpandMoreProps extends IconButtonProps {
    expand: boolean;
}

const ExpandMore = styled((props: ExpandMoreProps) => {
    const { expand, ...other } = props;
    return <IconButton {...other} />;
})(({ theme, expand }) => ({
    transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
        duration: theme.transitions.duration.shortest,
    }),
}));

type BattleMemberProps = {
    member: IBattleMemberEntity,
    sx: any
}

type BattleMemberStates = {
    isFollowed: boolean

}

export default class BattleMemberCard extends Component<BattleMemberProps, BattleMemberStates> {
    constructor(props: BattleMemberProps) {
        super(props);

        this.state = {
            isFollowed: false
        }
    }

    handleClick = () => {
        this.props.member.score++;

        new BattleMemberController()
            .updateMember(this.props.member)
            .then(data => {
                if(data.isSuccess)
                    this.setState({
                        isFollowed: true
                    })
            })
    }

    render() {
        return <Card sx={this.props.sx + {maxWidth: "100%"}}>
            <CardHeader
                avatar={
                    <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
                        {this.props.member.name[0]}
                    </Avatar>
                }

                title={this.props.member.name}
                subheader={`Счет: ` + this.props.member.score}
            />
            <CardActions disableSpacing>
                <IconButton aria-label="Проголосовать" disabled={this.state.isFollowed} onClick={this.handleClick}
                sx={{
                    color: ((this.state.isFollowed) ? "red" : "gray")
                }}>
                    <FavoriteIcon />
                </IconButton>
            </CardActions>
        </Card>
    }
}