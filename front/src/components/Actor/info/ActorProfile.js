import { React, useState } from 'react';

import { actorProfile, actorPhotoUrl, actorPhotoLists, googleToken } from 'recoil/recoilActorState';
import { useRecoilValue, useRecoilState } from 'recoil';
import { storage } from '../../../imageup/firebase';
import { ref, uploadBytes, getDownloadURL, listAll } from 'firebase/storage';
import { v4 } from 'uuid';
import axios from 'axios';

//프로필 사진 등록 component
const ActorProfile = () => {
  const [ActorPhotoUploaded, setActorPhotoUploaded] = useState({});
  const ActorPhotoDirectory = useRecoilValue(actorPhotoUrl);
  const actorInfo = useRecoilValue(actorProfile);
  const ActorPhotosListRef = ref(storage, ActorPhotoDirectory);
  const token = useRecoilValue(googleToken);

  //파일 미리볼 url을 저장해줄 state
  const [fileImage, setFileImage] = useState('');

  // 파일 저장
  const saveFileImage = (e) => {
    setFileImage(URL.createObjectURL(e.target.files[0]));
    setActorPhotoUploaded(e.target.files[0]);
    console.log(e.target.files[0]);
    console.log(fileImage.name, '대표파일이미지 이름');
    if (fileImage === null) return;
    const imageRef = ref(storage, `${ActorPhotoDirectory}/${fileImage.name + v4()}`);
    uploadBytes(imageRef, fileImage).then((snapshot) => {
      getDownloadURL(snapshot.ref).then((url) => {
        // setActPhotoUrl(url);
        const content = {
          originName: `${actorInfo.name}'s picture`,
          savedName: `picture ${actorInfo.name}`,
          type: 'PICTURE',
          url: url,
        };
        console.log('대표프로필 url', url);

        // axios.post(`http://i8a702.p.ssafy.io:8085/api/actors/${actorInfo.actorProfileId}/media`,
        axios
          .post(`http://localhost:8080/api/actors/100001/media`, content, {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          })
          .then((res) => {
            console.log('post success', res);
          })
          .catch((err) => {
            console.log(err);
          });
      });
    });
  };

  // 파일 삭제
  const deleteFileImage = () => {
    URL.revokeObjectURL(fileImage);
    setFileImage('');
  };

  console.log(fileImage, 25151251515125);

  return (
    <>
      <h1>이미지 미리보기</h1>
      <table>
        <tbody>
          <tr>
            <th></th>
            <td>
              <div>
                {fileImage ? (
                  <img alt="sample" src={fileImage} style={{ margin: 'auto' }} />
                ) : (
                  <img
                    alt="sample"
                    src="https://i.ibb.co/cTpZvr4/bb.png"
                    style={{ margin: 'auto' }}
                  />
                )}
                <div
                  style={{
                    alignItems: 'center',
                    justifyContent: 'center',
                  }}
                >
                  <input name="imgUpload" type="file" accept="image/*" onChange={saveFileImage} />

                  <button
                    style={{
                      backgroundColor: 'gray',
                      color: 'white',
                      width: '55px',
                      height: '40px',
                      cursor: 'pointer',
                    }}
                    onClick={() => deleteFileImage()}
                  >
                    삭제
                  </button>
                </div>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </>
  );
};

export default ActorProfile;
