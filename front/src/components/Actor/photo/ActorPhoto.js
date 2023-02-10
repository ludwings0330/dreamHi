import React, { useEffect } from 'react';
import './ActorPhoto.css';
import ActorPhotoUpload from './ActorPhotoUpload';
import {
  actorProfileId,
  actorPhotoUrl,
  actorProfile,
  actorPhotoLists,
} from 'recoil/recoilActorState';
import { useRecoilValue, useRecoilState } from 'recoil';
import axios from 'axios';

const ActorPhoto = () => {
  const [actorPhotos, setActorPhotos] = useRecoilState(actorPhotoLists);
  // const actorPhotos = useRecoilValue(actorPhotoLists);

  const setSelected = (idx) => {
    document.querySelector(
      '.actor-photo-main',
    ).innerHTML = `<img src=${actorPhotos[idx].url} alt=${actorPhotos[idx]}/>`;
  };

  console.log(actorPhotos, '안되나요?');
  useEffect(() => {
    axios
      .get(`http://i8a702.p.ssafy.io:8085/api/actors/100001/media`)
      .then((res) => {
        setActorPhotos(res.data.result.pictures);
        console.log(res.data.result.pictures, '잘 찍히나요?');
      })
      .catch((err) => {
        console.log(err);
      });
  }, [setActorPhotos]);

  return (

    <div className="bg-white">
        <h1>프로필사진</h1>

        {/*프로필 사진 전체 container*/}
        <div className="actor-photo-whole">

          {/*프로필 사진 메인 container*/}
          <div className="actor-photo-main">
            <img src={actorPhotos[0].url} alt={actorPhotos[0].url} />
          </div>

          {/*프로필 사진 리스트 container*/}
          {actorPhotos.length > 0 &&
            actorPhotos.map((actorPhoto, idx) => (
              <div className="actor-photo-list"
                   key={idx}>
                <img
                  src={actorPhoto.url}
                  alt="image"
                  onClick={() => setSelected(idx)}
                />
              </div>
            ))}

          <ActorPhotoUpload />
        </div>
    </div>
  );
};

export default ActorPhoto;
