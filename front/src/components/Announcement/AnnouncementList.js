import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';

import './Announcement.css';


import SearchBar from '../Common/CommonComponent/SearchBar';

import { useRecoilValue } from 'recoil';
import { announcementListSelector, announcementListState } from 'recoil/announcement/announcementStore';


function AnnouncementList(props) {
  // const { announcements, onClickItem } = props;
  console.log('😍😎😍')

  const announcementList = useRecoilValue(announcementListSelector())


  useEffect(() => {
    console.log('😁👻👻👻', announcementList)
  }, [])


  // useEffect()

  return (
    <div className="announcement-post-container">
      <h2> 공고리스트페이지 </h2>



      {announcementList.length > 0 && announcementList.map(function (announcement) {
        return (
          <Link to={`/announcement/${announcement.id}`} key={announcement.id} >
            <div>
              <div className="announcement-list-item-wrapper">
                <p>
                  {' '}
                  {announcement.title} - {announcement.producerName}{' '}
                </p>
                <div className="announcement-list-castings">
                  {announcement.castings.map(function (cast, index) {
                    return (
                      <span>
                        {cast.name}
                      </span>
                    );
                  })}
                </div>
                <p>{announcement.createDate}</p>
                <p>{announcement.hit}</p>
                <div>{announcement.isFollow === true ? '💙' : '🖤'}</div>

                <div>{announcement.state.processState === 'RECRUITING' ? '⭕' : '❌'}</div>
              </div>
            </div>


          </Link>

        );
      })}





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

      */}
    </div>
  );
}

export default AnnouncementList;
