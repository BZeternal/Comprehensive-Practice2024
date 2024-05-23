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

export const updatePassword = (uId, oldPass, newPass) => {
    return request.post("/user/update_password",
        {
            uId: uId,
            oldPass: oldPass,
            newPass: newPass
        }
    );
}

export const getAllpeo = () => {
    return request.post("/user/getAll");
}

export const updateAuth = (uId, newAuth) => {
    return request({
        method: "POST",
        url: "/user/update_auth",
        params: {
            uId: uId,
            newAuth: newAuth
        },
    });
}