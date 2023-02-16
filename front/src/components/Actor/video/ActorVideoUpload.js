/* eslint-disable jsx-a11y/alt-text */
import { useState, useEffect } from 'react';
import { ref, uploadBytes, getDownloadURL, listAll } from 'firebase/storage';
import { useRecoilValue, useRecoilState } from 'recoil';
import axios from 'axios';

import { storage } from '../../../imageup/firebase';
import { v4 } from 'uuid';

// import recoil
import { actorProfile, actorVideoUrl, actorVideoLists } from 'recoil/actor/actorStore';

// import css
import '../../../components/Casting/Casting.css';
import './ActorVideo.css';
import { API_BASE_URL } from '../../../constants';
import jwtApi from '../../../util/JwtApi';

function ActorVideoUpload({ actorInfo }) {
  const [actorVideoUploaded, setActorVideoUploaded] = useState(null);
  const ActorVideoDirectory = useRecoilValue(actorVideoUrl);
  const ActorVideosListRef = ref(storage, ActorVideoDirectory);
  const [actorVideos, setActorVideos] = useRecoilState(actorVideoLists);

  const uploadFile = () => {
    if (actorVideoUploaded === null) {
      return;
    }

    const imageRef = ref(storage, `${ActorVideoDirectory}/${actorVideoUploaded.name + v4()}`);
    uploadBytes(imageRef, actorVideoUploaded).then((snapshot) => {
      getDownloadURL(snapshot.ref).then((url) => {
        const content = {
          originName: `${actorInfo.name}'s video`,
          savedName: `video ${actorInfo.name}`,
          type: 'video',
          url: url,
        };

        jwtApi
          .post(`${API_BASE_URL}/api/actors/${actorInfo.actorProfileId}/media`, content)
          .then((response) => {
            !actorVideos && setActorVideos([]);
            setActorVideos([...actorVideos, { id: response.data.result, url: url }]);
          })
          .catch((err) => {});
      });
    });
  };

  return (
    <div>
      <div className="Video-list">
        <div className="file-box">
          <label htmlFor="file-video">
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
            id="file-video"
            onChange={(e) => {
              setActorVideoUploaded(e.target.files[0]);
            }}
          />
          <button onClick={uploadFile}>동영상 올리기</button>
        </div>
      </div>
    </div>
  );
}

export default ActorVideoUpload;
