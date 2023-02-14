import { atom, selectorFamily } from 'recoil';
import { announcementProcessState } from 'recoil/process/processStore';
import jwtApi from 'util/JwtApi';

export const volunteersState = atom({
  key: 'volunteersState',
  default: [],
});

export const vlounteerUserIdState = atom({
  key: 'vlounteerUserIdState',
  default: null,
});

export const totalVolunteerSelector = selectorFamily({
  key: 'totalVolunteerSelector',
  get:
    () =>
    async ({ get }) => {
      const processDetail = get(announcementProcessState);
      // const processId = 94586;
      const data = await jwtApi
        .get(`/api/announcements/process/${processDetail.processId}/volunteer-count`)
        .then((response) => response.data.result);
      return data;
    },
});
