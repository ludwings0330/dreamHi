/* eslint-disable jsx-a11y/alt-text */
import { useState, useEffect } from 'react';
import { ref, uploadBytes, getDownloadURL, listAll } from 'firebase/storage';
import { useRecoilValue, useRecoilState } from 'recoil';
import axios from 'axios';

import { storage } from '../../../imageup/firebase';
import { v4 } from 'uuid';

// import recoil
import { makerProfile, makerFilmoUrl, makerFilmoLists } from 'recoil/maker/makerStore';

// import css
import '../../../components/Casting/Casting.css';
import './MakerFilmo.css';
import {API_BASE_URL} from "../../../constants";

function MakerFilmoUpload(props) {
  console.log('test', props);
  //setActorPhotos
  console.log(props.makerFilmos);
  const [MakerFilmoUploaded, setMakerFilmoUploaded] = useState(null);
  const MakerFilmoDirectory = useRecoilValue(makerFilmoUrl);
  const makerInfo = useRecoilValue(makerProfile);

  const MakerFilmosListRef = ref(storage, MakerFilmoDirectory);

  const token = localStorage.getItem('accessToken');

  const uploadFile = () => {
    if (MakerFilmoUploaded === null) return;
    const imageRef = ref(storage, `${MakerFilmoDirectory}/${MakerFilmoUploaded.name + v4()}`);
    uploadBytes(imageRef, MakerFilmoUploaded).then((snapshot) => {
      getDownloadURL(snapshot.ref).then((url) => {
        // setActPhotoUrl(url);
        const content = {
          originName: `${makerInfo.name}'s picture`,
          savedName: `picture ${makerInfo.name}`,
          type: 'PICTURE',
          url: url,
        };
        console.log('파베', url);
        props.setMakerFilmos(props.makerFilmos.concat({ id: props.makerFilmos.length, url: url }));
        // axios.post(`http://i8a702.p.ssafy.io:8085/api/actors/${actorInfo.actorProfileId}/media`,
        axios
          .post(`${API_BASE_URL}/api/producers/100001/media`, content, {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          })
          .then((res) => {
            console.log('post success', res);
          })
          .catch((err) => {
            console.log(err);
          });
      });
    });
  };

  return (
    <div>
      <div className="photo-list">
        <div className="file-box">
          <label for="file-photo">
            <img
              src="/img/plus.png"
              width={'200px'}
              height={'200px'}
              object-fit={'cover'}
              className="object-center"
            />
          </label>
          <input
            type="file"
            id="file-photo"
            onChange={(e) => {
              setMakerFilmoUploaded(e.target.files[0]);
            }}
          />
          <button onClick={uploadFile}>사진 올리기</button>
        </div>
      </div>
    </div>
  );
}

export default MakerFilmoUpload;
