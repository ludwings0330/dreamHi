import {atom, selectorFamily} from "recoil";
import jwtApi from "util/JwtApi";
import api from "util/APIUtils";
import { qs, stringify } from 'qs';
import { userSimpleState } from "recoil/user/userStore";

export const announcementListState = atom({
    key: "announcementListState",
    default: {}
})

export const announcementFilterState = atom({
    key: "announcementFilterState",
    default: {
        searchCondition: {
            minHeight: "",
            maxHeight: "",
            minAge: "",
            maxAge: "",
            gender: "",
            keyword: "",
            styles : [],
            isFollow: "",
            isVolunteer: "",
        },
        page: 0,
        size: 4
    },
})

export const announcementListSelector = selectorFamily({
    key: "announcementListSelector",
    get: () => async ({get}) => {
        const announcementFilter = {
            searchCondition: {
                minHeight: "",
                maxHeight: "",
                minAge: "27",
                maxAge: "",
                gender: "",
                keyword: "",
                styles : [],
                isFollow: "",
                isVolunteer: "",
            },
            page: 0,
            size: 50
        };

        // const searchCondition = {
        //             minHeight: "",
        //             maxHeight: "",
        //             minAge: "27",
        //             maxAge: "",
        //             gender: "",
        //             keyword: "",
        //             styles : [],
        //             isFollow: "",
        //             isVolunteer: "",
        //         };
        // const pageable = {
        //     page: 0,
        //     size: 10
        // };
        console.log('😎😎😎😎😎',announcementFilter);
        const userSimple = get(userSimpleState);
        const data = null;
        if(userSimple.id) {
            console.log("LOGIN API");
            data = await jwtApi.get(`/api/announcements`, {params: announcementFilter})
            .then((response) => {
                console.log("Get/api/announcements");
                console.log(response);
                return response.data;
            });  
        } else {
            console.log("[NOT LOGIN] API");
            data = await api.get(`/api/announcements`, {params: announcementFilter})
                .then((response) => {
                    console.log("Get/api/announcements");
                    console.log(response);
                    return response.data;
                });
        }
        return data;
    },

    set: ({set}, data) => {
        console.log(data);
        set(announcementListState, data)
    }
})

