import React, { useEffect, useState } from 'react';
import { useRecoilState } from 'recoil';
import { useParams } from 'react-router-dom'
import axios from 'axios'

import ActorIntroduce from './info/ActorIntroduce';
import ActorFilmo from './filmo/ActorFilmo';
import ActorPhoto from './photo/ActorPhoto';
import ActorVideo from './video/ActorVideo';

import { actorProfile, actorFilmoUrl, actorPhotoUrl, actorVideoUrl, actorPhotoLists } from './recoilActorState'

const ActorDetail = () => {
  const { actorProfileId } = useParams()
  const token = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAwMDEiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZW1haWwiOiJkZGY5OThAZ21haWwuY29tIiwiZXhwIjoxNjc4MjU2MjEyfQ.gSBnEPdb7LPDgTMwi5fDDlEdYxgbdJ6hInbddudS9suerZhCPuHDV3P9C6ygWTacOvhfT9tS8i94LP1qSszc0w';

  const [actorPhotos, setActorPhotos] = useRecoilState(actorPhotoLists);
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
        console.log(error, '이래서 실패했다');
      });
  }, [setActorInfo]);


  useEffect(() => {
    axios.get(`http://i8a702.p.ssafy.io:8085/api/actors/100001/media`)
      // axios.get(`http://i8a702.p.ssafy.io:8085/api/actors/${actorInfo.actorProfileId}/media`)
      .then((res) => {
        setActorPhotos(res.data.result.pictures)
      })
      .catch((err) => {
        console.log(err)
      })
  }, [setActorPhotos]);

  const actorUrl = `${actorInfo.name}_${actorInfo.actorProfileId}`
  const [actFilmoUrl, setActFilmoUrl] = useRecoilState(actorFilmoUrl);
  const [actPhotoUrl, setActPhotoUrl] = useRecoilState(actorPhotoUrl);
  const [actVideoUrl, setActVideoUrl] = useRecoilState(actorVideoUrl);

  setActFilmoUrl(`images/${actorUrl}/filmo`)
  setActPhotoUrl(`images/${actorUrl}/photo`)
  setActVideoUrl(`images/${actorUrl}/video`)

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