import {IResponse} from "../../models/rest/IResponse";
import {IBattleEntity, IBattleInput} from "../../models/IBattleEntity";
import {Page} from "../../models/rest/Page";
import axios, {AxiosInstance} from "axios";
import {IBattleMemberEntity} from "../../models/IBattleMemberEntity";


export default class BattleMemberController {
    private static getaway: string = "/members";

    private axiosInstance: AxiosInstance;

    constructor() {
        this.axiosInstance = axios.create({
            baseURL: process.env.REACT_APP_REST_NODE + BattleMemberController.getaway,
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    public updateMember(ent: IBattleMemberEntity): Promise<IResponse<IBattleEntity>> {
        return this.axiosInstance.put("", ent)
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