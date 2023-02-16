import React from 'react';
import { useRecoilState } from 'recoil';
import { announcementDescription } from 'recoil/announcement/announcement';

function AnnouncementDescription(props) {
  const [dataDescription, SetDataDescription] = useRecoilState(announcementDescription);

  const handleDescription = (e) => {
    // e.defaultPrevented
    SetDataDescription(e.target.value);
  };

  return (
    <div>
      <label>공고 상세 등록</label> :{' '}
      <textarea
        type="text"
        name="description"
        placeholder="작품 상세 정보를 입력해주세요"
        onChange={handleDescription}
      />
    </div>
  );
}

export default AnnouncementDescription;
