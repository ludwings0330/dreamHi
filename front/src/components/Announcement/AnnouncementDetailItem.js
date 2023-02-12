import React from 'react';
// import annoumcementdetail from 'dummydata/announcementDetailData.json';
import Button from "../Common/CommonComponent/Button";
import AnnouncementDetailCasting from './AnnouncementDetailCasting';

function AnnouncementDetailItem(props) {
  const { announcement } = props;
  const { announcementId } = announcement.id
  console.log(99977733115);
  console.log(announcement);
  return (
      <div>
        <div>
          <img src={announcement.pictureUrl} />
          <p>조회수: {announcement.hit}</p>
          <p>작품명: {announcement.title}</p>
          <p>제작사: {announcement.producer.name}</p>
          <p>출연료: {announcement.payment} </p>
          <p>촬영 기간: {announcement.crankPeriod}</p>
          <p>모집 마감: {announcement.endDate}</p>
          <div className="announcement-list-item-casting-wrapper">
            <h4>배역 상세</h4>
            <AnnouncementDetailCasting 
            key={ announcementId }
            announcement = { announcement }/>
          </div>
          <p>상세내용: {announcement.description}</p>
        </div>

        <Button title="지원하기" />
      </div>
  );
}

export default AnnouncementDetailItem;
