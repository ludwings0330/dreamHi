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
    default: dayjs(new Date())
})

export const auditionStartTimeState = atom({
    key: "auditionStartTimeState",
    default: dayjs("09:00", "HH:mm")
})

export const auditionEndTimeState = atom({
    key: "auditionEndTimeState",
    default: dayjs("18:00", "HH:mm")
})

export const booksState = atom({
    key: "booksState",
    default: {
        time:"", isBook:""
    }
})

export const booksSelector = selectorFamily({
    key: "booksSelector",
    get: () => () => {
        let bookList = [
            {time: "09:00", isBook: true}, {time: "09:30", isBook: false}, {time: "10:00", isBook: false}, {time: "10:30", isBook: false}, 
            {time: "11:00", isBook: false}, {time: "11:30", isBook: false}, {time: "12:00", isBook: true}, {time: "12:30", isBook: true}, 
            {time: "13:00", isBook: false}, {time: "13:30", isBook: true}, {time: "14:00", isBook: false}, {time: "14:30", isBook: false}, 
            {time: "15:00", isBook: true}, {time: "15:30", isBook: false}, {time: "16:00", isBook: false}, {time: "16:30", isBook: false}, 
            {time: "17:00", isBook: false}, {time: "17:30", isBook: true}, {time: "18:00", isBook: true}, {time: "18:30", isBook: false}
        ];
        return bookList;
    }
})