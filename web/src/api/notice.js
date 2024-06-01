import request from '../utils/request.js';

export const addNotice = (notice) => {
    return request.put("/add_notice", notice)
}

export const getAllNotice = () => {
    return request.get("/get_all_notice")
}

export const updateNotice = (notice) => {
    return request.put("/update_notice", notice)
}

export const deleteNotice = (nId) => {
    return request.delete("/delete_notice", { params: { nId: nId } })
}
