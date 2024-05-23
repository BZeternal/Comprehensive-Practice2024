import request from '../utils/request.js';

export const findReserveByUser = (data) => {
    return request({
        method: 'POST',
        url: '/reserve/user/find',
        data: data
    })
}

export const insertReserve = (data) => {
    return request({
        method: 'POST',
        url: '/reserve/insert',
        data: data
    })
}

export const deleteReserve = (data) => {
    return request({
        method: 'GET',
        url: '/reserve/delete',
        params: data
    })
}
