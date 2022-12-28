import axios from "axios";

export const battleAxios = axios.create({
    baseURL: process.env.REST_NODE
});