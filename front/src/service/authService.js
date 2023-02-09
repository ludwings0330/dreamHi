import { ACCESS_TOKEN } from "constants/index";
import api from "util/APIUtils";
import jwtApi from "util/JwtApi";
import ErrorCode from "./errorService";
import Swal from "sweetalert2";


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
    await jwtApi.post("/api/logout")
    .then((response) => {
        console.log(response);
        if(response.status === 202) {
            localStorage.removeItem(ACCESS_TOKEN);
            Swal.fire({
                title: "감사합니다 😀",
                text: "로그아웃!!",
                icon: "success"
            }).then(function() {
                // window.location.href="http://i8a702.p.ssafy.io/login";
                window.location.href="/";
            })
        }
    })
}

export {
    reissueAccessToken, logout
}