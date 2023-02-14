import { atom, selectorFamily } from 'recoil';
import { recoilPersist } from 'recoil-persist';
import dayjs from 'dayjs';
import { getAuditionPeriod } from 'service/audition/scheduleService';
import { announcementListDetailState } from 'recoil/announcement/announcementStore';
import { announcementProcessState } from 'recoil/process/processStore';
import jwtApi from 'util/JwtApi';

// const { persistAtom } = recoilPersist();

export const auditionPeriodState = atom({
  key: 'auditionPeriodState',
  default: {},
});

export const auditionPeriodSelector = selectorFamily({
  key: 'auditionPeriodSelector',
  get:
    () =>
    async ({ get }) => {
      const announcementDetail = get(announcementListDetailState);
      const processDetail = get(announcementProcessState);
      console.log(announcementDetail);
      console.log(processDetail);

      let data = {};
      if (announcementDetail.isEditor) {
        data = await jwtApi
          .get(
            `/api/announcements/${announcementDetail.id}/audition/on/${processDetail.processId}/period`,
            {
              params: { pid: announcementDetail.producer.id },
            },
          )
          .then((response) => response.data.result)
          .catch(() => {});
      } else {
        data = await jwtApi
          .get(
            `/api/announcements/${announcementDetail.id}/audition/on/${processDetail.processId}/period`,
          )
          .then((response) => response.data.result)
          .catch(() => {});
      }
      return data;
    },
  set: ({ set }, data) => {
    set(auditionPeriodState, data);
  },
});

export const auditionStartState = atom({
  key: 'auditionStartState',
  default: dayjs(new Date()),
});

export const auditionEndState = atom({
  key: 'auditionEndState',
  default: dayjs(new Date()).add(7, 'day'),
});

export const auditionSelectState = atom({
  key: 'auditionSelectState',
  default: null,
});

export const auditionStartTimeState = atom({
  key: 'auditionStartTimeState',
  default: dayjs('09:00', 'HH:mm'),
});

export const auditionEndTimeState = atom({
  key: 'auditionEndTimeState',
  default: dayjs('18:00', 'HH:mm'),
});
export const checkTimeState = atom({
  key: 'checkTimeState',
  default: '',
});

export const booksState = atom({
  key: 'booksState',
  default: {
    time: '',
    isBook: '',
  },
});

export const booksSelector = selectorFamily({
  key: 'booksSelector',
  get:
    () =>
    async ({ get }) => {
      const date = get(auditionSelectState);
      const announcementDetail = get(announcementListDetailState);
      const processDetail = get(announcementProcessState);
      // const auditionSelectState
      // console.log('Selector => ', date);

      const data = await jwtApi.get(`/api/announcements/${announcementDetail.id}/audition/on/${processDetail.processId}/schedules`,)

      let bookList = [
        { id: 1, time: '09:00', isBook: Math.random() >= 0.5 },
        { id: 2, time: '09:30', isBook: Math.random() >= 0.5 },
        { id: 3, time: '10:00', isBook: Math.random() >= 0.5 },
        { id: 4, time: '10:30', isBook: Math.random() >= 0.5 },
        { id: 5, time: '11:00', isBook: Math.random() >= 0.5 },
        { id: 6, time: '11:30', isBook: Math.random() >= 0.5 },
        { id: 7, time: '12:00', isBook: Math.random() >= 0.5 },
        { id: 8, time: '12:30', isBook: Math.random() >= 0.5 },
        { id: 9, time: '13:00', isBook: Math.random() >= 0.5 },
        { id: 10, time: '13:30', isBook: Math.random() >= 0.5 },
        { id: 11, time: '14:00', isBook: Math.random() >= 0.5 },
        { id: 12, time: '14:30', isBook: Math.random() >= 0.5 },
        { id: 13, time: '15:00', isBook: Math.random() >= 0.5 },
        { id: 14, time: '15:30', isBook: Math.random() >= 0.5 },
        { id: 15, time: '16:00', isBook: Math.random() >= 0.5 },
        { id: 16, time: '16:30', isBook: Math.random() >= 0.5 },
        { id: 17, time: '17:00', isBook: Math.random() >= 0.5 },
        { id: 18, time: '17:30', isBook: Math.random() >= 0.5 },
        { id: 19, time: '18:00', isBook: Math.random() >= 0.5 },
        { id: 20, time: '18:30', isBook: Math.random() >= 0.5 },
      ];
      return bookList;
    },
});
