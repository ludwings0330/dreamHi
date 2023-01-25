import React from 'react';
import "./Actor.css";
import PageBar from '../Common/PageBar';

const ActorList = () => {
    return (
      <div>
      <div className="actor_container">
        <div className="actor">
          <div className="actor_img"><img src="/img/elephant.png" className="actor_img"/></div>
          <h5 className="actor_title"> 배우 소개</h5>
          <p className="actor_des"> 배우 소개 요약</p>
        </div>
        <div className="actor">
          <div className="actor_img"><img src="/img/elephant.png" className="actor_img"/></div>
          <h5 className="actor_title"> 배우 소개</h5>
          <p className="actor_des"> 배우 소개 요약</p>
        </div>
        <div className="actor">
          <div className="actor_img"><img src="/img/elephant.png" className="actor_img"/></div>
          <h5 className="actor_title"> 배우 소개</h5>
          <p className="actor_des"> 배우 소개 요약</p>
        </div>
        <div className="actor">
          <div className="actor_img"><img src="/img/elephant.png" className="actor_img"/></div>
          <h5 className="actor_title"> 배우 소개</h5>
          <p className="actor_des"> 배우 소개 요약</p>
        </div>
        <div className="actor">
          <div className="actor_img"><img src="/img/elephant.png" className="actor_img"/></div>
          <h5 className="actor_title"> 배우 소개</h5>
          <p className="actor_des"> 배우 소개 요약</p>
        </div>
        <div className="actor">
          <div className="actor_img"><img src="/img/elephant.png" className="actor_img"/></div>
          <h5 className="actor_title"> 배우 소개</h5>
          <p className="actor_des"> 배우 소개 요약</p>
        </div>
      </div>
        <div className={"page_bar"}>
        <PageBar />
        </div>
      </div>
    );
};

export default ActorList;