import React, { useEffect, useState } from 'react';
import { getDownloadURL, listAll, ref, uploadBytes } from 'firebase/storage';

import { storage } from './firebase';
import { v4 } from 'uuid';

import '../components/Casting/Casting.css';
import { useRecoilState, atom } from 'recoil';
import '../recoil/announcement';
import { announcementPictureUrl } from '../recoil/announcement';
import ImageUpload from "imageup/ImageUpload";
import AttachFileIcon from '@mui/icons-material/AttachFile';
import IconButton from '@mui/material/IconButton';

function FileUpload() {
    const[file, setFile] = useState();
    const[fileUrl, setFileUrl] = useState();
  const uploadFile = () => {
    if (file == null) return;
    const fileRef = ref(storage, `audition/${file.name + v4()}`);
    uploadBytes(fileRef, file).then((snapshot) => {
      getDownloadURL(snapshot.ref).then((url) => {
        // setImageUrls((prev) => [...prev, url]);
        setFileUrl((prev) => (url));
      });
    });
  };

  return (
    <>
    <IconButton color="primary" aria-label="upload file" >
        <input type="file" onChange={(e) => setFile(e.target.files[0])} />
    </IconButton>
    <AttachFileIcon />
      {/* <input
        type="file"
        onChange={(event) => {
          setFile(event.target.files[0]);
        }}
      />
      <button
        onClick={uploadFile}
      >
        Upload Image
      </button> */}
    </>
  );
}

export default FileUpload;
