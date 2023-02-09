import React, { useState } from 'react';
import AnnouncementWriteItem from './AnnouncementWriteItem';
import Button from '../Common/CommonComponent/Button';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

import { useRecoilState, useRecoilValue } from 'recoil';
import {
  announcementCastingState,
  announcementCrankPeriod,
  announcementDescription,
  announcementEndDate,
  announcementPayment,
  announcementPictureUrl,
  announcementProducerId,
  announcementTitle,
} from '../../recoil/announcement';
import AnnouncementWriteCasting from './AnnouncementWriteCasting';
import MultiSelect from './SelectExample';

function AnnouncementWrite(props) {
  const navigate = useNavigate();

  const announcementImg = useRecoilValue(announcementPictureUrl);
  const announcementCasting = useRecoilValue(announcementCastingState);
  const dataTitle = useRecoilValue(announcementTitle);
  const dataProducerId = useRecoilValue(announcementProducerId);
  const dataPayment = useRecoilValue(announcementPayment);
  const dataCrankPeriod = useRecoilValue(announcementCrankPeriod);
  const dataEndDate = useRecoilValue(announcementEndDate);
  const dataDescription = useRecoilValue(announcementDescription);

  const token = {};

  const sendData = {
    title: dataTitle,
    producerId: dataProducerId,
    payment: dataPayment,
    crankPeriod: dataCrankPeriod,
    endDate: dataEndDate,
    description: dataDescription,
    pictureUrl: announcementImg,
    castings: announcementCasting,
  };

  const postClick = () => {
    axios({
      method: 'POST',
      url: 'http://i8a702.p.ssafy.io:8085/api/producers?name={sendData.name}',
      data: sendData,
      headers: {
        Authorization: `Bearer${token}`,
      },
    })
      .then((res) => {
        alert('성공');
        console.log(res.data);
      })
      .catch((error) => {
        console.log('실패실패');
        console.log(error);
      });
  };

  return (
    <>
      <h1>공고 작성</h1>
      <Button
        title="뒤로 가기"
        onClick={() => {
          navigate('/announcement');
        }}
      />
      {/*<button onClick={postClick}>Post</button>*/}
      <img src={announcementImg} />
      {/*<p> { ImgUrl} </p>*/}
      <AnnouncementWriteItem />
      <AnnouncementWriteCasting />

      <Button
        title="공고 등록 하기"
        onClick={() => {
          postClick();
        }}
      />

      {/*<MultiSelect/>*/}

      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
    </>
  );
}

export default AnnouncementWrite;