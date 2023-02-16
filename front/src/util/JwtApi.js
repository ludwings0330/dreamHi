import axios from 'axios';
import { ACCESS_TOKEN, API_BASE_URL } from 'constants/index';
import { reissueAccessToken } from 'service/authService';
import ErrorCode from 'service/errorService';
import { parse, stringify } from 'qs';

const jwtApi = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json;charset=utf-8',
  },
  paramsSerializer: {
    encode: parse,
    serialize: stringify,
  },
});

/**
 * 1. 요청 인터셉터
 * - 요청 직전 수행할 콜백 함수
 * - error 직전 수행할 콜백 함수
 */
jwtApi.interceptors.request.use(
  function (config) {
    // before send request logic
    const accessToken = localStorage.getItem(ACCESS_TOKEN);
    if (accessToken) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }
    return config;
  },
  function (error) {
    // before send error logic
    return Promise.reject(error);
  },
);

/**
 * 2. 응답 인터셉터
 * - 응답 데이터 가공 콜백 함수
 * - error 데이터 가공 콜백 함수
 */
jwtApi.interceptors.response.use(
  function (response) {
    // response data 가공
    return response;
  },

  async function (error) {
    // error data 가공
    console.log(error);
    const { config, response } = error;
    console.log(config);
    console.log(response);
    if (response.status === 401) {
      const originRequest = config;
      await reissueAccessToken().then(() => {
        originRequest.headers.Authorization = `Bearer ${localStorage.getItem(ACCESS_TOKEN)}`;
      });

      return axios(originRequest);
    }
    ErrorCode(response);
    return Promise.reject(error);
  },
);

export default jwtApi;
