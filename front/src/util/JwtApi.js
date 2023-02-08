import axios from "axios";
import { ACCESS_TOKEN } from "constants/index";
import { reissueAccessToken } from "service/authService";
import ErrorCode from "service/errorService";
import { API_BASE_URL } from "constants/index";

const jwtApi = axios.create({
    baseURL: API_BASE_URL,
    // baseURL: "http://localhost:8080",
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    } 
})

/**
 * 1. 요청 인터셉터
 * - 요청 직전 수행할 콜백 함수
 * - error 직전 수행할 콜백 함수
 */
jwtApi.interceptors.request.use(
    function (config) {
        // before send request logic
        const accessToken = localStorage.getItem(ACCESS_TOKEN);
        if(accessToken) {
            // config.headers.Authorization = `Bearer ${accessToken}`;
            config.headers.Authorization = `Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5IiwiYXV0aCI6IlJPTEVfVVNFUiIsImVtYWlsIjoiYmJiNDIyNEBuYXRlLmNvbSIsImV4cCI6MTY3NTg0NzIwMX0.JxV4s5snsSvWTDUHiLMw0jCNJeErptu3R4rHK8VGJhqZzHNeqVs5DtBxYca7TJV1qHjjzOqwRC8ApaACaHU8eQ`;
        }
        return config;
    },
    function(error) {
        // before send error logic
        return Promise.reject(error);
    }
);
    
    /**
     * 2. 응답 인터셉터
     * - 응답 데이터 가공 콜백 함수
     * - error 데이터 가공 콜백 함수
    */
jwtApi.interceptors.response.use(
    function(response) {
        // response data 가공
    return response;
    },

    async function(error) {
        // error data 가공
        console.log(error);
        const {config, response} = error;
        console.log(config);
        console.log(response);
        if(response.status === 401) {
            const originRequest = config;
            await reissueAccessToken();
            originRequest.headers.Authorization = `Bearer ${localStorage.getItem(ACCESS_TOKEN)}`;
            return axios(originRequest);
        }
        ErrorCode(response);
        return Promise.reject(error);
    }
)

export default jwtApi;
