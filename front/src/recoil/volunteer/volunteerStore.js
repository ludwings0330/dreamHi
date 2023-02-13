import { atom, selectorFamily } from 'recoil';
import jwtApi from 'util/JwtApi';

export const volunteersState = atom({
  key: 'volunteersState',
  default: [],
});

export const vlounteerUserIdState = atom({
  key: 'vlounteerUserIdState',
  default: null,
});

export const volunteersSelector = selectorFamily({
  key: 'volunteersSelector',
  get:
    () =>
    ({ get }) => {
      const data = get(volunteersState);
      let volunteers = [
        { id: 1, time: '09:00', isBook: Math.random() >= 0.5, userId: 10009 },
        { id: 2, time: '09:30', isBook: Math.random() >= 0.5, userId: 51 },
        { id: 3, time: '10:00', isBook: Math.random() >= 0.5, userId: 652 },
        { id: 4, time: '10:30', isBook: Math.random() >= 0.5, userId: 11 },
        { id: 5, time: '11:00', isBook: Math.random() >= 0.5, userId: 84 },
        { id: 6, time: '11:30', isBook: Math.random() >= 0.5, userId: 94 },
        { id: 7, time: '12:00', isBook: Math.random() >= 0.5, userId: 101 },
        { id: 8, time: '12:30', isBook: Math.random() >= 0.5, userId: 999 },
        { id: 9, time: '13:00', isBook: Math.random() >= 0.5, userId: 74 },
        { id: 10, time: '13:30', isBook: Math.random() >= 0.5, userId: 663 },
        { id: 11, time: '14:00', isBook: Math.random() >= 0.5, userId: 184 },
        { id: 12, time: '14:30', isBook: Math.random() >= 0.5, userId: 774 },
        { id: 13, time: '15:00', isBook: Math.random() >= 0.5, userId: 41 },
        { id: 14, time: '15:30', isBook: Math.random() >= 0.5, userId: 33 },
        { id: 15, time: '16:00', isBook: Math.random() >= 0.5, userId: 467 },
        { id: 16, time: '16:30', isBook: Math.random() >= 0.5, userId: 123 },
        { id: 17, time: '17:00', isBook: Math.random() >= 0.5, userId: 202 },
        { id: 18, time: '17:30', isBook: Math.random() >= 0.5, userId: 221 },
        { id: 19, time: '18:00', isBook: Math.random() >= 0.5, userId: 232 },
        { id: 20, time: '18:30', isBook: Math.random() >= 0.5, userId: 756 },
      ];
      return volunteers;
    },
});

export const processState = atom({
  key: 'processState',
  default: 94586,
});

export const totlaVolunteerSelector = selectorFamily({
  key: 'totlaVolunteerSelector',
  get:
    () =>
    ({ get }) => {
      const processId = get(processState);
      // const processId = 94586;
      const data = jwtApi
        .get(`/api/announcements/process/${processId}/volunteer-count`)
        .then((response) => response.data.result);
      return data;
    },
});
