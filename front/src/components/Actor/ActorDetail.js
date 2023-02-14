import React, { useEffect, useState } from 'react';
import { useRecoilState } from 'recoil';
import { useNavigate, useParams } from 'react-router-dom';

//import component
import { useLocation } from 'react-router';

//import recoil
import { actorFilmoLists, actorPhotoLists, actorVideoLists } from 'recoil/actor/actorStore';
import jwtApi from '../../util/JwtApi';
import { Button } from '@mui/material';
import ActorFilmo from './filmo/ActorFilmo';

const ActorDetail = () => {
  const { state } = useLocation();
  const navigate = useNavigate();
  const { userId } = useParams();

  const [actorFilmos, setActorFilmos] = useRecoilState(actorFilmoLists);
  const [actorPhotos, setActorPhotos] = useRecoilState(actorPhotoLists);
  const [actorVideos, setActorVideos] = useRecoilState(actorVideoLists);
  const [actorInfo, setActorInfo] = useState({});

  // 최초 실행시 동작하는 useEffect
  // 배우 프로필 기본 정보 조회
  useEffect(() => {
    const fetchData = async () => {
      const actorInfo = await jwtApi
        .get(`/api/users/${userId}/actor-profile`)
        .then((response) => response.data.result);
      setActorInfo(actorInfo);
      console.log(actorInfo);

      const media = await jwtApi.get(`/api/actors/${actorInfo.actorProfileId}/media`);
      setActorPhotos(media.data.result.picture);
      setActorVideos(media.data.result.videos);
      console.log(media);

      // 필모그래피 정보아무것도 안줫을때 너무 조회를 많이함 최대 5 개만 가져오도록 수정.
      const filmographies = await jwtApi.get(`/api/filmographies`, {
        params: { actorId: actorInfo.actorProfileId },
      });
      setActorFilmos(filmographies.data.result);
      console.log(filmographies);
    };
    console.log(actorInfo);
    fetchData();
  }, []);

  if (JSON.stringify(actorInfo) === '{}') {
    return null;
  }

  return (
    <>
      <div className="photo" width={'400px'} height={'500px'}>
        <img src={actorInfo.pictureUrl} alt="image" className="object-center" />
      </div>
      <div>{actorInfo.description}</div>

      <div>{actorInfo.title}</div>
      <div>{actorInfo.gender}</div>
      <div>{actorInfo.age}</div>
      <div>{actorInfo.height}</div>
      {actorInfo.styles.length > 0 &&
        actorInfo.styles.map((actor, idx) => <div key={idx}>{actor['description']}</div>)}
      <div>{actorInfo.phone}</div>
      <div>{actorInfo.email}</div>
      <ActorFilmo actorId={actorInfo.actorProfileId} />
      {/*<ActorPhoto />*/}
      {/*<ActorVideo />*/}

      <Button
        title="수정하기"
        variant="contained"
        onClick={() => navigate('/actor/write', { state: actorInfo })}
      >
        수정하기
      </Button>

      <Button variant="contained" title="삭제하기" onClick={() => navigate('/actor/delete')}>
        삭제하기
      </Button>
    </>
  );
};

export default ActorDetail;
