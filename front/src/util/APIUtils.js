import axios from "axios";
import { API_BASE_URL } from "constants/index";
import ErrorCode from "service/errorService";
import { parse, stringify } from "qs";


const api = axios.create({
    baseURL: API_BASE_URL,
    // baseURL: "http://localhost:8080",
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
    paramsSerializer: {
        encode: parse,
        serialize: stringify
    },
})

})
/**
 * 1. 요청 인터셉터
 * - 요청 직전 수행할 콜백 함수
 * - error 직전 수행할 콜백 함수
 */
api.interceptors.request.use(
    function (config) {
        // before send request logic
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
api.interceptors.response.use(
    function(response) {
        // response data 가공
        return response;
    },

    async function(error) {
        // error data 가공
        ErrorCode(error.response);
        return Promise.reject(error);
    }
)

export default api;