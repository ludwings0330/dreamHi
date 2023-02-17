/* eslint-disable jsx-a11y/alt-text */
import { useState } from 'react';
import { getDownloadURL, ref, uploadBytes } from 'firebase/storage';
import { useRecoilState, useRecoilValue } from 'recoil';

import { storage } from '../../../imageup/firebase';
import { v4 } from 'uuid';

// import recoil
import { makerFilmoLists, makerFilmoUrl } from 'recoil/maker/makerStore';

// import css
import '../../../components/Casting/Casting.css';
import './MakerFilmo.css';
import jwtApi from '../../../util/JwtApi';
import { useParams } from 'react-router-dom';

const MakerFilmoUpload = ({ makerInfo }) => {
  const { makerId } = useParams();
  const [MakerFilmoUploaded, setMakerFilmoUploaded] = useState(null);
  const MakerFilmoDirectory = useRecoilValue(makerFilmoUrl);
  const [makerFilmos, setMakerFilmos] = useRecoilState(makerFilmoLists);

  const uploadFile = () => {
    if (MakerFilmoUploaded === null) {
      return;
    }

    const imageRef = ref(storage, `${MakerFilmoDirectory}/${MakerFilmoUploaded.name + v4()}`);
    uploadBytes(imageRef, MakerFilmoUploaded).then((snapshot) => {
      getDownloadURL(snapshot.ref).then((url) => {
        // setActPhotoUrl(url);
        const content = {
          title: '제작사 필모그래피',
          description: '제작사 필모 설명',
          originName: `${makerInfo.name}'s picture`,
          savedName: `picture ${makerInfo.name}`,
          producerId: makerId,
          photoUrl: url,
        };
        console.log('파베', url);

        jwtApi
          .post(`/api/filmographies`, content)
          .then((response) => {
            setMakerFilmos([...makerFilmos, { id: response.data.result, photoUrl: url }]);
          })
          .catch((e) => console.log(e));
      });
    });
  };

  return (
    <>
      <div id={'maker-list-plus-section'}>
        <div className="file-box">
          <label htmlFor="file-filmo">
            <img src="/img/plus.png" object-fit={'cover'} className="maker-filmo-plus-button" />
          </label>
          <input
            type="file"
            id="file-filmo"
            onChange={(e) => {
              setMakerFilmoUploaded(e.target.files[0]);
            }}
          />
          <button className={'maker-filmo-upload-button'} onClick={uploadFile}>
            사진 올리기
          </button>
        </div>
      </div>
    </>
  );
};

export default MakerFilmoUpload;
