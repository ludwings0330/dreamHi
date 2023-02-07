import React from 'react';
import CastingData from 'dummydata/announcementDetailCastingData.json';

function AnnouncementDetailCasting(props) {
  const data = CastingData

  return (
    <div>
      {data.result.castings.map(function (casting, index) {
        console.log(casting)
        return (
          <div className='announcement-post-container'>
            <div className='announcement-content'> 배역 명 : {casting.name} </div>
            <div className='announcement-content'> 성별 : {casting.gender} </div>
            <div className='announcement-content'> 인원 : {casting.headCount} 명 </div>
            <div className='announcement-content'> 기타 상세 : {casting.description} </div>


          </div>
        );
      })}



    </div>
  );
}

export default AnnouncementDetailCasting;
