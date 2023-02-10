/* eslint-disable jsx-a11y/alt-text */
import { useState, useEffect } from 'react';
import { ref, uploadBytes, getDownloadURL, listAll } from 'firebase/storage';
import { useRecoilValue, useRecoilState } from 'recoil';
import axios from 'axios';

import { storage } from '../../../imageup/firebase';
import { v4 } from 'uuid';

// import recoil
import { actorProfile, actorVideoUrl, actorVideoLists, googleToken } from 'recoil/recoilActorState';

// import css
import '../../../components/Casting/Casting.css';
import './ActorVideo.css';

function ActorVideoUpload(props) {
  const [ActorVideoUploaded, setActorVideoUploaded] = useState(null);
  // const [actVideoUrl, setActVideoUrl] = useState('');
  // const [actorVideos, setActorVideos] = useRecoilState(actorVideoLists);
  const ActorVideoDirectory = useRecoilValue(actorVideoUrl);
  const actorInfo = useRecoilValue(actorProfile);

  const ActorVideosListRef = ref(storage, ActorVideoDirectory);

  const token = useRecoilValue(googleToken);

  const uploadFile = () => {
    console.log(ActorVideoUploaded, '개답해 대코');
    console.log('뭐해?');
    if (ActorVideoUploaded === null) return;
    console.log('일안해?');
    const imageRef = ref(storage, `${ActorVideoDirectory}/${ActorVideoUploaded.name + v4()}`);
    uploadBytes(imageRef, ActorVideoUploaded).then((snapshot) => {
      getDownloadURL(snapshot.ref).then((url) => {
        console.log('업로드가 잘 되고 잇나요', url);
        // setActVideoUrl(url);
        const content = {
          originName: `${actorInfo.name}'s video`,
          savedName: `video ${actorInfo.name}`,
          type: 'video',
          url: url,
        };
        // axios.post(`http://i8a702.p.ssafy.io:8085/api/actors/${actorInfo.actorProfileId}/media`,
        axios
          .post(`http://i8a702.p.ssafy.io:8085/api/actors/100001/media`, content, {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          })
          .then((res) => {
            console.log('post success');
          })
          .catch((err) => {
            console.log(err);
          });
      });
    });
  };

  // useEffect(() => {
  //   axios.get(`http://i8a702.p.ssafy.io:8085/api/actors/100001/media`)
  //     .then((res) => {
  //       setActorVideos(res.data.result.videos)
  //       console.log(res.data.result.videos)
  //     })
  //     .catch((err) => {
  //       console.log(err)
  //     })
  // }, [setActorVideos]);

  // useEffect(() => {
  //   listAll(ActorVideosListRef).then((response) => {
  //     // console.log(response.items[response.items.length - 1])
  //     response.items.forEach((item) => {
  //       getDownloadURL(item).then((url) => {
  //         setActVideoUrl((prev) => [...prev, url]);
  //         // setActVideoUrl((prev) => [url]);
  //       });
  //     });
  //   });
  // }, []);

  useEffect(() => {
    console.log(ActorVideoUploaded, '제발 잘나와줘');
  }, [ActorVideoUploaded]);

  return (
    <div>
      <div className="Video-list">
        {/*{actVideoUrl.map((url, idx) => (*/}
        {/*    <div className='Video' key={idx}*/}
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
        {/*{actorVideos.length > 0 && actorVideos.map((actorVideo, idx) => (*/}
        {/*  <div className='Video'*/}
        {/*       key={idx}*/}
        {/*       width={"200px"}*/}
        {/*       height={"200px"}>*/}
        {/*    <img src={actorVideo.url}*/}
        {/*         alt='image'*/}
        {/*         object-fit={"contain"}*/}
        {/*         className="object-center"*/}
        {/*    />*/}
        {/*  </div>*/}
        {/*))}*/}
        <div className="file-box">
          <label for="file-video">
            <img
              src="https://www.w3schools.com/howto/img_avatar2.png"
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
          <button onClick={uploadFile}>사진 올리기</button>
        </div>
      </div>
    </div>
  );
}

export default ActorVideoUpload;
