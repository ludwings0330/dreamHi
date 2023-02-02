import React from 'react';
import SearchBar from '../Common/SearchBar';
import PageBar from '../Common/PageBar';
import {} from './MakerList.css'

const MakerList = () => {
      return (
      <div>
        <SearchBar />
        <div className=" maker_container">
          <div className="maker">
            <div className="maker_img"><img src="/img/elephant.png" className="actor_img"/></div>
            <h5 className="maker_title"> 제작사 소개</h5>
            <p className="maker_des"> 제작사 소개 요약</p>
          </div>
        </div>
        <div className={"page_bar"}>
          <PageBar />
        </div>
      </div>
      );
};

export default MakerList;