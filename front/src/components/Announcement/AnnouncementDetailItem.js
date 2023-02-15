import React from 'react';
// import annoumcementdetail from 'dummydata/announcementDetailData.json';
import Button from '../Common/CommonComponent/Button';
import AnnouncementDetailCasting from './AnnouncementDetailCasting';
import './AnnouncementDetail.css';

function AnnouncementDetailItem(props) {
  const { announcement } = props;
  const { announcementId } = announcement.id;
  return (
    <div>
      <div id="announcement-detail-full">
        <div id="announcement-main-profile ">
          <img src={announcement.pictureUrl} alt="image" />
        </div>
        <div id="announcement-detail-info">
          <p>조회수: {announcement.hit}</p>
          <p>작품명: {announcement.title}</p>
          <p>제작사: {announcement.producer.name}</p>
          <p>출연료: {announcement.payment} </p>
          <p>촬영 기간: {announcement.crankPeriod}</p>
          <p>모집 마감: {announcement.endDate}</p>
        </div>
      </div>
      <h5 id="announcement-detail-full">배역 상세</h5>
      <div id="announcement-detail-full">
        <AnnouncementDetailCasting key={announcementId} announcement={announcement} />
      </div>
      <h6 id="announcement-detail-full">상세내용: {announcement.description}</h6>
    </div>
  );
}

export default AnnouncementDetailItem;
