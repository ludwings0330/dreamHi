import React from 'react';
import AnnouncementListItem from './AnnouncementListItem';
import SearchBar from '../Common/CommonComponent/SearchBar';

function AnnouncementList(props) {
  const { announcements, onClickItem } = props;

  return (
    <div className="announcement-post-container">
      <h2> 공고리스트페이지 </h2>
      <SearchBar />

      {announcements.map(function (announcement, index) {
        return (
          <AnnouncementListItem
            key={announcements[0].result.list[0].id}
            announcement={announcement}
            onClick={() => {
              onClickItem(announcement);
            }}
          />
        );
      })}

    </div>
  );
}

export default AnnouncementList;
