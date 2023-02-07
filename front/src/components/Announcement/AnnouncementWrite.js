import React, { useState } from 'react';
import AnnouncementWriteItem from './AnnouncementWriteItem';
import Button from '../Common/CommonComponent/Button';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';


import { useRecoilState, useRecoilValue } from 'recoil';
import { announcementImageUrl } from '../../recoil/announcement';

function AnnouncementWrite(props) {
  const navigate = useNavigate();

  // const ImgUrl = useRecoilValue()
  const announcementImg = useRecoilValue(announcementImageUrl)
  const token = {}

  const sendData = {
    title: 'title',
    producerId: 1001,
    payment: '협의 후 결정',
    crankPeriod: '6개월',
    endDate: '2023-02-27T10:00:00',
    description: '공고설명',
    pictureUrl: { announcementImg },
    castings: [
      {
        name: '배역명',
        description: '배역설명',
        headcount: 1,
        minHeight: 130,
        maxHeight: 150,
        minAge: 5,
        maxAge: 10,
        gender: 'MALE',
        styles: [1],
      },
    ],
  };

  const postClick = () => {
    axios({
      method: 'POST',
      url: 'http://i8a702.p.ssafy.io:8085/api/producers?name={sendData.name}',
      data: sendData,
      headers: {
        Authorization:
          `Bearer${token}`,
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

      <Button
          title="공고 등록 하기"
          onClick={() => {
            postClick();
            navigate('/announcement');
          }}
      />
    </>
  );
}

export default AnnouncementWrite;