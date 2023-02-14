import React from 'react';
import './ActorFilmo.css';
import ActorFilmoUpload from './ActorFilmoUpload';
import { actorFilmoLists } from 'recoil/actor/actorStore';
import { useRecoilState } from 'recoil';

const ActorFilmo = ({ actorId }) => {
  const [actorFilmos, setActorFilmos] = useRecoilState(actorFilmoLists);

  const setSelected = (idx) => {
    document.querySelector(
      '.actor-filmo-main',
    ).innerHTML = `<img src=${actorFilmos[idx].photoUrl} alt=${actorFilmos[idx]}/>`;
  };

  return (
    <div className="bg-white">
      <div className="mx-auto max-w-2xl py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
        <h1 className={'actor-filmo-title'}>필모그래피</h1>

        <div className={'actor-filmo-list'}>
          {/*메인이미지 부분*/}
          {actorFilmos && actorFilmos.length > 0 ? (
            <div className="actor-filmo-main">
              <img src={actorFilmos[0].photoUrl} alt={actorFilmos[0].photoUrl} />
            </div>
          ) : null}
          {actorFilmos &&
            actorFilmos.length > 0 &&
            actorFilmos.map((filmography, idx) => (
              <div className="actor-filmo" key={filmography.id}>
                <img
                  src={filmography.photoUrl}
                  alt="image"
                  className="object-center"
                  loading={'lazy'}
                  onClick={() => setSelected(idx)}
                />
              </div>
            ))}

          <ActorFilmoUpload actorId={actorId} />
        </div>
      </div>
    </div>
  );
};

export default ActorFilmo;
