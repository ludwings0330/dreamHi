import React from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Button from '../Common/CommonComponent/Button';
import AnnouncementDetailItem from './AnnouncementDetailItem';
import { useRecoilValue, useRecoilState } from 'recoil';
import {
  announcementListDetailSelector,
  announcementDetailId,
  announcementListSelector,
} from 'recoil/announcement/announcementStore';
import AnnouncementDetailProcess from './AnnouncementDetailProcess';
import AnnouncementDetailButton from './AnnouncementDetailButton';

function AnnouncementDetail(props) {
  console.log(1234);
  const navigate = useNavigate();
  // const announcementList = useRecoilValue(announcementListSelector())
  const { announcementId } = useParams();
  console.log('🐳🐳', announcementId);

  const announcement = useRecoilValue(announcementListDetailSelector(announcementId));
  console.log('1414', announcement);

  // const announcement = announcementList.find((item) => {
  //     console.log(announcementId)
  //     console.log(item)
  //     return item.id == announcementId
  // })

  // const announcementDetail = AnnouncementDetailData

  // const announcementDetail = AnnouncementDetailData.find((item) => {
  //   return item.result.id == announcementId
  // })

  return (
    <div>
      <h1>공고상세페이지</h1>
      <Button
        title="뒤로 가기"
        onClick={() => {
          navigate('/announcement');
        }}
      />

      <br />
      <AnnouncementDetailProcess key={announcementId} announcement={announcement} />

      <AnnouncementDetailItem key={announcementId} announcement={announcement} />

      {/* <AnnouncementDetailButton key={announcementId} announcement={announcement} /> */}
    </div>
  );
}

export default AnnouncementDetail;
