import React, { useEffect } from 'react';
import './ActorPhoto.css';
import ActorPhotoUpload from './ActorPhotoUpload';
import {
  actorProfileId,
  actorPhotoUrl,
  actorProfile,
  actorPhotoLists,
} from 'recoil/actor/actorStore';
import { useRecoilValue, useRecoilState } from 'recoil';
import axios from 'axios';
import { API_BASE_URL } from '../../../constants';
import jwtApi from '../../../util/JwtApi';
import {} from './ActorPhoto.css';
import { userSimpleState } from '../../../recoil/user/userStore';

const ActorPhoto = ({ actorInfo }) => {
  const [actorPhotos, setActorPhotos] = useRecoilState(actorPhotoLists);
  const [userInfo, setUserInfo] = useRecoilState(userSimpleState);
  const setSelected = (idx) => {
    document.querySelector(
      '.actor-photo-main',
    ).innerHTML = `<img src=${actorPhotos[idx].url} alt=${actorPhotos[idx]}/>`;
  };

  return (
    <>
      <div id={'actor-photo-whole'}>
        <h1 className={'actor-photo-title'}>프로필사진</h1>
        <div className={'actor-photo-list-main'}>
          <div className={'actor-photo-list'}>
            {/*메인이미지 부분*/}
            {actorPhotos && actorPhotos.length > 0 ? (
              <div className="actor-photo-main">
                <img src={actorPhotos[0].url} alt={actorPhotos[0].url} />
              </div>
            ) : null}
            {actorPhotos &&
              actorPhotos.length > 0 &&
              actorPhotos.map((actorPhoto, idx) => (
                <div className="actor-photo" key={actorPhoto.id}>
                  <img
                    src={actorPhoto.url}
                    alt="image"
                    className="actor-photo-list-item"
                    loading={'lazy'}
                    onClick={() => setSelected(idx)}
                  />
                </div>
              ))}
          </div>
          {userInfo && userInfo.id === actorInfo.userId ? (
            <ActorPhotoUpload actorInfo={actorInfo} />
          ) : null}
        </div>
      </div>
    </>
  );
};

export default ActorPhoto;
