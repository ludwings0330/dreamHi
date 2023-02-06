import React from 'react';
import Button from '../../Common/CommonComponent/Button';

const MakerIntroduce = () => {
  return (
    <>
      <div>

        <div className="mt-10 sm:mt-0">
          <div className="md:grid md:grid-cols-6 md:gap-6">



            <form action="#" method="POST">
              <div className="overflow-hidden shadow sm:rounded-md">

                <div className="bg-white px-5 py-5 sm:p-6">

                  <div className="px-4 sm:px-0">
                    <h1 className="text-lg font-medium leading-6 text-gray-900">기본 정보 입력</h1>
                  </div>
                  {/*공백부분*/}
                  <div className="space-y-6 bg-white py-3">
                  </div>

                  {/*프로필 사진 등록 부분*/}
                  <div>
                    <label className="maker-title">제작사 프로필 사진 등록</label>
                    <div className="mt-1 items-center">

                      <svg className="text-gray-300" fill="currentColor" width="200" viewBox="0 0 24 24">
                        <path d="M24 20.993V24H0v-2.996A14.977 14.977 0 0112.004 15c4.904 0 9.26 2.354 11.996 5.993zM16.002 8.999a4 4 0 11-8 0 4 4 0 018 0z" />
                      </svg>
                    </div>
                  </div>

                  <div>

                    <div className="space-y-1 text-center">

                      <div className="flex text-sm text-gray-600">
                        <label
                          htmlFor="file-upload"
                          className="relative cursor-pointer rounded-md bg-white font-medium text-indigo-600 focus-within:outline-none focus-within:ring-2 focus-within:ring-indigo-500 focus-within:ring-offset-2 hover:text-indigo-500"
                        >
                          <span>Upload a file</span>
                          <input id="file-upload" name="file-upload" type="file" className="sr-only" />
                        </label>
                        <p className="pl-1">or drag and drop</p>
                      </div>
                      <p className="text-xs text-gray-500">PNG, JPG, GIF up to 10MB</p>
                    </div>
                  </div>
                </div>


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

                {/*제작사소개 부분*/}
                <div>
                  <label htmlFor="about" className="block text-xl-center font-medium text-gray-700">
                    제작사 소개
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
                

                {/* 개인정보 입력 후 저장버튼*/}
                <div className="bg-gray-50 px-4 py-5 text-right">
                  <Button title="저장"/>
                </div>

              </div>
            </form>
          </div>
        </div>
      </div>

    </>
  );
};

export default MakerIntroduce;