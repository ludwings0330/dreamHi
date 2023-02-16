import { atom, selectorFamily } from 'recoil';
import dayjs from 'dayjs';
import {
  announcementListDetailSelector,
  announcementListDetailState,
} from 'recoil/announcement/announcementStore';
import {
  announcementListDetailProcessSelector,
  announcementProcessState,
} from 'recoil/process/processStore';
import jwtApi from 'util/JwtApi';

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

      const params = {
        pid: announcementDetail.isEditor ? announcementDetail.producer.id : null,
      };
      const data = await jwtApi
        .get(
          `/api/announcements/${announcementDetail.id}/audition/on/${processDetail.processId}/period`,
          {
            params: params,
          },
        )
        .then((response) => response.data.result)
        .catch(() => {});
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

export const selectedDateState = atom({
  key: 'selectedDateState',
  default: null,
});

export const auditionStartTimeState = atom({
  key: 'auditionStartTimeState',
  default: dayjs(new Date()).set('h', 9).set('m', 0),
});

export const auditionEndTimeState = atom({
  key: 'auditionEndTimeState',
  default: dayjs(new Date()).set('h', 18).set('m', 0),
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
      const date = get(selectedDateState);
      if (date === null) {
        return [];
      }
      const announcementDetail = get(announcementListDetailState);
      const processDetail = get(announcementProcessState);
      // const auditionSelectState
      console.log('Selector => ', date);
      const params = {
        pid: announcementDetail.isEditor ? announcementDetail.producer.id : null,
        date: date.format('YYYY-MM-DD'),
      };

      const data = await jwtApi
        .get(
          `/api/announcements/${announcementDetail.id}/audition/on/${processDetail.processId}/schedules`,
          { params: params },
        )
        .then((response) => response.data.result);
      console.log(data);

      return data;
    },
});

export const isBookedSelector = selectorFamily({
  key: 'isBookedSelector',
  get:
    () =>
    async ({ get }) => {
      // const announcementDetail = get(announcementListDetailSelector());
      // const processDetail = get(announcementListDetailProcessSelector());
      const announcementDetail = get(announcementListDetailState);
      const processDetail = get(announcementProcessState);
      const data = await jwtApi
        .get(
          `/api/announcements/${announcementDetail.id}/audition/on/${processDetail.processId}/reservation`,
        )
        .then((response) => response.data.result);
      console.log("IsBooked", data);
      return data;
    },
});
