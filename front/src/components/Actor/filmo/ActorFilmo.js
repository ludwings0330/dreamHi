import React, { useEffect } from 'react';
import './ActorFilmo.css';
import ActorFilmoUpload from './ActorFilmoUpload';
import {
  actorProfileId,
  actorFilmoUrl,
  actorProfile,
  actorFilmoLists,
} from 'recoil/actor/actorStore';
import { useRecoilValue, useRecoilState } from 'recoil';
import axios from 'axios';
import { API_BASE_URL } from '../../../constants';

const ActorFilmo = () => {
  const [actorFilmos, setActorFilmos] = useRecoilState(actorFilmoLists);

  const setSelected = (idx) => {
    document.querySelector(
      '.actor-filmo-main',
    ).innerHTML = `<img src=${actorFilmos[idx].url} alt=${actorFilmos[idx]}/>`;
  };

  console.log(actorFilmos, '안되는');

  console.log(actorFilmos, '안되는222222');
  useEffect(() => {
    console.log(`${API_BASE_URL}/api/actors/100001/media`);
    axios
      .get(`{API_BASE_URL}/api/actors/100001/media`)
      .then((res) => {
        setActorFilmos(res.data.result.pictures);
        console.log(res.data.result.pictures, '잘 찍히나요?');
      })
      .catch((err) => {
        console.log(err);
      });
  }, [setActorFilmos]);

  return (
    <div className="bg-white">
      <div className="mx-auto max-w-2xl py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
        <h1>필모그래피</h1>

        <div className="list-container">
          {/*메인이미지 부분*/}
          <div className="actor-filmo-main">
            <img src={actorFilmos[0].url} alt={actorFilmos[0].url} />
          </div>

          {actorFilmos.length > 0 &&
            actorFilmos.map((actorFilmo, idx) => (
              <div className="actor-filmo" key={idx} width={'200px'} height={'200px'}>
                <img
                  src={actorFilmo.url}
                  alt="image"
                  className="object-center"
                  onClick={() => setSelected(idx)}
                />
              </div>
            ))}

          <ActorFilmoUpload actorFilmos={actorFilmos} setActorFilmos={setActorFilmos} />
        </div>
      </div>
    </div>
  );
};

export default ActorFilmo;
