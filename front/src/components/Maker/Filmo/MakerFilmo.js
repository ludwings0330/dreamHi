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
    ).innerHTML = `<img src=${makerFilmos[idx].photoUrl} alt=${makerFilmos[idx]}/>`;
  };

  return (
    <>
      <div id={'maker-filmo-whole'}>
        <h1>제작사 필모그래피</h1>
        <div className={'maker-filmo-list-main'}>
          {makerFilmos && makerFilmos.length > 0 ? (
            <div className="maker-filmo-list-container">
              {/*메인이미지 부분*/}
              <div className="maker-filmo-main">
                <img src={makerFilmos[0].photoUrl} alt={makerFilmos[0].photoUrl} />
                <p>필모 이름 : {makerFilmos[0].title}</p>
                <p>필모 설명 : {makerFilmos[0].description}</p>
              </div>

              {makerFilmos.map((makerFilmo, idx) => (
                <div className="maker-filmo-list" key={idx}>
                  <img
                    src={makerFilmo.photoUrl}
                    alt="image"
                    className="maker-filmo-list-item"
                    onClick={() => setSelected(idx)}
                  />
                  <p>필모 이름 : {makerFilmo.title}</p>
                  <p>필모 설명 : {makerFilmo.description}</p>
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
