import { ACCESS_TOKEN } from "constants/index";
import api from "util/APIUtils";
import jwtApi from "util/JwtApi";
import ErrorCode from "./errorService";



const reissueAccessToken = async () => {
    api.defaults.headers["Authorization"] = "Bearer " + localStorage.getItem("accessToken");
    await api.post("/auth/refresh-token", {})
    .then((response) => {
        console.log(response);
        localStorage.setItem("accessToken", response.data.result[ACCESS_TOKEN]);
    })
    .catch((error) => {
        ErrorCode(error.response);
    });
}

const logout = async () => {
    const result = await jwtApi.post("/api/logout")
    .then((response) => {
        console.log(response);
        if(response.status === 202) {
            localStorage.removeItem(ACCESS_TOKEN);
        }
        return response.status;
    })
    return result === 202 ? true : false;
}

export {
    reissueAccessToken, logout
}