import {atom, selectorFamily } from "recoil";

export const userSimpleState = atom({
    key: "userSimpleState",
    default: {}
})

export const userSimpleSelector = selectorFamily({
    key: "userSimpleSelector",
    get: () => ({get}) => {
        return get(userSimpleState);
    },
    set: ({set}, data) => {
        console.log("userSimpleState 설정 => ", data);
        set(userSimpleState, data);
    }
})