import { atom, selectorFamily } from 'recoil';
import jwtApi from 'util/JwtApi';
import api from 'util/APIUtils';
import { qs, stringify } from 'qs';

export const makerListState = atom({
  key: 'makerListState',
  default: {},
});

export const makerProfileId = atom({
  key: 'makerProfileId',
  default: 'locker',
});

export const makerProfile = atom({
  key: 'makerProfile',
  default: {},
});

export const makerFilmoUrl = atom({
  key: 'makerFilmoUrl',
  default: null,
});
export const makerPhotoUrl = atom({
  key: 'makerPhotoUrl',
  default: null,
});

export const makerPhotoLists = atom({
  key: 'makerPhotoLists',
  default: [
    {
      id: '0',
      url: 'https://firebasestorage.googleapis.com/v0/b/dreamhi-17f24.appspot.com/o/images%2Fblank-profile-picture-973460_640.png?alt=media&token=0fd71f6c-3c8f-451c-958a-d321645845bf',
    },
  ],
});

export const googleToken = atom({
  key: 'googleToken',
  default:
    'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAwMDEiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZW1haWwiOiJkZGY5OThAZ21haWwuY29tIiwiZXhwIjoxNjc1OTMwOTc2fQ.nY5hyxEi6xQv_1ZUErustADrwOyfCtpbnRnpaRvw2CHHsNEZPrKjbXsiZRvZ1Whd8HKYN9bj3iJEjqLK1AUtQw',
});

export const makerListSelector = selectorFamily({
  key: 'makerListSelector',
  get:
    () =>
    async ({ get }) => {
      console.log(get, '성공');
      const userSimple = 1;
      console.log(userSimple);

      const data = await api.get(`/api/producers`).then((response) => {
        console.log('GET /api/producers');
        console.log(response);
        return response.data.result.content;
      });
      return data;
    },

  set: ({ set }, data) => {
    console.log(data, 8888888888888888);
    set(makerListState, data);
  },
});

export const makerDetailState = atom({
  key: 'makerDetailState',
  default: {},
});

export const makerDetailSelector = selectorFamily({
  key: 'makerDetailSelector',

  get:
    () =>
    async ({ get }) => {
      const userSimple = 1;
      console.log(userSimple);

      const data = await api.get(`/api/producers`).then((response) => {
        console.log('GET /api/producerss');
        console.log(response);
        if (response.status === 204) return [];
        return response.data.result;
      });
      console.log('data : ', data);
      return data;
    },

  set: ({ set }, data) => {
    console.log(data);
    set(makerListState, data);
  },
});
