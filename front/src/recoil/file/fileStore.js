import jwtApi from 'util/JwtApi';
import { selectorFamily } from 'recoil';
import { announcementListDetailState } from '../announcement/announcementStore';
import { announcementProcessState } from '../process/processStore';

export const noticeFileSelector = selectorFamily({
  key: 'noticeFileSelector',
  get:
    () =>
    async ({ get }) => {
      const announcementDetail = get(announcementListDetailState);
      const processDetail = get(announcementProcessState);
      const params = { pid: announcementDetail.isEditor ? announcementDetail.producer.id : null };
      const data = await jwtApi
        .get(
          `/api/announcements/${announcementDetail.id}/audition/on/${processDetail.processId}/file`,
          { params: params },
        )
        .then((response) => response.data.result);
      return data;
    },
});
