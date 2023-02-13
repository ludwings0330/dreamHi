import React, { useEffect } from 'react';
import { useRecoilState, useRecoilValue } from 'recoil';
import axios from 'axios';

//import img upload
import MakerFilmoUpload from './MakerFilmoUpload';
import {
  makerProfileId,
  makerFilmoUrl,
  makerProfile,
  makerFilmoLists,
} from 'recoil/maker/makerStore';

import {} from './MakerFilmo.css';
import {API_BASE_URL} from "../../../constants";

const MakerFilmo = () => {
  const [makerFilmos, setMakerFilmos] = useRecoilState(makerFilmoLists);

  const setSelected = (idx) => {
    document.querySelector(
      '.maker-filmo-main',
    ).innerHTML = `<img src=${makerFilmos[idx].url} alt=${makerFilmos[idx]}/>`;
  };

  useEffect(() => {
    axios
      .get(`${API_BASE_URL}/api/producers/100001/media`)
      .then((res) => {
        setMakerFilmos(res.data.result.pictures);
        console.log(res.data.result.pictures, '잘 찍히나요?');
      })
      .catch((err) => {
        console.log(err);
      });
  }, [setMakerFilmos]);

  return (
    <div className="bg-white">
      <div className="mx-auto max-w-2xl py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
        <h1>필모그래피</h1>

        <div className="list-container">
          {/*메인이미지 부분*/}
          <div className="maker-filmo-main">
            <img src={makerFilmos[0].url} alt={makerFilmos[0].url} />
          </div>

          {makerFilmos.length > 0 &&
            makerFilmos.map((makerFilmo, idx) => (
              <div className="photo" key={idx} width={'200px'} height={'200px'}>
                <img
                  src={makerFilmo.url}
                  alt="image"
                  className="object-center"
                  onClick={() => setSelected(idx)}
                />
              </div>
            ))}

          <MakerFilmoUpload makerFilmos={makerFilmos} setMakerFilmos={setMakerFilmos} />
        </div>
      </div>
    </div>
  );
};

export default MakerFilmo;
