import React, { useRef, useState } from 'react';
import Button from '../Common/Button';
import ImageUpload from '../../imageup/ImageUpload';

function AnnouncementWriteImage() {
  const [image, setImage] = useState('');
  const imgRef = useRef();
  const previewImage = () => {
    const file = imgRef.current.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = () => {
      setImage(reader.result);
    };
  };
  
  function clickImageInput() {
    const formData = new FormData()
    formData.append('image', image)
  }
  return (
    <div>
      <h4>공고 사진 업로드</h4>
      <img 
      src={image ? image : `/logo192.png`}
      alt = "공고 이미지"
      />
      <input type='file'
             accept='image/jpg, image/png, image/jpeg, image/gif'
             id='announcementImg' onChange={previewImage} ref={imgRef}/>
      <Button
      title = "이미지 변경"
      onClick = {clickImageInput}
      />

      <ImageUpload />
    </div>
  )
}

export default AnnouncementWriteImage;