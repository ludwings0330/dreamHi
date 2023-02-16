/* eslint-disable jsx-a11y/alt-text */
import { useState, useEffect } from 'react';
import { ref, uploadBytes, getDownloadURL, listAll } from 'firebase/storage';
import { useRecoilValue, useRecoilState } from 'recoil';
import axios from 'axios';

import { storage } from '../../../imageup/firebase';
import { v4 } from 'uuid';

// import recoil
import { actorProfile, actorPhotoUrl, actorPhotoLists } from 'recoil/actor/actorStore';

// import css
import '../../../components/Casting/Casting.css';
import './ActorPhoto.css';
import { API_BASE_URL } from '../../../constants';
import jwtApi from '../../../util/JwtApi';

function ActorPhotoUpload({ actorInfo }) {
  const [actorPhotoUploaded, setActorPhotoUploaded] = useState(null);
  const [actorPhotos, setActorPhotos] = useRecoilState(actorPhotoLists);
  const ActorPhotoDirectory = useRecoilValue(actorPhotoUrl);
  const ActorPhotosListRef = ref(storage, ActorPhotoDirectory);

  const uploadFile = () => {
    if (actorPhotoUploaded === null) {
      return;
    }

    const imageRef = ref(storage, `${ActorPhotoDirectory}/${actorPhotoUploaded.name + v4()}`);

    uploadBytes(imageRef, actorPhotoUploaded).then((snapshot) => {
      getDownloadURL(snapshot.ref).then((url) => {
        // setActPhotoUrl(url);
        const content = {
          originName: `${actorInfo.name}'s picture`,
          savedName: `picture ${actorInfo.name}`,
          type: 'PICTURE',
          url: url,
        };
        console.log('배우 사진 업로드 완료', url);
        jwtApi
          .post(`${API_BASE_URL}/api/actors/${actorInfo.actorProfileId}/media`, content)
          .then((response) => {
            !actorPhotos && setActorPhotos([]);

            setActorPhotos([...actorPhotos, { id: response.data.result, url: url }]);

            console.log('post success', response);
          })
          .catch((err) => {
            console.log(err);
          });
      });
    });
  };

  return (
    <>
      <div id="photo-list-plus-section">
        <div className="file-box">
          <label htmlFor="file-photo">
            <img src="/img/plus.png" object-fit={'cover'} className={'photo-plus-button'} />
          </label>
          <input
            type="file"
            id="file-photo"
            onChange={(e) => setActorPhotoUploaded(e.target.files[0])}
          />
          <button className={'actor-photo-upload-button'} onClick={uploadFile}>
            사진 올리기
          </button>
        </div>
      </div>
    </>
  );
}

export default ActorPhotoUpload;
