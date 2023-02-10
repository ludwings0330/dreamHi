import Swal from "sweetalert2";

function ErrorCode(response) {
    const code = response.status;
    const message = response.data.message;
    switch (code) {
        case 204:
            Swal.fire({
                title: code, 
                text: message, 
                icon: "info"
            });
            break;
        case 400:
            Swal.fire({
                title: code, 
                text: message, 
                icon: "error"
            });
            return;
        case 401:
            // Test 필요하다.
            Swal.fire({
                title: code,
                text: message,
                icon: "info"
            }).then(function() {
                // window.location.href="http://i8a702.p.ssafy.io/login";
                window.location.href="http://localhost:3000/login";
            })
            return;
        case 403: case 404: case 409: case 500:
            Swal.fire({
                title: code, 
                text: message, 
                icon: "error"
            });
            return;
        default:
            Swal.fire({
                title: code, 
                text: message, 
                icon: "error"
            });
            return;
    }
}

export default ErrorCode;