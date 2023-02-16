import React from 'react';

import './MainView.css';
import MainMiddle from './MainMiddle';
import MainBanner from './MainBanner';

function MainView() {
  return (
    <>
      <div id={'mainview-whole'}>
        <div className={'mainview-header'}>
          <div className="mainview-title">
            <h1>Dream Hi</h1>
            <p>당신의 꿈을 응원합니다</p>
            <button>Write Your Filmo</button>
          </div>
        </div>
      </div>
      <MainMiddle />
      <MainBanner />
    </>
  );
}

export default MainView;
