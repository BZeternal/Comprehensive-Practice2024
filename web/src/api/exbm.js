import request from '../utils/request.js';

export const judgeType = (data) => {
    return request({
        method: 'GET',
        url: '/eb/judge',
        params:data
    })
}
