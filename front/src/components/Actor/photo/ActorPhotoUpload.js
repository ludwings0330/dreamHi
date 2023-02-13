/* eslint-disable jsx-a11y/alt-text */
import { useState, useEffect } from 'react';
import { ref, uploadBytes, getDownloadURL, listAll } from 'firebase/storage';
import { useRecoilValue, useRecoilState } from 'recoil';
import axios from 'axios';

import { storage } from '../../../imageup/firebase';
import { v4 } from 'uuid';

// import recoil
import { actorProfile, actorPhotoUrl, actorPhotoLists, googleToken } from 'recoil/actor/actorStore';

// import css
import '../../../components/Casting/Casting.css';
import './ActorPhoto.css';
import {API_BASE_URL} from "../../../constants";

function ActorPhotoUpload(props) {
  console.log('test', props);
  //setActorPhotos
  console.log(props.actorPhotos);
  const [ActorPhotoUploaded, setActorPhotoUploaded] = useState(null);
  const ActorPhotoDirectory = useRecoilValue(actorPhotoUrl);
  const actorInfo = useRecoilValue(actorProfile);

  const ActorPhotosListRef = ref(storage, ActorPhotoDirectory);

  const token = useRecoilValue(googleToken);

  const uploadFile = () => {
    if (ActorPhotoUploaded === null) return;
    const imageRef = ref(storage, `${ActorPhotoDirectory}/${ActorPhotoUploaded.name + v4()}`);
    uploadBytes(imageRef, ActorPhotoUploaded).then((snapshot) => {
      getDownloadURL(snapshot.ref).then((url) => {
        // setActPhotoUrl(url);
        const content = {
          originName: `${actorInfo.name}'s picture`,
          savedName: `picture ${actorInfo.name}`,
          type: 'PICTURE',
          url: url,
        };
        console.log('파베', url);
        props.setActorPhotos(props.actorPhotos.concat({ id: props.actorPhotos.length, url: url }));
        // axios.post(`http://i8a702.p.ssafy.io:8085/api/actors/${actorInfo.actorProfileId}/media`,
        axios
          .post(`${API_BASE_URL}/api/actors/100001/media`, content, {
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
              setActorPhotoUploaded(e.target.files[0]);
            }}
          />
          <button onClick={uploadFile}>사진 올리기</button>
        </div>
      </div>
    </div>
  );
}

export default ActorPhotoUpload;
