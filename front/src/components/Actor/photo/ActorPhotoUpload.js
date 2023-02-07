import { useState, useEffect } from 'react';
import { ref, uploadBytes, getDownloadURL, listAll } from 'firebase/storage';

import { storage } from '../../../imageup/firebase';
import { v4 } from 'uuid';

import '../../../components/Casting/Casting.css';
import './ActorPhoto.css';

function ActorImageUpload(props) {
  const [ActorImageUpload, setActorImageUpload] = useState(null);
  const [ActorImageUrls, setActorImageUrls] = useState([]);
  const ActorImageDirectory = 'images/actorId/photo'

  const ActorImagesListRef = ref(storage, ActorImageDirectory);
  const uploadFile = () => {
    if (ActorImageUpload == null) return;
    const imageRef = ref(storage, `${ActorImageDirectory}/${ActorImageUpload.name + v4()}`);
    uploadBytes(imageRef, ActorImageUpload).then((snapshot) => {
      getDownloadURL(snapshot.ref).then((url) => {
        setActorImageUrls((prev) => [...prev, url]);
        // setActorImageUrls((prev) => [url]);
        console.log(setActorImageUrls)
        console.log(ActorImageUrls)
      });
    });
  };

  // const onChange = event => {
  //   const { value } = event.target;
  //   setActorImageUrls(value)
  //   props.onChange(ActorImageUrls)
  // }

  // useEffect(() => {
  //   listAll(ActorImagesListRef).then((response) => {
  //     // console.log(3434343434);
  //     // console.log(response.items);
  //     // console.log(response.items[response.items.length - 1]);
  //     response.items.forEach((item) => {
  //       getDownloadURL(item).then((url) => {
  //         // console.log(3333333333333333333);
  //         // console.log(url);
  //         setActorImageUrls((prev) => [...prev, url]);
  //         // setActorImageUrls((prev) => [url]);
  //       });
  //     });
  //   });
  // }, []);

  // useEffect(() => {
  //   listAll(ActorImagesListRef).then((response) => {
  //     console.log(3434343434);
  //     console.log(response.items[response.items.length - 1]);
  //
  //     getDownloadURL(response.items[response.items.length - 1]).then((url) => {
  //       console.log(3333333333333333333);
  //       console.log(url);
  //       // setActorImageUrls((prev) => [...prev, url]);
  //       setActorImageUrls((prev) => [url]);
  //     });
  //   });
  // }, []);

  // console.log(1414141141);
  // console.log(ActorImageUrls);
  // console.log(ActorImageUrls[ActorImageUrls.length - 1])

  useEffect(() => {
    listAll(ActorImagesListRef).then((response) => {
      console.log(3434343434)
      console.log(response.items[response.items.length - 1])
      response.items.forEach((item) => {
        getDownloadURL(item).then((url) => {
          console.log(3333333333333333333)
          console.log(url)
          setActorImageUrls((prev) => [...prev, url]);
          // setActorImageUrls((prev) => [url]);


        });
      });
    });
  }, []);

  return (
    <div>
      {/*<input*/}
      {/*  type="file"*/}
      {/*  onChange={(event) => {*/}
      {/*    setActorImageUpload(event.target.files[0]);*/}
      {/*  }}*/}
      {/*/>*/}
      <button
        onClick={uploadFile}
        // onChange={() => {
        //   props.onChange(ActorImageUrls[ActorImageUrls.length - 1]);
        // }}
      >
        사진 올리기
      </button>
      <div className="photo-list">
        {ActorImageUrls.map((url, idx) => (
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
          <input
            type="file"
            id="file"
            onChange={(event) => {
              setActorImageUpload(event.target.files[0]);
            }}
          />
        </div>

        {/*<img src={ActorImageUrls} />*/}
      </div>
    </div>
  );
}

export default ActorImageUpload;
