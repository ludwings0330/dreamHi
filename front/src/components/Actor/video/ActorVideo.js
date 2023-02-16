import React, { useEffect } from 'react';
import './ActorVideo.css';
import ActorVideoUpload from './ActorVideoUpload';
import { actorVideoLists } from 'recoil/actor/actorStore';
import { useRecoilValue, useRecoilState } from 'recoil';
import { userSimpleState } from '../../../recoil/user/userStore';

const ActorVideo = ({ actorInfo }) => {
  const [actorVideos, setActorVideos] = useRecoilState(actorVideoLists);
  const [userInfo, setUserInfo] = useRecoilState(userSimpleState);

  const setSelected = (idx) => {
    document.querySelector(
      '.actor-video-main',
    ).innerHTML = `<video src=${actorVideos[idx].url} alt=${actorVideos[idx]} />`;
  };

  return (
    <>
      <div className="bg-white">
        <h1>연기영상</h1>

        {/*연기영상 전체 틀*/}
        {actorVideos && actorVideos.length > 0 ? (
          <div className="actor-video-whole">
            <div className="actor-video-main">
              선택 메인 영상
              <video src={actorVideos[0].url} alt={actorVideos[0].url} />
            </div>
            {actorVideos.map((actorVideo, idx) => (
              <div className="actor-video-list" key={actorVideo.id}>
                올린 영상들
                <video src={actorVideo.url} alt="image" onClick={() => setSelected(idx)} />
              </div>
            ))}
          </div>
        ) : null}
        {userInfo.id === actorInfo.userId ? <ActorVideoUpload actorInfo={actorInfo} /> : null}
      </div>
    </>
  );
};

export default ActorVideo;
