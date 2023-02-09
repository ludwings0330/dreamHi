import React from 'react';
import {useRecoilState} from "../../recoil";
import {announcementDescription} from "../../recoil/announcement";

function AnnouncementDescription(props) {

    const [dataDescription, SetDataDescription] = useRecoilState(announcementDescription);

    return (
        <div>


        </div>
    );
}

export default AnnouncementDescription;