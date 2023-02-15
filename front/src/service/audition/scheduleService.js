import Swal from 'sweetalert2';
import jwtApi from 'util/JwtApi';

// 일정 등록
const createAuditionSchedule = async (announcementId, processId, payload) => {
  const result = await jwtApi
    .post(`/api/announcements/${announcementId}/audition/on/${processId}/schedules`, payload)
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

const getAuditionPeriod = (announcementId, processId, producerId, success, fail) => {
  jwtApi
    .get(`/api/announcements/${announcementId}/audition/on/${processId}/period`, {
      params: producerId,
    })
    .then(success)
    .catch(fail);
};

export { createAuditionSchedule, getAuditionPeriod };
