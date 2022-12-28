import {IResponse} from "../../models/rest/IResponse";
import {IBattleEntity} from "../../models/IBattleEntity";
import {Page} from "../../models/rest/Page";
import axios, {AxiosInstance} from "axios";


export default class BattleController {
    private static getaway: string = "/battles";

    private axiosInstance: AxiosInstance;

    constructor() {
        this.axiosInstance = axios.create({
            baseURL: process.env.REST_NODE + BattleController.getaway,
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    public getBattleInProgress() : Promise<IResponse<IBattleEntity>> {
        return this.axiosInstance.get<IResponse<IBattleEntity>>("/now")
            .then(data => data.data)
            .catch(error => {
                return {
                    error: error.message,
                    isSuccess: false,
                    data: null,
                    code: error.code
                }
            });
    }

    public getPagedBattles(page: number, size: number, sort: string) : Promise<IResponse<Page<IBattleEntity>>> {
        return this.axiosInstance.get<IResponse<Page<IBattleEntity>>>("", {
            params: { page, size, sort}
        })
            .then(data => data.data)
            .catch(error => {
                return {
                    error: error.message,
                    isSuccess: false,
                    data: null,
                    code: error.code}
            });
    }

    public getBattles(): Promise<IResponse<IBattleEntity[]>> {
        return this.axiosInstance.get<IResponse<IBattleEntity[]>>("")
            .then(data => data.data)
            .catch(error => {
                return {
                    error: error.message,
                    isSuccess: false,
                    data: null,
                    code: error.code}
            })
    }

    public getBattle(id: bigint): Promise<IResponse<IBattleEntity>> {
        return this.axiosInstance.get(`/${id}`)
            .then(data => data.data)
            .catch(error => {
                return {
                    error: error.message,
                    isSuccess: false,
                    data: null,
                    code: error.code
                }
            })
    }
}