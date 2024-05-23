import request from '../utils/request.js';

export const chooseCoach = (data) => {
    return request({
        method: "POST",
        url: "/sel/coach",
        data:data
    })
}