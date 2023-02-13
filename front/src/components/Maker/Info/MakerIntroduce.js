import React, { useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import MakerIntro from './MakerIntro';
import Button from '../../Common/CommonComponent/Button';

const MakerIntroduce = () => {
  const navigate = useNavigate();

  //제작사 한줄 소개
  const titleInputRef = useRef();
  //한줄 소개 관련
  const [title, setTitle] = useState('');
  const handleChangeTitle = (e) => {
    setTitle(e.target.value);
  };

  //파일 미리볼 url을 저장해줄 state
  const [fileImage, setFileImage] = useState('');

  // 파일 저장
  const saveFileImage = (e) => {
    setFileImage(URL.createObjectURL(e.target.files[0]));
    console.log(e.target.files[0]);
  };

  // 파일 삭제
  const deleteFileImage = () => {
    URL.revokeObjectURL(fileImage);
    setFileImage('');
  };

  return (
    <>
      <div className={'maker-info'}>
        {/*공백부분*/}
        <div className="space-y-6 bg-white py-3"></div>

        {/*프로필 사진 등록 부분*/}
        <table>
          <h2>프로필사진등록</h2>
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

        {/*한줄소개 부분*/}
        <div>
          <label>
            한줄소개
            <input
              type={'text'}
              value={title}
              required
              ref={titleInputRef}
              onChange={handleChangeTitle}
            />
          </label>
        </div>

        {/*제작사소개 부분*/}
        {/*컴포넌트로 추가*/}
        <MakerIntro />
      </div>
    </>
  );
};

export default MakerIntroduce;
