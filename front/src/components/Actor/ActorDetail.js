import React, {useEffect, useState} from 'react';
import { useRecoilState } from 'recoil';
import { useParams } from 'react-router-dom'
import axios from 'axios'

import Layout from '../Common/MainLayout/Layout';
import ActorIntroduce from './info/ActorIntroduce';
import ActorFilmo from './filmo/ActorFilmo';
import ActorPhoto from './photo/ActorPhoto';
import ActorVideo from './video/ActorVideo';

import { actorProfile } from './recoilActorState'

const ActorDetail = () => {
  const { actorProfileId } = useParams()
  const token = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAzIiwiYXV0aCI6IlJPTEVfVVNFUiIsImVtYWlsIjoiZGRmOTk4QGdtYWlsLmNvbSIsImV4cCI6MTY3ODIzNDgwMX0.B-xblykNgvy8DSacYxAUzQCxEkXxqdEi8yXJaKlm3p8Y96rxR0wkvTaEUU_0e-jLqXSXezDDLi5jSA9Imf_A1g';

  const [actorInfo, setActorInfo] = useRecoilState(actorProfile);

  // api 요청 보내서 배우 목록 확보
  useEffect(() => {
    axios.get('http://i8a702.p.ssafy.io:8085/api/actors',
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setActorInfo(res.data.result.content.filter(actor => actor.actorProfileId == actorProfileId)[0])
      })
      .catch((error) => {
        console.log('실패실패ㅠㅠ');
        console.log(error);
      });

  }, [setActorInfo]);

  console.log(actorInfo, '배우모ㅗㅗㅗㅗㅗㅗㅗㅗㅗ록')

  return (
    <>
      <ActorIntroduce />
      <ActorFilmo />
      <ActorPhoto />
      <ActorVideo />
    </>
  );
};

export default ActorDetail;