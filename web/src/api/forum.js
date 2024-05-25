import request from '../utils/request.js';

export const getInfo = () => {
    return request({
        method:"GET",
        url:"/forum/info",
    })
}

export const insertForum = (data) => {
    return request({
        method:"POST",
        url:"/forum/insert",
        data:data
    })
}

export const deleteForum = (data) => {
    return request({
        method:"POST",
        url:"/forum/delete",
        data:data
    })
}

export const updateForum = (data) => {
    return request({
        method:"POST",
        url:"/forum/update",
        data:data
    })
}

export const insertComment = (data) => {
    return request({
        method:"POST",
        url:"/comment/insert",
        data:data
    })
}

export const deleteComment = (data) => {
    return request({
        method:"POST",
        url:"/comment/delete",
        data:data
    })
}

