import { atom, selectorFamily } from 'recoil';
import jwtApi from 'util/JwtApi';
import api from 'util/APIUtils';
import dayjs from 'dayjs';
import { userSimpleState } from 'recoil/user/userStore';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist();

export const announcementListState = atom({
  key: 'announcementListState',
  default: {},
});

export const announcementFilterState = atom({
  key: 'announcementFilterState',
  default: {
    minHeight: '',
    maxHeight: '',
    minAge: '',
    maxAge: '',
    gender: '',
    styles: [],
    keyword: '',
    isFollow: '',
    isVolunteer: '',
    page: 0,
    size: 10,
  },
  effects_UNSTABLE: [persistAtom],
});

export const announcementListSelector = selectorFamily({
  key: 'announcementListSelector',
  get:
    () =>
    async ({ get }) => {
      const announcementFilter = get(announcementFilterState);
      // const announcementFilter = {
      //     searchCondition: {
      //         minHeight: "",
      //         maxHeight: "",
      //         minAge: "27",
      //         maxAge: "",
      //         gender: "",
      //         keyword: "",
      //         styles : [],
      //         isFollow: "",
      //         isVolunteer: "",
      //     },
      //     page: 0,
      //     size: 10
      // };

      console.log('😎😎😎😎😎', announcementFilter);
      const userSimple = get(userSimpleState);
      let data = null;
      if (userSimple) {
        console.log('LOGIN API');
        data = await jwtApi
          .get(`/api/announcements`, { params: announcementFilter })
          .then((response) => {
            console.log('logedin');
            console.log('Get/api/announcements');
            console.log(response);
            return response.data.result.content;
          });
      } else {
        console.log('[NOT LOGIN] API');
        data = await api
          .get(`/api/announcements`, { params: announcementFilter })
          .then((response) => {
            console.log('로그인 안 된 상태');
            console.log('Get/api/announcements');
            console.log(response);
            return response.data.result.content;
          });
      }
      return data;
    },

  set: ({ set }, data) => {
    console.log(data);
    set(announcementListState, data);
  },
});

export const announcementListDetailState = atom({
  key: 'announcementListDetailState',
  default: {
    id: 1,
    title: '슬램덩크',
    producer: {
      id: 1,
      name: 'test-1',
    },
    payment: '협의 후 결정',
    crankPeriod: '6개월 예정',
    endDate: dayjs('2023.02.28T12:36:20'),
    hit: 150,
    description: '상세 설명',
    url: 'www.aws.dwdocn',
    isFollow: false,
    isEditor: false,
  },
});

export const announcementListDetailSelector = selectorFamily({
  key: 'announcementListDetailSelector',
  get:
    (announcementDetailId) =>
    async ({ get }) => {
      const userSimple = get(userSimpleState);
      let data = null;
      if (userSimple) {
        data = await jwtApi.get(`/api/announcements/${announcementDetailId}`).then((response) => {
          console.log('logedin');
          console.log('Get/api/announcment/announcementDetailId');
          console.log(response);
          return response.data.result;
        });
      } else {
        console.log('[NOT LOGIN] API');
        data = await api.get(`/api/announcements/${announcementDetailId}`).then((response) => {
          console.log('로그인 안 된 상태');
          console.log('Get/api/announcment/announcementDetailId');
          console.log(response);
          return response.data.result;
        });
      }
      return data;
    },
});

export const announcementListDetailCastingState = atom({
  key: 'announcementListDetailCastingState',
  default: {},
});

export const announcementListDetailCastingSelector = selectorFamily({
  key: 'announcementListDetailCastingSelector',
  get:
    (announcementDetailId) =>
    async ({ get }) => {
      const userSimple = get(userSimpleState);
      let data = null;
      if (userSimple) {
        data = await jwtApi
          .get(`/api/announcements/${announcementDetailId}/castings`)
          .then((response) => {
            console.log('😭😯😯');
            console.log('Get/api/announcment/castings');
            console.log(response);
            return response.data.result;
          });
      } else {
        console.log('[NOT LOGIN] API😆🥱😂');
        data = await api
          .get(`/api/announcements/${announcementDetailId}/castings`)
          .then((response) => {
            console.log('로그인 안 된 상태');
            console.log('Get/api/announcment/castings');
            console.log(response);
            return response.data.result;
          });
      }
      return data;
    },
});

export const followAnnouncementState = atom({
  key: 'followAnnouncementState',
  default: '',
  effects_UNSTABLE: [persistAtom],
});
