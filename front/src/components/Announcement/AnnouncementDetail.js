import React from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import AnnouncementData from '../../dummydata/announcementData.json'
import AnnouncementDetailData from '../../dummydata/announcementDetailData.json'
import Button from "../Common/CommonComponent/Button";
import AnnouncementDetailItem from './AnnouncementDetailItem';


function AnnouncementDetail(props) {
  const navigate = useNavigate()
  const { announcementId } = useParams()
  const announcement = AnnouncementData.find((item) => {
    console.log(33333333)
    console.log(item.result.list[0].id)
    console.log(55555555555)
    console.log(announcementId)
    return item.result.list[0].id == announcementId
  })
  const announcementDetail = AnnouncementDetailData
  console.log(AnnouncementDetailData.result.id)
  console.log(AnnouncementDetailData.result.description)
  console.log(announcementDetail)


  // const announcementDetail = AnnouncementDetailData.find((item) => {
  //   console.log(777777777777)
  //   console.log(item.result.id)
  //   return item.result.id == announcementId
  // })


  return (
    <div>
      <h1>공고상세페이지</h1>
      <Button
        title="뒤로 가기"
        onClick={() => {
          navigate("/announcement")
        }}
      />

      <br />

      {announcement.result.list[0].state === '모집중' ? '⭕' : '❌'}


      <AnnouncementDetailItem
        key={announcementDetail.result.id}
        announcementDetail={announcementDetail}/>

    </div>
  );
}

export default AnnouncementDetail;