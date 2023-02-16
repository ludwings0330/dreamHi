import React, { useEffect, useState } from 'react';
import { useRecoilState } from 'recoil';
import { useNavigate, useParams } from 'react-router-dom';

//import component
import { useLocation } from 'react-router';
import {} from './ActorDetail.css';

//import recoil
import { actorFilmoLists, actorPhotoLists, actorVideoLists } from 'recoil/actor/actorStore';
import jwtApi from '../../util/JwtApi';
import { Button } from '@mui/material';
import ActorFilmo from './filmo/ActorFilmo';
import ActorPhoto from './photo/ActorPhoto';
import ActorVideo from './video/ActorVideo';
import { userSimpleState } from '../../recoil/user/userStore';

const ActorDetail = (props) => {
  const navigate = useNavigate();
  const [userInfo, setUserInfo] = useRecoilState(userSimpleState);
  let { userId } = useParams();
  if (!userId) {
    userId = props.userId;
  }
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

      const media = await jwtApi.get(`/api/actors/${actorInfo.actorProfileId}/media`);
      setActorPhotos(media.data.result.pictures);
      setActorVideos(media.data.result.videos);

      // 필모그래피 정보아무것도 안줫을때 너무 조회를 많이함 최대 5 개만 가져오도록 수정.
      const filmographies = await jwtApi.get(`/api/filmographies`, {
        params: { actorId: actorInfo.actorProfileId },
      });

      setActorFilmos(filmographies.data.result);
    };

    fetchData();
  }, []);

  if (JSON.stringify(actorInfo) === '{}') {
    return null;
  }

  return (
    <>
      <div id={'actor-detail-full'}>
        <div id={'actor-main-profile'}>
          <img src={actorInfo.pictureUrl} alt="image" className="object-center" />
        </div>

        <div id={'actor-detail-info'}>
          <div className={'actor-description-content'}>{actorInfo.description}</div>
          <div>{actorInfo.title}</div>
          <div>{actorInfo.gender}</div>
          <div>{actorInfo.age}</div>
          <div>{actorInfo.height}</div>
          {actorInfo.styles.length > 0 &&
            actorInfo.styles.map((actor, idx) => <div key={idx}>{actor['description']}</div>)}
          <div>{actorInfo.phone}</div>
          <div>{actorInfo.email}</div>
        </div>
      </div>

      <ActorFilmo actorInfo={actorInfo} />
      <ActorPhoto actorInfo={actorInfo} />
      <ActorVideo actorInfo={actorInfo} />

      {userInfo.id === actorInfo.userId ? (
        <>
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
      ) : null}
    </>
  );
};

export default ActorDetail;
