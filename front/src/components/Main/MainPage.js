import React from 'react';

import "./Main.css"

function MainPage() {
  return (
    <div className="main-body">
      <div className="main-img">
        <img src="img/test.png" className="main-img" />
      </div>
      <div className="main-calendar">
        <img src="img/test.png" className="main-img" />
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

export default MainPage;