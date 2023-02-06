import React from 'react';
import AnnouncementList from '../components/Announcement/AnnouncementList';
import Button from '../components/Common/CommonComponent/Button';
import { useNavigate } from 'react-router-dom';
import AnnouncementData from '../dummydata/announcementData.json';
import Layout from '../components/Common/MainLayout/Layout';



console.log('여기는 공고 여기는 공고')

function AnnouncementPage(props) {
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

      <AnnouncementList
        announcements={AnnouncementData}
        onClickItem={(item) => {
          console.log(1234)
          navigate(`/announcement/${item.result.list[0].id}`);
        }}
      />
    </>
  );
}


export default AnnouncementPage;