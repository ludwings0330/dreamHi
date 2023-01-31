import React from 'react';
import AnnouncementListItem from './AnnouncementListItem';

function AnnouncementList(props) {
  const { announcements } = props;

  return (
    <div>
      <h2> 공고리스트페이지 </h2>
      {announcements.map((announcement, index) => {
        return (
          <AnnouncementListItem
            key = {announcement[0].result.list[0].id}
            announcement={announcement}
            // onClick={() => {
            //   onClickItem(announcement);
            // }}
          />
        )
        }

      )
      }
    </div>
  );
};

export default AnnouncementList;