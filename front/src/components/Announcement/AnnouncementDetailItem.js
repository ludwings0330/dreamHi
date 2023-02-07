import React from 'react';
import annoumcementdetail from 'dummydata/announcementDetailData.json';
import Button from '../Common/CommonComponent/Button';
import AnnouncementDetailCasting from './AnnouncementDetailCasting';

function AnnouncementDetailItem(props) {
  const { announcementDetail } = props;
  console.log(99977733115);
  console.log(announcementDetail);
  return (
    <div>
      <div>
        <img src={`/logo192.png`} />
        <p>조회수: {announcementDetail.result.hit}</p>
        <p>작품명: {announcementDetail.result.title}</p>
        <p>제작사: {announcementDetail.result.producer.name}</p>
        <p>출연료: {announcementDetail.result.payment} </p>
        <p>촬영 기간: {announcementDetail.result.crankPeriod}</p>
        <p>모집 마감: {announcementDetail.result.endDate}</p>
        <div className="announcement-list-item-casting-wrapper">
          <h4>배역 상세</h4>
          <AnnouncementDetailCasting />
        </div>
        <p>상세내용: {announcementDetail.result.description}</p>
      </div>

      <Button title="지원하기" />
    </div>
  );
}

export default AnnouncementDetailItem;
