import { atom, selectorFamily } from 'recoil';
import api from 'util/APIUtils';

export const actorListState = atom({
  key: 'actorListState',
  default: {},
});

export const actorFilterState = atom({
  key: 'actorFilterState',
  default: {
    filter: {
      name: '',
      height: '',
      age: '',
      gender: '',
      styles: [],
      isFollow: '',
    },
    page: 0,
    size: 8,
  },
});

export const actorProfileId = atom({
  key: 'actorProfileId',
  default: 'locker',
});

export const actorProfile = atom({
  key: 'actorProfile',
  default: {},
});

export const actorFilmoUrl = atom({
  key: 'actorFilmoUrl',
  default: null,
});

export const actorFilmoLists = atom({
  key: 'actorFilmoLists',
  default: [],
});
export const actorPhotoUrl = atom({
  key: 'actorPhotoUrl',
  default: null,
});

export const actorVideoUrl = atom({
  key: 'actorVideoUrl',
  default: null,
});

export const actorPhotoLists = atom({
  key: 'actorPhotoLists',
  default: [],
});

export const actorVideoLists = atom({
  key: 'actorVideoLists',
  default: [
    {
      id: '0',
      url: 'https://firebasestorage.googleapis.com/v0/b/dreamhi-17f24.appspot.com/o/images%2F00f5b771.mp4?alt=media&token=293dd7f5-a5ea-4a78-8ccc-8b11e4f7d25c',
    },
  ],
});

export const actorListSelector = selectorFamily({
  key: 'actorListSelector',
  get:
    () =>
    async ({ get }) => {
      console.log(get, 555555555555);
      // const userSimple = get(userSimpleState);
      const userSimple = 1;
      console.log(userSimple);
      // const actorFilter = get(actorFilterState);
      const actorFilter = {
        filter: {
          name: '',
          height: '',
          age: '',
          gender: '',
          styles: [],
          isFollow: '',
        },
        page: 0,
        size: 8,
      };
      console.log(actorFilter);

      // api.defaults.paramsSerializer = params => {
      //     return stringify(params);
      // }
      // console.log(stringify(actorFilter));
      const data = await api.get(`/api/actors`, actorFilter).then((response) => {
        console.log('GET /api/actors');
        console.log(response);
        return response.data.result.content;
      });
      return data;
    },

  set: ({ set }, data) => {
    console.log(data, 8888888888888888);
    set(actorListState, data);
  },
});

export const actorDetailState = atom({
  key: 'actorDetailState',
  default: {},
});

export const actorDetailSelector = selectorFamily({
  key: 'actorDetailSelector',

  get:
    () =>
    async ({ get }) => {
      // const userSimple = get(userSimpleState);
      const userSimple = 1;
      console.log(userSimple);
      // const actorFilter = get(actorFilterState);
      const actorFilter = {
        filter: {
          name: '',
          height: '',
          age: '',
          gender: '',
          styles: [],
          isFollow: '',
        },
        page: 0,
        size: 8,
      };
      console.log(actorFilter);

      // api.defaults.paramsSerializer = params => {
      //     return stringify(params);
      // }
      // console.log(stringify(actorFilter));
      const data = await api.get(`/api/actors`, actorFilter).then((response) => {
        console.log('GET /api/actors');
        console.log(response);
        if (response.status === 204) {
          return [];
        }
        return response.data.result;
      });
      console.log('data : ', data);
      return data;
    },

  set: ({ set }, data) => {
    console.log(data);
    set(actorListState, data);
  },
});
