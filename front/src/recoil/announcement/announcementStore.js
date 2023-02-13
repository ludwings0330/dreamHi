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
        size: 10
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
            size: 10
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
        let data = null;
        if(userSimple.id) {
            console.log("LOGIN API");
            data = await jwtApi.get(`/api/announcements`, {params: announcementFilter})
            .then((response) => {
                console.log('logedin')
                console.log("Get/api/announcements");
                console.log(response);
                return response.data.result.content;
            });  
        } else {
            console.log("[NOT LOGIN] API");
            data = await api.get(`/api/announcements`, {params: announcementFilter})
                .then((response) => {
                    console.log('로그인 안 된 상태')
                    console.log("Get/api/announcements");
                    console.log(response);
                    return response.data.result.content;
                });
        }
        return data;
    },

    set: ({set}, data) => {
        console.log(data);
        set(announcementListState, data)
    }
})

