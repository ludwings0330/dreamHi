import { useState, useEffect } from 'react';
import { ref, uploadBytes, getDownloadURL, listAll } from 'firebase/storage';
import { useRecoilValue } from 'recoil'

import { storage } from '../../../imageup/firebase';
import { v4 } from 'uuid';

// import recoil
import { actorProfile } from '../recoilActorState'

// import css
import '../../../components/Casting/Casting.css';
import './ActorPhoto.css';

function ActorPhotoUpload(props) {
  const [ActorPhotoUpload, setActorPhotoUpload] = useState(null);
  const [ActorPhotoUrls, setActorPhotoUrls] = useState([]);
  const actorInfo = useRecoilValue(actorProfile)
  const ActorPhotoDirectory = `images/${actorInfo.name}_${actorInfo.actorProfileId}/photo`

  const ActorPhotosListRef = ref(storage, ActorPhotoDirectory);
  const uploadFile = (e) => {
    setActorPhotoUpload(e.target.files[0])
    // if (ActorPhotoUpload == null) return;
    const imageRef = ref(storage, `${ActorPhotoDirectory}/${ActorPhotoUpload.name + v4()}`);
    uploadBytes(imageRef, ActorPhotoUpload).then((snapshot) => {
      getDownloadURL(snapshot.ref).then((url) => {
        setActorPhotoUrls((prev) => [...prev, url]);
        // setActorPhotoUrls((prev) => [url]);
        console.log(setActorPhotoUrls)
        console.log(ActorPhotoUrls)
      });
    });
  };

  // const onChange = event => {
  //   const { value } = event.target;
  //   setActorPhotoUrls(value)
  //   props.onChange(ActorPhotoUrls)
  // }

  // useEffect(() => {
  //   listAll(ActorPhotosListRef).then((response) => {
  //     // console.log(3434343434);
  //     // console.log(response.items);
  //     // console.log(response.items[response.items.length - 1]);
  //     response.items.forEach((item) => {
  //       getDownloadURL(item).then((url) => {
  //         // console.log(3333333333333333333);
  //         // console.log(url);
  //         setActorPhotoUrls((prev) => [...prev, url]);
  //         // setActorPhotoUrls((prev) => [url]);
  //       });
  //     });
  //   });
  // }, []);

  // useEffect(() => {
  //   listAll(ActorPhotosListRef).then((response) => {
  //     console.log(3434343434);
  //     console.log(response.items[response.items.length - 1]);
  //
  //     getDownloadURL(response.items[response.items.length - 1]).then((url) => {
  //       console.log(3333333333333333333);
  //       console.log(url);
  //       // setActorPhotoUrls((prev) => [...prev, url]);
  //       setActorPhotoUrls((prev) => [url]);
  //     });
  //   });
  // }, []);

  // console.log(1414141141);
  // console.log(ActorPhotoUrls);
  // console.log(ActorPhotoUrls[ActorPhotoUrls.length - 1])

  useEffect(() => {
    listAll(ActorPhotosListRef).then((response) => {
      // console.log(response.items[response.items.length - 1])
      response.items.forEach((item) => {
        getDownloadURL(item).then((url) => {
          setActorPhotoUrls((prev) => [...prev, url]);
          // setActorPhotoUrls((prev) => [url]);


        });
      });
    });
  }, []);

  return (
    <div>
      {/*<input*/}
      {/*  type="file"*/}
      {/*  onChange={(event) => {*/}
      {/*    setActorPhotoUpload(event.target.files[0]);*/}
      {/*  }}*/}
      {/*/>*/}
      <button
        onClick={uploadFile}
        // onChange={() => {
        //   props.onChange(ActorPhotoUrls[ActorPhotoUrls.length - 1]);
        // }}
      >
        사진 올리기
      </button>
      <div className="photo-list">
        {ActorPhotoUrls.map((url, idx) => (
            <div className='photo' key={idx}
                 width={"200px"}
                 height={"200px"}>
              <img
                src={url}
                alt="test"
                object-fit={"scale-down"}
                className="object-center"
                onClick={uploadFile}
              />
            </div>
          )
        )}
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
          {/*<input*/}
          {/*  type="file"*/}
          {/*  id="file"*/}
          {/*  onChange={(event) => {*/}
          {/*    setActorPhotoUpload(event.target.files[0]);*/}
          {/*  }}*/}
          {/*/>*/}
          <input
            type="file"
            id="file"
            onChange={uploadFile}
          />
        </div>

        {/*<img src={ActorPhotoUrls} />*/}
      </div>
    </div>
  );
}

export default ActorPhotoUpload;
