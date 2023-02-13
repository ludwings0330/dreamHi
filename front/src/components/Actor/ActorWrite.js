import React from 'react';
import { useNavigate } from 'react-router-dom';

import ActorIntroduce from './info/ActorIntroduce';
import ActorFilmo from './filmo/ActorFilmo';
import ActorPhoto from './photo/ActorPhoto';
import ActorVideo from './video/ActorVideo';
import Button from '../Common/CommonComponent/Button';

import { useLocation } from 'react-router';

const ActorWrite = () => {
  const navigate = useNavigate();

  const { state } = useLocation();
  console.log(state, 'state 전달 디테일에서 수정 인풋');

  return (
    <>
      <h1 className={'main-title'}>이력서 작성</h1>
      <ActorIntroduce />
      <ActorFilmo />
      <ActorPhoto />
      <ActorVideo />
      <Button
        title="등록하기"
        onClick={() => {
          navigate(`/actor/detail/${state.userId}`, { state: state });
        }}
      />
    </>
  );
};

export default ActorWrite;
