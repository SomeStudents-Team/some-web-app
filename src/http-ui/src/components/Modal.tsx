import {Backdrop, Fab, Fade, Modal, SxProps} from "@mui/material";
import Box from "@mui/material/Box";
import FormAddBattle from "./FormAddBattle";
import React, {Component, useState} from "react";
import AddIcon from "@mui/icons-material/Add";

const style = {
    position: 'absolute' as 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

type ModalProps = {
    isOpened: boolean
}

type ModalStates = {
    open: boolean
}

const fabStyle = {
    position: 'absolute',
    bottom: 16,
    right: 16,
};

export default class ModalFormAddBattle extends Component<ModalProps, ModalStates> {

    constructor(props: ModalProps) {
        super(props);

        this.state = {
            open: props.isOpened
        }
    }

    handleOpen = () => this.setState({open: true});
    handleClose = () => this.setState({open: false});

    render () {
        return <div>
            <Fab color="primary" sx={fabStyle as SxProps} aria-label="add" onClick={this.handleOpen}>
                <AddIcon />
            </Fab>

            <Modal
                aria-labelledby="transition-modal-title"
                aria-describedby="transition-modal-description"
                open={this.state.open}
                onClose={this.handleClose}
                closeAfterTransition
                BackdropComponent={Backdrop}
                BackdropProps={{
                    timeout: 500,
                }}
            >
                <Fade in={this.state.open}>
                    <Box sx={style}>
                        <FormAddBattle/>
                    </Box>
                </Fade>
            </Modal>
        </div>
    }
}
