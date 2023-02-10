import {atom, selectorFamily} from "recoil";
import jwtApi from "util/JwtApi";
import api from "util/APIUtils";
import { qs, stringify } from "qs";

export const actorListState = atom({
    key: "actorListState",
    default: {}
})

export const actorFilterState = atom({
    key: "actorFilterState",
    default: {
        filter: {
            name: "",
            height: "",
            age: "",
            gender: "",
            styles: [],
            isFollow: "",
        },
        page: 0,
        size: 8
    },
})
export const actorListSelector = selectorFamily({
    key: "actorListSelector",
    get: () => async ({get}) => {
        // const userSimple = get(userSimpleState);
        const userSimple = 1;
        console.log(userSimple);
        // const actorFilter = get(actorFilterState);
        const actorFilter = {
            filter: {
                name: "",
                height: "",
                age: "",
                gender: "",
                styles: [],
                isFollow: "",
            },
            page: 0,
            size: 8
        };
        console.log(actorFilter);

        // api.defaults.paramsSerializer = params => {
        //     return stringify(params);
        // }
        // console.log(stringify(actorFilter));
        const data = await api.get(`/api/actors`, actorFilter)
            .then((response) => {
                console.log("GET /api/actors");
                console.log(response);
                return response.data.result.content;
            });
        return data;
    },

    set: ({set}, data) => {
        console.log(data);
        set(actorListState, data);
    }
})