import jwtApi from 'util/JwtApi';
import { atom, selectorFamily } from 'recoil';
import { announcementListDetailState } from '../announcement/announcementStore';
import { announcementProcessState } from '../process/processStore';

export const noticeFileSelector = selectorFamily({
  key: 'noticeFileSelector',
  get:
    () =>
    async ({ get }) => {
      // /api/announcements/{공고ID}/audition/on/{절차ID}/file[?pid={제작사ID}]
      const announcementDetail = get(announcementListDetailState);
      const processDetail = get(announcementProcessState);
      const params = announcementDetail.isEditor ? announcementDetail.producer.id : null;
      const data = await jwtApi
        .get(
          `/api/announcements/${announcementDetail.id}/audition/on/${processDetail.processId}/file`,
          { params: params },
        )
        .then((response) => response.data.result);
      return data;
    },
});
