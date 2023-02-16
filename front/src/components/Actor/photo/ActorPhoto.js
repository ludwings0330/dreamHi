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
      <div className="bg-white">
        <div className="mx-auto max-w-2xl py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
          <h1 className={'actor-photo-title'}>프로필사진</h1>
          {actorPhotos && actorPhotos.length > 0 ? (
            <div className={'actor-photo-whole'}>
              {/*메인이미지 부분*/}
              <div className="actor-photo-main">
                <img src={actorPhotos[0].url} alt={actorPhotos[0].url} />
              </div>

              {actorPhotos.map((actorPhoto, idx) => (
                <div className="actor-photo-list" key={actorPhoto.id}>
                  <img
                    src={actorPhoto.url}
                    alt="image"
                    object-fit={'contain'}
                    className="object-center"
                    onClick={() => setSelected(idx)}
                  />
                </div>
              ))}
            </div>
          ) : null}

          {userInfo.id === actorInfo.userId ? <ActorPhotoUpload actorInfo={actorInfo} /> : null}

        </div>
      </div>
    </>
  );
};

export default ActorPhoto;
