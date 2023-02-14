import React from 'react';
import { useRecoilState } from 'recoil';

//import img upload
import MakerFilmoUpload from './MakerFilmoUpload';
import { makerFilmoLists } from 'recoil/maker/makerStore';

const MakerFilmo = ({ makerInfo }) => {
  const [makerFilmos, setMakerFilmos] = useRecoilState(makerFilmoLists);

  const setSelected = (idx) => {
    document.querySelector(
      '.maker-filmo-main',
    ).innerHTML = `<img src=${makerFilmos[idx].url} alt=${makerFilmos[idx]}/>`;
  };

  return (
    <>
      <div className="bg-white">
        <div className="mx-auto max-w-2xl py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
          <h1>제작사 필모그래피</h1>

          {makerFilmos && makerFilmos.length > 0 ? (
            <div className="list-container">
              {/*메인이미지 부분*/}
              <div className="maker-filmo-main">
                <img src={makerFilmos[0].photoUrl} alt={makerFilmos[0].photoUrl} />
              </div>

              {makerFilmos.map((makerFilmo, idx) => (
                <div className="photo" key={idx} width={'200px'} height={'200px'}>
                  <img
                    src={makerFilmo.photoUrl}
                    alt="image"
                    className="object-center"
                    onClick={() => setSelected(idx)}
                  />
                  <p>
                    필모 이름 : {makerFilmo.title}
                    필모 설명 : {makerFilmo.description}
                  </p>
                </div>
              ))}
            </div>
          ) : null}

          <MakerFilmoUpload makerInfo={makerInfo} />
        </div>
      </div>
    </>
  );
};

export default MakerFilmo;
