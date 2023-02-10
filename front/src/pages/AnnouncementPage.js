import React, { useState } from 'react';
import AnnouncementList from '../components/Announcement/AnnouncementList';
import Button from '../components/Common/CommonComponent/Button';
import { useNavigate } from 'react-router-dom';
import AnnouncementData from '../dummydata/announcementData.json';
import axios from 'axios';
import AxiosTry from '../components/Announcement/AxiosTry';



function AnnouncementPage(props) {
  
  const navigate = useNavigate();


  return (
    <>
      <Button
        title="글 작성하기"
        onClick={() => {
          navigate("/announcement/write")
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