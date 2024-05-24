import request from '../utils/request.js';

export const getCoachInfo = () => {
    return request({
        method: 'GET',
        url: '/coach/info',
    })
}

export const selectCoach = (data) => {
    return request({
        method: 'GET',
        url: '/coach/select/user',
        params:data
    })
}

export const chooseCoachList = (data) => {
    return request({
        method: 'GET',
        url: '/coach/choose',
        params:data
    })
}

export const findCoach = (data) => {
    return request({
        method: 'GET',
        url: '/coach/find',
        params:data
    })
}

export const insertCoach = (data) => {
    return request({
        method: 'POST',
        url: '/coach/insert',
        data:data
    })
}

export const updateCoach = (data) => {
    return request({
        method: 'POST',
        url: '/coach/update',
        data:data
    })
}

export const deleteCoach = (data) => {
    return request({
        method: 'POST',
        url: '/coach/delete',
        data:data
    })
}