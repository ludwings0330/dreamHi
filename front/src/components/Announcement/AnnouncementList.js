import React from 'react';
import AnnouncementListItem from './AnnouncementListItem';

function AnnouncementList(props) {
  const { announcements, onClickItem } = props;

  return (
    <div>
      <h2> 공고리스트페이지 </h2>
      {announcements.map(function(announcement, index){
        // console.log(announcements[0].result.list[0].id)
        console.log(123455555555555555)
        console.log(announcement.result.list[0].id)
        console.log(123455555555555555)
        return (

          <AnnouncementListItem
            key = {announcements[0].result.list[0].id}
            announcement={announcement}
            onClick={() => {
              onClickItem(announcement);
            }}
          />
        )
        }

      )
      }
    </div>
  );
};

export default AnnouncementList;