import React from "react";
import {useRecoilValue} from "recoil";
import { vlounteerUserIdState } from "recoil/volunteer/volunteerStore";

export default function VolunteerCard() {
    const voluteerUserId = useRecoilValue(vlounteerUserIdState);

    return (
        <h1>{voluteerUserId}</h1>
    )
}