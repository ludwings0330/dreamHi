import { ACCESS_TOKEN } from "constants/index";
import api from "util/APIUtils";
import jwtApi from "util/JwtApi";
import ErrorCode from "./errorService";


const noticeFileUpload = async () => {
    // api.defaults.headers["Authorization"] = "Bearer " + localStorage.getItem("accessToken");
    // await api.post("/auth/refresh-token", {})
    // .then((response) => {
    //     console.log(response);
    //     localStorage.setItem("accessToken", response.data.result[ACCESS_TOKEN]);
    // })
    // .catch((error) => {
    //     ErrorCode(error.response);
    // });
}

export {
    noticeFileUpload
}