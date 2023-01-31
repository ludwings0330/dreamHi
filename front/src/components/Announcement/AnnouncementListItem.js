import React from 'react';
import Button from '../Common/Button';
import { useNavigate } from 'react-router-dom';


function AnnouncementListItem(props) {
  const navigate = useNavigate();
  return (
    <div>
      <h3>공고 리스트 아이템</h3>
      <Button
        title="글 작성하기"
        onClick={() => {
          navigate("/announcementwrite")
        }}
      />


    </div>
  );
}

export default AnnouncementListItem;