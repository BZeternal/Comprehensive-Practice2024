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

export const getPro = () => {
    return request({
        method:'GET',
        url:'/reserve/getPro',
    })
}

export const getEveryHourNum = () => {
    return request({
        method:'GET',
        url:'/reserve/num/every',
    })
}

export const getSubIdNum = () => {
    return request({
        method:'GET',
        url:'/reserve/num/sub',
    })
}

export const getCarTypeNum = () => {
    return request({
        method:'GET',
        url:'/reserve/num/type',
    })
}

export const getPreNum = () => {
    return request({
        method:'GET',
        url:'/reserve/num/pre',
    })
}
