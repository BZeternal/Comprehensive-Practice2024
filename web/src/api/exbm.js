import request from '../utils/request.js';

export const judgeType = (data) => {
    return request({
        method: 'GET',
        url: '/eb/judge',
        params:data
    })
}

export const getInfo = () => {
    return request({
        method: 'GET',
        url: '/eb/info/list',
    })
}
