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
      <div id={'actor-video-whole'}>
        <h1 className={'actor-video-title'}>연기영상</h1>
        <div className="actor-video-list-main">
          <div className={'actor-video-list'}>
            {/*연기영상 전체 틀*/}
            {actorVideos && actorVideos.length > 0 ? (
              <div className={'actor-video-main'}>
                <video src={actorVideos[0].url} alt={actorVideos[0].url} />
              </div>
            ) : null}
            {actorVideos &&
              actorVideos.length > 0 &&
              actorVideos.map((actorVideo, idx) => (
                <div className="actor-video" key={actorVideo.id}>
                  올린 영상들
                  <video
                    src={actorVideo.url}
                    alt="image"
                    className={'actor-video-list-item'}
                    onClick={() => setSelected(idx)}
                  />
                </div>
              ))}
          </div>

          {userInfo && userInfo.id === actorInfo.userId ? (
            <ActorVideoUpload actorInfo={actorInfo} />
          ) : null}
        </div>
      </div>
    </>
  );
};

export default ActorVideo;
