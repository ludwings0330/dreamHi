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
import { userSimpleState } from '../../../recoil/user/userStore';

const ActorFilmoUpload = ({ actorInfo }) => {
  const [actorFilmos, setActorFilmos] = useRecoilState(actorFilmoLists);
  const [ActorFilmoUploaded, setActorFilmoUploaded] = useState(null);
  const ActorFilmoDirectory = useRecoilValue(actorFilmoUrl);

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
          actorId: actorInfo.actorProfileId,
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
      <div id="actor-list-plus-section">
        <div className="file-box">
          <label htmlFor="file-filmo">
            <img src="/img/plus.png" object-fit={'cover'} className={"filmo-plus-button"} />
          </label>

          <input
            type="file"
            id="file-filmo"
            onChange={(e) => setActorFilmoUploaded(e.target.files[0])}
          />
          <button 
              className={'actor-filmo-upload-button'}
              onClick={uploadFile}>사진 올리기</button>
        </div>
      </div>
    </>
  );
};

export default ActorFilmoUpload;
