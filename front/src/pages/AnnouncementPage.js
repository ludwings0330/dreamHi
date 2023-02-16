import React, { useState } from 'react';
import AnnouncementList from '../components/Announcement/AnnouncementList';
import Button from '../components/Common/CommonComponent/Button';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import AxiosTry from '../components/Announcement/AxiosTry';
import { useRecoilValue } from 'recoil';
import { userSimpleState } from 'recoil/user/userStore';

function AnnouncementPage(props) {
  const navigate = useNavigate();
  const userSimple = useRecoilValue(userSimpleState);

  return (
    <>
      <Button
        title="글 작성하기"
        onClick={() => {
          if (userSimple) {
            navigate('/announcement/write');
          } else {
            navigate('/login');
          }
        }}
      />

      <AnnouncementList />

      {/* 
      <AnnouncementList
        announcements={AnnouncementData}
        onClickItem={(item) => {
          console.log(1234)
          navigate(`/announcement/${item.result.list[0].id}`);
        }}
      /> */}
      {/*<button onClick={getData}> Getdata </button>*/}
    </>
  );
}

export default AnnouncementPage;
