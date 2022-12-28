export interface IResponse<T> {
    isSuccess: boolean,

    error: string | null,

    code: string,

    data: T | null
}

export interface INormalSuccessResponse<T> {

    code: string,

    data: T
}

export interface INormalErrorResponse {

    code: string,

    error: string
}

export function ToNormalErrorResponse(data: IResponse<any>): INormalErrorResponse {
    return { code: data.code, error: data.error as string }
}

export function ToNormalSuccessResponse<T>(data: IResponse<T>): INormalSuccessResponse<T> {
    return { code: data.code, data: data.data as T }
}