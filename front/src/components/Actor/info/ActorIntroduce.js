import React, { useRef, useState } from 'react';
import Button from '../../Common/CommonComponent/Button';


const ActorIntroduce = () => {
//파일 미리볼 url을 저장해줄 state
  const [fileImage, setFileImage] = useState("");

  // 파일 저장
  const saveFileImage = (e) => {
    setFileImage(URL.createObjectURL(e.target.files[0]));
    console.log(e.target.files[0])
  };

  // 파일 삭제
  const deleteFileImage = () => {
    URL.revokeObjectURL(fileImage);
    setFileImage("");
  };

  console.log(fileImage,25151251515125)
  return (
      <>
                  {/*배우 정보 입력 최상단 tag*/}
                  <div className="actor-info">
                <h1>정보 입력</h1>

                    {/*공백부분*/}
                    <div className="space-y-6 bg-white py-3">
                    </div>

                    {/*프로필 사진 등록 부분*/}
                    <table>
                      <h2>프로필사진등록</h2>
                      <tbody>
                      <tr>
                        <th></th>
                        <td>
                          <div>
                            { fileImage ?
                                <img
                                    alt="sample"
                                    src={fileImage}
                                    style={{ margin: "auto" }}
                                />
                                :
                                <img
                                    alt="sample"
                                    src="https://i.ibb.co/cTpZvr4/bb.png"
                                    style={{ margin: "auto" }}
                                />
                            }
                            <div
                                style={{
                                  alignItems: "center",
                                  justifyContent: "center",
                                }}
                            >
                              <input
                                  name="imgUpload"
                                  type="file"
                                  accept="image/*"
                                  onChange={saveFileImage}
                              />

                              <button
                                  style={{
                                    backgroundColor: "gray",
                                    color: "white",
                                    width: "55px",
                                    height: "40px",
                                    cursor: "pointer",
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
                    <label htmlFor="about" className="block text-xl-center font-medium text-gray-700">
                      한줄소개
                    </label>
                    {/*라벨과의 사이 간격 거리*/}
                    <div className="mt-1">

                                <textarea
                                    id="about"
                                    name="about"
                                    rows={3}
                                    cols={100}
                                    className="info-title"
                                />
                    </div>
                  </div>

                  {/* 개인 정보 입력 부분*/}
                  <div className="col-span-6 sm:col-span-3">
                    <label htmlFor="first-name" className="block text-sm font-medium text-gray-700">
                      이름 :
                    </label>
                    <input
                        type="text"
                        name="first-name"
                        id="first-name"
                        autoComplete="given-name"
                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>

                  {/*성별 checkbox 부분*/}
                  <div className={"gender"}>
                    <label htmlFor="inputName">성별 :</label>
                    여성
                    <input
                        type={"checkbox"}
                        name={"gender"}
                    />

                    남성
                    <input
                        type={"checkbox"}
                        name={"gender"}
                    />
                  </div>

                  <div className="col-span-6 sm:col-span-4">
                    <label htmlFor="email-address" className="block text-sm font-medium text-gray-700">
                      나이 :
                    </label>
                    <input
                        type="text"
                        name="email-address"
                        id="email-address"
                        autoComplete="email"
                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>

                  <div className="col-span-6">
                    <label htmlFor="street-address" className="block text-sm font-medium text-gray-700">
                      키 :
                    </label>
                    <input
                        type="text"
                        name="street-address"
                        id="street-address"
                        autoComplete="street-address"
                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>


                  <div className="col-span-6">
                    <label htmlFor="street-address" className="block text-sm font-medium text-gray-700">
                      체형 :
                    </label>
                    <input
                        type="text"
                        name="street-address"
                        id="street-address"
                        autoComplete="street-address"
                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>

                  <div className="col-span-6 sm:col-span-6 lg:col-span-2">
                    <label htmlFor="city" className="block text-sm font-medium text-gray-700">
                      전화번호 :
                    </label>
                    <input
                        type="text"
                        name="city"
                        id="city"
                        autoComplete="address-level2"
                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>

                  <div className="col-span-6 sm:col-span-3 lg:col-span-2">
                    <label htmlFor="region" className="block text-sm font-medium text-gray-700">
                      이메일 :
                    </label>
                    <input
                        type="text"
                        name="region"
                        id="region"
                        autoComplete="address-level1"
                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>

                  {/* 개인정보 입력 후 저장버튼*/}
                  <div className="bg-gray-50 px-4 py-5 text-right">
                    <Button title="저장"/>
                  </div>
                    
                  </div>

      </>

  );
};

export default ActorIntroduce;