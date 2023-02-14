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

const ActorPhoto = ({ actorInfo }) => {
  const [actorPhotos, setActorPhotos] = useRecoilState(actorPhotoLists);

  const setSelected = (idx) => {
    document.querySelector(
      '.actor-photo-main',
    ).innerHTML = `<img src=${actorPhotos[idx].url} alt=${actorPhotos[idx]}/>`;
  };

  return (
    <>
      <div className="bg-white">
        <div className="mx-auto max-w-2xl py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
          <h1>프로필사진</h1>
          {actorPhotos && actorPhotos.length > 0 ? (
            <div className="list-container">
              {/*메인이미지 부분*/}
              <div className="actor-photo-main">
                <img src={actorPhotos[0].url} alt={actorPhotos[0].url} />
              </div>

              {actorPhotos.map((actorPhoto, idx) => (
                <div className="actor-photo" key={actorPhoto.id} width={'200px'} height={'200px'}>
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
          <ActorPhotoUpload actorInfo={actorInfo} />
        </div>
      </div>
    </>
  );
};

export default ActorPhoto;
