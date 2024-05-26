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

export const addUserInfo = (apply, healthForm) => {
    return request({
        method: "POST",
        url: "/user/addUserInfo",
        data: {
            apply,
            healthForm,
        },
    });
}

export const addUser = (uId) => {
    return request.get("/user/add_user", { params: { uId: uId } });
}

export const deletUser = (uId) => {
    return request.get("/user/delete_user", { params: { uId: uId } });
}

export const findUser = (type, key) => {
    return request.get("/user/find_user", { params: { type: type, key: key } });
}

export const getApply = (uId) => {
    return request({
        method: "POST",
        url: "/user/get_apply",
        params: {
            uId: uId,
        },
    });
}

export const getHea = (uId) => {
    return request({
        method: "POST",
        url: "/user/get_hea",
        params: {
            uId: uId,
        },
    });
}

export const check = (uId, state) => {
    return request({
        method: "POST",
        url: "/user/check",
        params: {
            uId: uId,
            state: state,
        },
    });
}