import React, { useState, useEffect } from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { useRecoilValue } from 'recoil';
import {
  announcementCrankPeriod,
  announcementDescription,
  announcementEndDate,
  announcementPayment,
  announcementProducerId,
  announcementTitle,
} from 'recoil/announcement/announcement';
import { useRecoilState } from 'recoil';
import './AnnouncementWrite.css';

function AnnouncementWriteItem(props) {
  const [dataArray, setDataArray] = useState([]);

  const [dataTitle, setDataTitle] = useRecoilState(announcementTitle);
  const [dataProducerId, setDataProducerId] = useRecoilState(announcementProducerId);

  const [dataPayment, setDataPayment] = useRecoilState(announcementPayment);
  const [dataCrankPeriod, setDataCrankPeriod] = useRecoilState(announcementCrankPeriod);
  const [dataEndDate, setDataEndDate] = useState(new Date());
  const [endDate, setEndDate] = useRecoilState(announcementEndDate);

  const handleDataTitle = (e) => {
    // e.defaultPrevented
    setDataTitle(e.target.value);
  };

  const handleDataPayment = (e) => {
    setDataPayment(e.target.value);
  };

  const handleDataCrankPeriod = (e) => {
    setDataCrankPeriod(e.target.value);
  };

  const handleDataEndDate = (date) => {
    setDataEndDate(date);
  };

  const formattedDate = dataEndDate.toISOString().substr(0, 10);

  setEndDate(formattedDate);

  console.log('🤯🤯🤯🤯', endDate);

  return (
    <div id="announcement-write-info ">
      <div>
        <label>작품명</label> :{' '}
        <input
          type="text"
          name="dataTitle"
          value={dataTitle}
          placeholder="작품명을 입력해주세요"
          maxLength={20}
          onChange={handleDataTitle}
        />
      </div>

      <div>
        <label>급여</label> :{' '}
        <input
          type="text"
          name="dataPayment"
          value={dataPayment}
          placeholder="급여를 입력해주세요"
          maxLength={20}
          onChange={handleDataPayment}
        />
      </div>

      <div>
        <label>촬영 기간</label> :{' '}
        <input
          type="text"
          name="dataCrankPeriod"
          value={dataCrankPeriod}
          placeholder="촬영 기간을 입력해주세요"
          maxLength={20}
          onChange={handleDataCrankPeriod}
        />
      </div>

      <div>
        <label>공고 마감 날짜</label>
        <DatePicker
          selected={dataEndDate}
          onChange={handleDataEndDate}
          value={dataEndDate}
          dateFormat="yyyy-MM-dd"
        />
      </div>
    </div>
  );
}

export default AnnouncementWriteItem;
