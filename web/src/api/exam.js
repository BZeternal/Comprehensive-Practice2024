import request from '../utils/request.js';

export const getType = () => {
    return request.get("/get_type")
}
export const getCj = (uId) => {
    return request.get("/get_cj", { params: uId })
}
export const isSignUp = (uId, subId) => {
    return request.get("/is_sign_up", { params: { uId: uId, subId: subId } })
}

export const canSignUp = (examBm) => {
    return request.put("/can_sign_up", examBm)
}
