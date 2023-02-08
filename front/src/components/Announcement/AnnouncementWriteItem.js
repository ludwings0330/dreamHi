import React from 'react';
import {useRecoilValue} from "../../recoil";
import {
    announcementCrankPeriod, announcementDescription, announcementEndDate,
    announcementPayment,
    announcementProducerId,
    announcementTitle
} from "../../recoil/announcement";
import {useRecoilState} from "recoil";

function AnnouncementWriteItem(props) {



    const [dataTitle, setDataTitle] = useRecoilState(announcementTitle);
    const [dataProducerId, setDataProducerId] = useRecoilState(announcementProducerId);
    const [dataPayment, setDataPayment] = useRecoilState(announcementPayment);
    const [dataCrankPeriod, setDataCrankPeriod] = useRecoilState(announcementCrankPeriod);
    const [dataEndDate, setDataEndDate] = useRecoilState(announcementEndDate);
    const [dataDescription, SetDataDescription] = useRecoilState(announcementDescription);

    const handleDataChange = (e) => {
        setDataTitle({ ...dataTitle, [e.target.name]: e.target.value });
    };



    return (
        <div>


        </div>
    );
}

export default AnnouncementWriteItem;