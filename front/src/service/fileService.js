import { ACCESS_TOKEN } from 'constants/index';
import Swal from 'sweetalert2';
import jwtApi from 'util/JwtApi';

const noticeFileUpload = async (processId, payload) => {
  const result = await jwtApi
    .post(`/api/announcements/process/${processId}/notice-file`, payload)
    .then((response) => {
      if (response.status === 201) Swal.fire('📃 파일 업로드 완료!!', 'success');
      return response.status;
    });
  return result;
};

export { noticeFileUpload };
