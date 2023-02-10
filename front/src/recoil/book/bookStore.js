import {atom, selectorFamily } from "recoil";
import { recoilPersist } from "recoil-persist";

// const { persistAtom } = recoilPersist();

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