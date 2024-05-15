import axios from 'axios';

export const B_URL = 'http://localhost:3000/api';
//创建axios实例
let request = axios.create({
    baseURL: B_URL,
    timeout: 10000,
});

//请求拦截器
request.interceptors.request.use(
    config => {
        //返回配置对象
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

//响应拦截器
request.interceptors.response.use(
    response => {
        return response.data;
    },
    error => {
        //处理网络错误
        let msg = '';
        let status = error.response.status;
        switch (status) {
            case 400:
                msg = '语义有误';
                break;
            case 401:
                msg = '服务器认证失败';
                break;
            case 403:
                msg = '服务器拒绝访问';
                break;
            case 404:
                msg = '请求地址错误';
                break;
            case 500:
                msg = '服务器出现问题';
                break;
            case 502:
                msg = '服务器无响应';
                break;
            default:
                msg = error.response.info;
        }
        alert(msg)
        return Promise.reject(error);
    }
);
export default request;
