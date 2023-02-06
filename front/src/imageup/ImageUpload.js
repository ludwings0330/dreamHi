import { useState, useEffect } from 'react';
import { ref, uploadBytes, getDownloadURL, listAll } from 'firebase/storage';

import { storage } from './firebase';
import { v4 } from 'uuid';

import '../components/Casting/Casting.css';

function ImageUpload(props) {
  const [imageUpload, setImageUpload] = useState(null);
  const [imageUrls, setImageUrls] = useState();

  const imagesListRef = ref(storage, 'images/');
  const uploadFile = () => {
    if (imageUpload == null) return;
    const imageRef = ref(storage, `images/${imageUpload.name + v4()}`);
    uploadBytes(imageRef, imageUpload).then((snapshot) => {
      getDownloadURL(snapshot.ref).then((url) => {
        // setImageUrls((prev) => [...prev, url]);
        setImageUrls((prev) => [url]);
        console.log(setImageUrls)
        console.log(imageUrls)
      });
    });
  };

  // const onChange = event => {
  //   const { value } = event.target;
  //   setImageUrls(value)
  //   props.onChange(imageUrls)
  // }

  // useEffect(() => {
  //   listAll(imagesListRef).then((response) => {
  //     // console.log(3434343434);
  //     // console.log(response.items);
  //     // console.log(response.items[response.items.length - 1]);
  //     response.items.forEach((item) => {
  //       getDownloadURL(item).then((url) => {
  //         // console.log(3333333333333333333);
  //         // console.log(url);
  //         setImageUrls((prev) => [...prev, url]);
  //         // setImageUrls((prev) => [url]);
  //       });
  //     });
  //   });
  // }, []);

  useEffect(() => {
    listAll(imagesListRef).then((response) => {
      console.log(3434343434);
      console.log(response.items[response.items.length - 1]);

      getDownloadURL(response.items[response.items.length - 1]).then((url) => {
        console.log(3333333333333333333);
        console.log(url);
        // setImageUrls((prev) => [...prev, url]);
        setImageUrls((prev) => [url]);
      });
    });
  }, []);

  // console.log(1414141141);
  // console.log(imageUrls);
  // console.log(imageUrls[imageUrls.length - 1])

  // useEffect(() => {
  //   listAll(imagesListRef).then((response) => {
  //     console.log(3434343434)
  //     console.log(response.items.orderBy())
  //     console.log(response.items[response.items.length - 1])
  //     response.items.forEach((item) => {
  //       getDownloadURL(item).then((url) => {
  //         console.log(3333333333333333333)
  //         console.log(url)
  //         setImageUrls((prev) => [...prev, url]);
  //         // setImageUrls((prev) => [url]);
  //
  //
  //       });
  //     });
  //   });
  // }, []);

  return (
    <div>
      <input
        type="file"
        onChange={(event) => {
          setImageUpload(event.target.files[0]);
        }}
      />
      <button
        onClick={uploadFile}
        // onChange={() => {
        //   props.onChange(imageUrls[imageUrls.length - 1]);
        // }}
      >
        Upload Image
      </button>
      <div>
        {/*{imageUrls.map((url) => {*/}
        {/*  return <img src={url} />;*/}
        {/*})}*/}

        <img src={imageUrls} />
      </div>
    </div>
  );
}

export default ImageUpload;
