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