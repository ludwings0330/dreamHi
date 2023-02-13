import { ACCESS_TOKEN } from 'constants/index';
import Swal from 'sweetalert2';
import jwtApi from 'util/JwtApi';

// 일정 등록
const createAuditionSchedule = async (processId, payload) => {
  //   await jwtApi.post(`/api/announcements/process/${processId}/audition`, payload).then(success);
  const result = await jwtApi
    .post(`/api/announcements/process/${processId}/audition`, payload)
    .then((response) => {
      console.log(response);
      console.log(response.status);
      if (response.status === 201) Swal.fire('📽 오디션 생성 완료!!', 'success');
      return response.status;
    });
  console.log(result);
  return result;
};
// 파일 업로드

export { createAuditionSchedule };
