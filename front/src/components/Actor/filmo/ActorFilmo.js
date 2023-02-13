import React from 'react';
import './ActorFilmo.css';
import ActorFilmoUpload from './ActorFilmoUpload';
import { actorFilmoLists } from 'recoil/actor/actorStore';
import { useRecoilState } from 'recoil';

const ActorFilmo = () => {
  const [actorFilmos, setActorFilmos] = useRecoilState(actorFilmoLists);

  const setSelected = (idx) => {
    document.querySelector(
      '.actor-filmo-main',
    ).innerHTML = `<img src=${actorFilmos[idx].url} alt=${actorFilmos[idx]}/>`;
  };

  if (actorFilmos.length === 0) {
    return null;
  }
  console.log('필모그래피 정보들');
  console.log(actorFilmos);
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
            actorFilmos.map((filmography) => (
              <div className="actor-filmo" key={filmography.id} width={'200px'} height={'200px'}>
                <img
                  src={filmography.photoUrl}
                  alt="image"
                  className="object-center"
                  loading={'lazy'}
                  onClick={() => setSelected(filmography.id)}
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
