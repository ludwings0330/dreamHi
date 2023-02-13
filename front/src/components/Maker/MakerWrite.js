import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useLocation } from 'react-router';

//import component
import MakerIntroduce from './Info/MakerIntroduce';
import MakerFilmo from './Filmo/MakerFilmo';
import MakerPeopleList from './MakerPeopleList/MakerPeopleList';
import MakerAnnouncementList from './AnnouncementList/MakerAnnouncementList';
import Button from '../Common/CommonComponent/Button';

const MakerWrite = () => {
  const navigate = useNavigate();

  const { state } = useLocation();
  console.log(state, 'state 전달 디테일에서 수정 인풋');

  return (
    <>
      <h1 className={'main-title'}>제작사 작성</h1>
      <MakerIntroduce />
      <MakerFilmo />
      <MakerPeopleList />
      <MakerAnnouncementList />
      <Button
        title="등록하기"
        onClick={() => {
          navigate(`/maker/detail/${state.userId}`, { state: state });
        }}
      />
    </>
  );
};

export default MakerWrite;
