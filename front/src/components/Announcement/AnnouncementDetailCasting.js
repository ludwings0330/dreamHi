import React from 'react';
import { useRecoilValue } from 'recoil';
import { announcementListDetailCastingSelector } from 'recoil/announcement/announcementStore';

function AnnouncementDetailCasting(props) {
  const { announcement } = props;
  const CastingData = useRecoilValue(announcementListDetailCastingSelector(announcement.id));

  const data = CastingData;

  return (
    <div>
      {data.map(function (casting, index) {
        return (
          <div key={casting.id} className="announcement-post-container">
            <div className="announcement-content"> 배역 명 : {casting.name} </div>
            <div className="announcement-content"> 성별 : {casting.gender} </div>
            <div className="announcement-content"> 인원 : {casting.headcount} 명 </div>
            <div className="announcement-content"> 기타 상세 : {casting.description} </div>
            <div className="announcement-content">
              {' '}
              스타일 :{' '}
              {casting.styles.map(function (style, index) {
                return <li key={style.id}>{style.description}</li>;
              })}{' '}
            </div>
          </div>
        );
      })}
    </div>
  );
}

export default AnnouncementDetailCasting;
