import { useState, useEffect } from 'react';
import { ref, uploadBytes, getDownloadURL, listAll } from 'firebase/storage';
import { useRecoilValue, useRecoilState } from 'recoil'
import axios from 'axios'

import { storage } from '../../../imageup/firebase';
import { v4 } from 'uuid';

// import recoil
import { actorProfile, actorPhotoUrl, actorPhotoLists, googleToken } from 'recoil/recoilActorState'

// import css
import '../../../components/Casting/Casting.css';
import './ActorPhoto.css';

function ActorPhotoUpload(props) {
  console.log('test')
  const [ActorPhotoUploaded, setActorPhotoUploaded] = useState(null);
  // const [actPhotoUrl, setActPhotoUrl] = useState('');
  // const [actorPhotos, setActorPhotos] = useRecoilState(actorPhotoLists);
  const ActorPhotoDirectory = useRecoilValue(actorPhotoUrl)
  const actorInfo = useRecoilValue(actorProfile)

  const ActorPhotosListRef = ref(storage, ActorPhotoDirectory);

  const token = useRecoilValue(googleToken)

  const uploadFile = () => {
    if (ActorPhotoUploaded === null) return;
    const imageRef = ref(storage, `${ActorPhotoDirectory}/${ActorPhotoUploaded.name + v4()}`)
    uploadBytes(imageRef, ActorPhotoUploaded).then((snapshot) => {
      getDownloadURL(snapshot.ref)
        .then((url) => {
          // setActPhotoUrl(url);
        const content = {
          originName: `${actorInfo.name}'s picture`,
          savedName: `picture ${actorInfo.name}`,
          type: "PICTURE",
          url: url
        }
        // axios.post(`http://i8a702.p.ssafy.io:8085/api/actors/${actorInfo.actorProfileId}/media`,
          axios.post(`http://i8a702.p.ssafy.io:8085/api/actors/100001/media`,
          content,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            }
          })
          .then((res) => {
            console.log('post success')
          })
          .catch((err) => {
            console.log(err)
          })
      });
    });
  };

  // useEffect(() => {
  //   axios.get(`http://i8a702.p.ssafy.io:8085/api/actors/100001/media`)
  //     .then((res) => {
  //       setActorPhotos(res.data.result.pictures)
  //       console.log(res.data.result.pictures)
  //     })
  //     .catch((err) => {
  //       console.log(err)
  //     })
  // }, [setActorPhotos]);


  // useEffect(() => {
  //   listAll(ActorPhotosListRef).then((response) => {
  //     // console.log(response.items[response.items.length - 1])
  //     response.items.forEach((item) => {
  //       getDownloadURL(item).then((url) => {
  //         setActPhotoUrl((prev) => [...prev, url]);
  //         // setActPhotoUrl((prev) => [url]);
  //       });
  //     });
  //   });
  // }, []);

  return (
    <div>
      <div className="photo-list">
        {/*{actPhotoUrl.map((url, idx) => (*/}
        {/*    <div className='photo' key={idx}*/}
        {/*         width={"200px"}*/}
        {/*         height={"200px"}>*/}
        {/*      <img*/}
        {/*        src={url}*/}
        {/*        alt="test"*/}
        {/*        object-fit={"scale-down"}*/}
        {/*        className="object-center"*/}
        {/*        onClick={uploadFile}*/}
        {/*      />*/}
        {/*    </div>*/}
        {/*  )*/}
        {/*)}*/}
        {/*{actorPhotos.length > 0 && actorPhotos.map((actorPhoto, idx) => (*/}
        {/*  <div className='photo'*/}
        {/*       key={idx}*/}
        {/*       width={"200px"}*/}
        {/*       height={"200px"}>*/}
        {/*    <img src={actorPhoto.url}*/}
        {/*         alt='image'*/}
        {/*         object-fit={"contain"}*/}
        {/*         className="object-center"*/}
        {/*    />*/}
        {/*  </div>*/}
        {/*))}*/}
        <div className="file-box">
          <label for='file'>
            <img
              src="https://www.w3schools.com/howto/img_avatar2.png"
              width={"200px"}
              height={"200px"}
              object-fit={"cover"}
              className="object-center"
            />
          </label>
          <input
            type="file"
            id="file"
            onChange={(e) => {
            setActorPhotoUploaded(e.target.files[0])}
            }
          />
          <button
            onClick={uploadFile}
          >
            사진 올리기
          </button>
        </div>
      </div>
    </div>
  );
}

export default ActorPhotoUpload;
