import Swal from 'sweetalert2';
import jwtApi from 'util/JwtApi';

const noticeFileUpload = async (announcementId, processId, payload) => {
  const result = await jwtApi
    .post(`/api/announcements/${announcementId}/audition/on/${processId}/files`, payload)
    .then((response) => {
      if (response.status === 201) Swal.fire('📃 파일 업로드 완료!!', 'success');
      return response.status;
    });
  return result;
};

export { noticeFileUpload };
