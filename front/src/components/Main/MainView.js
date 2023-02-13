import React from 'react';

import './MainView.css';
import MainBanner from './MainBanner';
import BestAnnouncement from './BestAnnouncement';

function MainView() {
  return (
    <div className="main-body">
      <div className="main-img">
        <MainBanner />
      </div>
      <div>
        <BestAnnouncement />
      </div>
    </div>
  );
}

export default MainView;
