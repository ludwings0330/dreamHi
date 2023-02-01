import React from 'react';
import AnnouncementWriteItem from './AnnouncementWriteItem';
import Button from '../Common/Button';
import { useNavigate } from 'react-router-dom';
import Layout from '../Common/Layout';

function AnnouncementWrite(props) {
  const navigate = useNavigate();
  return (
    <Layout>
      <h1>공고 작성</h1>
      <Button
        title="뒤로 가기"
        onClick={() => {
          navigate("/announcement")
        }}
      />
      <AnnouncementWriteItem />
    </Layout>
  );
}

export default AnnouncementWrite;