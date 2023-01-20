import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TablePagination, TableRow} from "@mui/material";
import React, {Component} from "react";
import {IResponse, ToNormalSuccessResponse} from "../models/rest/IResponse";
import {IBattleEntity} from "../models/IBattleEntity";
import BattleController from "../rest/controllers/BattleController";
import {Page} from "../models/rest/Page";

const tableRows: string[] = [
    "Заголовок", "Описание", "Состояние", "Лидер"
]

const battleState: string[] = [
    "в процессе", "ожидает", "завершен"
]

type TableStates = {
    data: IResponse<Page<IBattleEntity>>,

    page: number,

    rowsPerPage: number,

    sortBy: string,

    count: number
}

type TableProps = {

}

export default class History extends Component<TableProps, TableStates> {

    constructor(props: TableProps) {
        super(props);

        this.state = {
            rowsPerPage: 10,
            page: 0,

            data: {
                isSuccess: false,
                data: null,
                error: null,
                code: ""
            },

            sortBy: "dateStart",
            count: 0
        }
    }
    componentDidMount() {
        new BattleController().getBattlesCount()
            .then(data => this.setState({
                count: data.isSuccess ? ToNormalSuccessResponse(data).data : 0
            }));

        this.requestData(0, 10, "dateStart");
    }

    requestData = (page: number, rows: number, sortBy: string) => {
        new BattleController().getPagedBattles(page, rows, sortBy)
            .then(data => {

                this.setState({
                    data: data,
                    page,
                    rowsPerPage: rows
                })
            })
    }

    handleChangePage = (event: unknown, newPage: number) => {
        this.requestData(newPage, this.state.rowsPerPage, this.state.sortBy);
    }

    handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
        this.requestData(0, +event.target.value, this.state.sortBy);
    }

    render () {
        return <Paper sx={{width: '100%', overflow: 'hidden', marginTop: '5%'}} elevation={3}>

            <TableContainer sx={{
                maxHeight: '100%',
                height: '50%'
            }}>
                <Table stickyHeader aria-label="sticky table">
                    <TableHead>
                        <TableRow>
                            {tableRows.map((header: string) => (
                                <TableCell
                                    key={header}
                                    align={'center'}
                                    style={{minWidth: 'auto'}}
                                >
                                    {header}
                                </TableCell>
                            ))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.state.data.isSuccess && this.state.data
                            .data?.content.map((entity: IBattleEntity) => {
                                return (
                                    <TableRow hover role="checkbox" tabIndex={-1} key={entity.id.toString()}>
                                        <TableCell key={entity.title} align={'center'}>
                                            {entity.title}
                                        </TableCell>

                                        <TableCell key={entity.description} align={'center'}>
                                            {entity.description}
                                        </TableCell>

                                        <TableCell key={entity.state} align={'center'}>
                                            {battleState[entity.state]}
                                        </TableCell>

                                        <TableCell key={entity.owner.name} align={'center'}>
                                            {entity.leader == null ? "Неизвестно" : entity.leader.name}
                                        </TableCell>
                                    </TableRow>
                                );
                            })}
                    </TableBody>
                </Table>
            </TableContainer>
            <TablePagination
                rowsPerPageOptions={[10, 25, 100]}
                component="div"
                count={this.state.count as number}
                rowsPerPage={this.state.rowsPerPage}
                page={this.state.page}
                onPageChange={this.handleChangePage}
                onRowsPerPageChange={this.handleChangeRowsPerPage}
            />
        </Paper>
    }
}