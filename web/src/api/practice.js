import axios from 'axios';
import request from '../utils/request.js';

export const getQuestion = (subject, model) => {
    return axios({
        method: 'GET',
        url: '/jztk/query',
        params: {
            subject: subject,
            model: model,
            testType: 'rand',
            key: '6a79ecdb34275e23be0efb3f7ba7d761'
        },
        headers: { 'content-type': 'application/x-www-form-urlencoded' }
    })
}

export const insertScore = (data) => {
    return request({
        method: 'POST',
        url: '/practice/insert',
        params: data
    })
}