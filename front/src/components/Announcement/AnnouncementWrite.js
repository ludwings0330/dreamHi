import React from 'react';
import AnnouncementWriteItem from './AnnouncementWriteItem';
import Button from '../Common/CommonComponent/Button';
import {useNavigate} from 'react-router-dom';
import axios from 'axios';

import {useRecoilValue} from 'recoil';
import {
    announcementCastingState,
    announcementCrankPeriod,
    announcementDescription,
    announcementEndDate,
    announcementPayment,
    announcementPictureUrl,
    announcementProducerId,
    announcementTitle,
} from 'recoil/announcement/announcement';
import AnnouncementWriteCasting from './AnnouncementWriteCasting';
import ImageUpload from "imageup/ImageUpload";

function AnnouncementWrite(props) {
    const token = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAwMDEiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZW1haWwiOiJkZGY5OThAZ21haWwuY29tIiwiZXhwIjoxNjc4MjU2MjEyfQ.gSBnEPdb7LPDgTMwi5fDDlEdYxgbdJ6hInbddudS9suerZhCPuHDV3P9C6ygWTacOvhfT9tS8i94LP1qSszc0w';

    const navigate = useNavigate();

  const announcementImg = useRecoilValue(announcementPictureUrl);
  const announcementCasting = useRecoilValue(announcementCastingState);
  const dataTitle = useRecoilValue(announcementTitle);
  const dataProducerId = useRecoilValue(announcementProducerId);
  const dataPayment = useRecoilValue(announcementPayment);
  const dataCrankPeriod = useRecoilValue(announcementCrankPeriod);
  const dataEndDate = useRecoilValue(announcementEndDate);
  const dataDescription = useRecoilValue(announcementDescription);

  const sendData = {
    title: dataTitle,
    producerId: 50002,
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
      url: 'http://i8a702.p.ssafy.io:8085/api/announcements',
      data: sendData,
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        alert('성공');
        console.log(res.data);
      })
      .catch((error) => {
        console.log('실패실패');
        console.log(error);
        console.log(sendData)
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
      {/*<p> { ImgUrl} </p>*/}
        <ImageUpload/>
      <AnnouncementWriteItem />
      <AnnouncementWriteCasting />
        
        <button onClick={postClick}>공고다공고</button>

      {/*<Button*/}
      {/*  title="공고 등록 하기"*/}
      {/*  onClick={() => {*/}
      {/*    postClick();*/}
      {/*  }}*/}
      {/*/>*/}

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