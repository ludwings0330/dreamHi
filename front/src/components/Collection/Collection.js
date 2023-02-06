import React from 'react';
import FavoriteAnnouncement from './FavoriteAnnouncement';
import RegistAnnouncement from './RegistAnnouncement';
import FavoriteActor from './FavoriteActor';
import FavoriteMaker from './FavoriteMaker';

const Collection = () => {
  return (
    <>
    <h1>모아보기</h1>
      <FavoriteAnnouncement />
      <RegistAnnouncement />
      <FavoriteActor />
      <FavoriteMaker />
    </>
  );
};

export default Collection;