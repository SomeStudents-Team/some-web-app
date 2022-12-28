export interface Page<T> {

    content: T[],

    pageable: Pageable,

    last: boolean,

    totalElements: number,

    totalPages: number,

    size: number,

    sort: Sort,

    first: boolean,

    numberOfElements: 2,

    empty: false
}

export interface Pageable {
    sort: Sort,

    offset: number,

    pageSize: number,

    pageNumber: number,

    paged: number,

    unpaged: number
}

export interface Sort {
    empty: boolean,

    sorted: boolean,

    unsorted: boolean
}