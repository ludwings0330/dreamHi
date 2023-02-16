import { atom, selectorFamily } from 'recoil';
import { announcementProcessState } from 'recoil/process/processStore';
import jwtApi from 'util/JwtApi';

export const volunteersState = atom({
  key: 'volunteersState',
  default: [],
});

export const volunteerUserIdState = atom({
  key: 'volunteerUserIdState',
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

export const volunteerDetailSelector = selectorFamily({
  key: "volunteerDetailSelector",
  get: () => async ({get}) => {
    const userId = get(volunteerUserIdState);
    if(userId === null) return 'loading';
    // /api/users/{userId}/actor-profile
    const data = await jwtApi.get(`/api/users/${userId}/actor-profile`)
    .then(response => {
      console.log(response);
      return response.data.result;
    });
    return data;
  }
})