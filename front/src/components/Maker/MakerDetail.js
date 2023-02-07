import React from 'react';
import MakerIntroduce from './Info/MakerIntroduce';
import MakerFilmo from './Filmo/MakerFilmo';
import MakerPeopleList from './MakerPeopleList/MakerPeopleList';
import MakerAnnouncementList from './AnnouncementList/MakerAnnouncementList';

const MakerDetail = () => {
  return (
    <>
      <MakerIntroduce />
      <MakerFilmo />
      <MakerPeopleList />
      <MakerAnnouncementList />
    </>
  );
};

export default MakerDetail;