import React, { useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Button from '../Common/CommonComponent/Button';
import AnnouncementDetailItem from './AnnouncementDetailItem';
import { useRecoilValue, useRecoilState } from 'recoil';
import {
  announcementListDetailSelector,
  announcementDetailId,
  announcementListSelector,
  announcementListDetailState,
} from 'recoil/announcement/announcementStore';
import AnnouncementDetailProcess from './AnnouncementDetailProcess';
import AnnouncementDetailButton from './AnnouncementDetailButton';
import jwtApi from 'util/JwtApi';

function AnnouncementDetail(props) {
  console.log(1234);
  const navigate = useNavigate();
  // const announcementList = useRecoilValue(announcementListSelector())
  const { announcementId } = useParams();
  console.log('🐳🐳', announcementId);

  const announcement = useRecoilValue(announcementListDetailSelector(announcementId));
  const [annouoncementData, setAnnouncementData] = useRecoilState(announcementListDetailState);
  setAnnouncementData(announcement);

  // useEffect(() => {
  //   jwtApi.get(`/api/announcements/${announcementId}`).then((response) => {
  //     console.log('get /api/announcement Detail Data');
  //     console.log('😀😀😀😀😀😀', response.data);
  //     console.log(response.data.result);
  //     setAnnouncementData(response.data.result);
  //     console.log('141415', annouoncementData);
  //   });
  // }, []);

  useEffect(() => {
    console.log('change AnnouncementData', annouoncementData);
  }, [annouoncementData]);

  console.log('1414', annouoncementData);

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
