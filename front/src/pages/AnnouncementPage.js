import React, { useState } from 'react';
import AnnouncementList from '../components/Announcement/AnnouncementList';
import Button from '../components/Common/CommonComponent/Button';
import { useNavigate } from 'react-router-dom';
import AnnouncementData from '../dummydata/announcementData.json';
import Layout from '../components/Common/Layout';
import axios from 'axios';
import Layout from '../components/Common/MainLayout/Layout';
import AxiosTry from '../components/Announcement/AxiosTry';



console.log('여기는 공고 여기는 공고')

function AnnouncementPage(props) {
  // const token = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMSIsImF1dGgiOiJST0xFX1VTRVIiLCJlbWFpbCI6Inl1aG1pbjkyQGhhbm1haWwubmV0IiwiZXhwIjoxNjc3ODM0MTQ1fQ.vFVU8uv4lHETyf9tS8mrhxSLBUcz0Xy6xLm8XTZPwlVWMeliDY2-teDtxmmBy2Xs43ApGj5p7YvlC_w2entSFg'
  //
  // const getData = async () => {
  //   let response = await axios.get('httphttp://i8a702.p.ssafy.io:8085/api/my', {headers: { Authorization: `Baearer${token}`}});
  //   return response.data;
  // }
  //
  // let res = getData();
  //
  // res.then((data) => {
  //   console.log(123456788)
  //   console.log(data);
  // });
  const navigate = useNavigate();
 /* const announcements = AnnouncementData
  console.log(announcements)
  console.log(announcements[0].result.list[0].title);
  console.log(announcements[0].result.list[0].id);*/
  return (
    <>
      <Button
        title="글 작성하기"
        onClick={() => {
          navigate("/announcement/write")
        }}
      />

      <AxiosTry />


      <AnnouncementList
        announcements={AnnouncementData}
        onClickItem={(item) => {
          console.log(1234)
          navigate(`/announcement/${item.result.list[0].id}`);
        }}
      />
    </>
      {/*<button onClick={getData}> Getdata </button>*/}

    </Layout>
  );
}


export default AnnouncementPage;