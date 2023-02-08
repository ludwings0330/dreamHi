import { ACCESS_TOKEN } from "constants/index";
import api from "util/APIUtils";
import ErrorCode from "./errorService";

const reissueAccessToken = async () => {
    api.defaults.headers["Authorization"] = "Bearer " + localStorage.getItem("accessToken");
    // api.defaults.headers["Authorization"] = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5IiwiYXV0aCI6IlJPTEVfVVNFUiIsImVtYWlsIjoiYmJiNDIyNEBuYXRlLmNvbSIsImV4cCI6MTY3NTg0NzIwMX0.JxV4s5snsSvWTDUHiLMw0jCNJeErptu3R4rHK8VGJhqZzHNeqVs5DtBxYca7TJV1qHjjzOqwRC8ApaACaHU8eQ";
    await api.post("/auth/refresh-token", {})
    .then((response) => {
        console.log(response);
        localStorage.setItem("accessToken", response.data.result[ACCESS_TOKEN]);
    })
    .catch((error) => {
        ErrorCode(error.response);
    });
}

const aaa = async (actorId, payload, success) => {
    api.get(`/api/actors/${actorId}`, payload)
    .then(success);
}

export {
    reissueAccessToken, aaa
}