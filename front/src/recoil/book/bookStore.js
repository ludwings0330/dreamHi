import {atom, selectorFamily } from "recoil";
import { recoilPersist } from "recoil-persist";
import dayjs from 'dayjs';

// const { persistAtom } = recoilPersist();

export const auditionStartState = atom({
    key: "auditionStartState",
    default: dayjs(new Date())
})

export const auditionEndState = atom({
    key: "auditionEndState",
    default: dayjs("2023-12-31")
})

export const auditionSelectState = atom({
    key: "auditionSelectState",
    default: dayjs(new Date()),
})

export const auditionStartTimeState = atom({
    key: "auditionStartTimeState",
    default: dayjs("09:00", "HH:mm")
})

export const auditionEndTimeState = atom({
    key: "auditionEndTimeState",
    default: dayjs("18:00", "HH:mm")
})
export const checkTimeState = atom({
    key: "checkTimeState",
    default: ""
})



export const booksState = atom({
    key: "booksState",
    default: {
        time:"", isBook:""
    }
})

export const booksSelector = selectorFamily({
    key: "booksSelector",
    get: () => ({get}) => {
        const date = get(auditionSelectState);
        let bookList = [
            {id : 1,time: "09:00", isBook: Math.random() >= 0.5}, {id : 2,time: "09:30", isBook: Math.random() >= 0.5}, {id : 3,time: "10:00", isBook: Math.random() >= 0.5}, {id : 4,time: "10:30", isBook: Math.random() >= 0.5}, 
            {id : 5,time: "11:00", isBook: Math.random() >= 0.5}, {id : 6,time: "11:30", isBook: Math.random() >= 0.5}, {id : 7,time: "12:00", isBook: Math.random() >= 0.5}, {id : 8,time: "12:30", isBook: Math.random() >= 0.5}, 
            {id : 9,time: "13:00", isBook: Math.random() >= 0.5}, {id : 10,time: "13:30", isBook: Math.random() >= 0.5}, {id : 11,time: "14:00", isBook: Math.random() >= 0.5}, {id : 12,time: "14:30", isBook: Math.random() >= 0.5}, 
            {id : 13,time: "15:00", isBook: Math.random() >= 0.5}, {id : 14,time: "15:30", isBook: Math.random() >= 0.5}, {id : 15,time: "16:00", isBook: Math.random() >= 0.5}, {id : 16,time: "16:30", isBook: Math.random() >= 0.5}, 
            {id : 17,time: "17:00", isBook: Math.random() >= 0.5}, {id : 18,time: "17:30", isBook: Math.random() >= 0.5}, {id : 19,time: "18:00", isBook: Math.random() >= 0.5}, {id : 20,time: "18:30", isBook: Math.random() >= 0.5}
        ];
        return bookList;
    }
})
