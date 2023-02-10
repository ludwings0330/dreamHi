import React, {useEffect} from 'react';
import AnnouncementListItem from './AnnouncementListItem';
import SearchBar from '../Common/CommonComponent/SearchBar';
import PageBar from '../Common/CommonComponent/PageBar';

import { useRecoilValue } from 'recoil';
import { announcementListSelector } from 'recoil/announcement/announcementStore';


function AnnouncementList(props) {
  // const { announcements, onClickItem } = props;

  const announcementList = useRecoilValue(announcementListSelector())

  useEffect(() =>{
    console.log('😁👻👻👻',announcementList)
  }, [])


  // useEffect()

  return (
    <div className="announcement-post-container">
      <h2> 공고리스트페이지 </h2>


      {/* <SearchBar />

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

      <PageBar /> */}
    </div>
  );
}

export default AnnouncementList;
