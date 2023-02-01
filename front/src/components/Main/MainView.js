import React from 'react';

import "./MainView.css"
import MainBanner from './MainBanner';

function MainView() {
  return (

    <div className="main-body">
      <div className="main-img">
        <MainBanner />
      </div>
      <div className="main-content">
        <div className="main-best">
          <h2>인기 공고 TOP 10</h2>
          <div>공고 리스트가 들어옵니다</div>
        </div>
        <div className="main-news">
          <h2>최신 NEWS</h2>
          <div>최신 뉴스가 들어옵니다</div>
        </div>
      </div>
    </div>
  );
};

export default MainView;