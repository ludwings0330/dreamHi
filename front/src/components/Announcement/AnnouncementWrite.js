import React, { useState, useEffect } from 'react';
import AnnouncementWriteItem from './AnnouncementWriteItem';
import Button from '../Common/CommonComponent/Button';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { writeAnnouncement } from './AnnouncementAxios';
import jwtApi from 'util/JwtApi';

import { useRecoilValue } from 'recoil';
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
import ImageUpload from 'imageup/ImageUpload';
import './AnnouncementWrite.css';
import { styled } from '../../../node_modules/@mui/material/styles';
import Box from '../../../node_modules/@mui/material/Box';
import Grid from '../../../node_modules/@mui/material/Grid';
import Chip from '../../../node_modules/@mui/material/Chip';

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
  const [producerId, setProducerId] = useState('');

  useEffect(() => {
    jwtApi
      .get(`/api/producers`, { params: { involve: true } })
      .then((response) => {
        console.log('😡😠😠😱', response);
        console.log(response.data.result.content[0].id);
        setProducerId(response.data.result.content[0].id);
      })
      .catch((e) => console.log(e));
  }, []);

  const sendData = {
    title: dataTitle,
    producerId: producerId,
    payment: dataPayment,
    crankPeriod: dataCrankPeriod,
    endDate: dataEndDate,
    description: dataDescription,
    pictureUrl: announcementImg,
    castings: announcementCasting,
  };

  const postClick = () => {
    jwtApi
      .post('/api/announcements/', sendData)
      .then((response) => {
        console.log('😎😍😘🥰', response);
        console.log('sendData', sendData);
        alert('글 작성이 완료되었습니다.');
        navigate('/announcement');
      })
      .catch((e) => {
        console.log('sendData', sendData);
        alert(e);
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

      <Grid id="announcement-write-full">
        <Grid container spacing={2}>
          <Grid item xs={6}>
            <ImageUpload />
          </Grid>
          <Grid item xs={4}>
            <AnnouncementWriteItem />
          </Grid>
        </Grid>
      </Grid>

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
