import { useState, useEffect } from 'react';
import { ref, uploadBytes, getDownloadURL, listAll } from 'firebase/storage';
import { useRecoilValue, useRecoilState } from 'recoil';
import axios from 'axios';

import { storage } from '../../../imageup/firebase';
import { v4 } from 'uuid';

// import recoil
import { actorProfile, actorFilmoUrl, actorFilmoLists } from 'recoil/actor/actorStore';

// import css
import '../../../components/Casting/Casting.css';
import './ActorFilmo.css';
import { API_BASE_URL } from '../../../constants';
import jwtApi from '../../../util/JwtApi';

const ActorFilmoUpload = ({ actorId }) => {
  const [actorFilmos, setActorFilmos] = useRecoilState(actorFilmoLists);
  const [ActorFilmoUploaded, setActorFilmoUploaded] = useState(null);
  const ActorFilmoDirectory = useRecoilValue(actorFilmoUrl);
  const actorInfo = useRecoilValue(actorProfile);

  const uploadFile = () => {
    if (ActorFilmoUploaded === null) {
      return;
    }
    const imageRef = ref(storage, `${ActorFilmoDirectory}/${ActorFilmoUploaded.name + v4()}`);

    uploadBytes(imageRef, ActorFilmoUploaded).then((snapshot) => {
      getDownloadURL(snapshot.ref).then((url) => {
        // setActPhotoUrl(url);
        const content = {
          originName: `${actorInfo.name}'s picture`,
          savedName: `picture ${actorInfo.name}`,
          type: 'PICTURE',
          photoUrl: url,
          title: 'title-filmography',
          description: 'description-filmography',
          actorId: actorId,
        };

        jwtApi
          .post(`${API_BASE_URL}/api/filmographies`, { ...content })
          .then((response) => {
            !actorFilmos && setActorFilmos([]);

            setActorFilmos([
              ...actorFilmos,
              { id: response.data.result, photoUrl: content.photoUrl },
            ]);
          })
          .catch((e) => {
            console.log(e);
          });
      });

      setActorFilmoUploaded(null);
    });
  };

  return (
    <>
      <div className="actor-list">
        <div className="file-box">
          <label htmlFor="file-filmo">
            <img src="/img/plus.png" object-fit={'cover'} className="object-center" />
          </label>

          <input
            type="file"
            id="file-filmo"
            onChange={(e) => setActorFilmoUploaded(e.target.files[0])}
          />
          <button onClick={uploadFile}>사진 올리기</button>
        </div>
      </div>
    </>
  );
};

export default ActorFilmoUpload;
