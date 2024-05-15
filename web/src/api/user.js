import request from '../utils/request.js';

export const login = (data) => {
    return request({
        method: "POST",
        url: "/login",
        params: data
    })
}

export const register = (data) => {
    return request({
        method: "POST",
        url: "/register",
        params: data
    })
}

export const getUserInfo = (data) => {
    return request({
        method: "POST",
        url: "/user/info",
        params: { uId: data }
    })
}