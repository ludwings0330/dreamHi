import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';

import './Announcement.css';

import { useRecoilValue } from 'recoil';
import {
  announcementListSelector,
  announcementListState,
  announcementFilterState,
} from 'recoil/announcement/announcementStore';
import AnnouncementSearchBar from './AnnouncementSearchBar';
import { SearchAnnouncement } from './AnnouncementAxios';

// import Css
import { styled } from '../../../node_modules/@mui/material/styles';
import Box from '../../../node_modules/@mui/material/Box';
import Grid from '../../../node_modules/@mui/material/Grid';
import Chip from '../../../node_modules/@mui/material/Chip';
import { useState } from 'react';
import { useRecoilState } from 'recoil';

// import { styled } from '../../../node_modules/@mui/material/styles'

function AnnouncementList(props) {
  // const { announcements, onClickItem } = props;
  console.log('😍😎😍');
  // let list = [];
  // const [list, setList] = useState([]);

  // const [announcementList, setAnnouncementList] = useState(list);
  // const searchList = useRecoilValue(announcementListState);
  // setAnnouncementList(searchList);
  // setAnnouncementList(useRecoilValue(announcementListState));
  const [announcementList, setAnnouncementList] = useRecoilState(announcementListState);

  // const announcementList = useRecoilValue(announcementListState);

  // const [announcementList, setAnnouncementList] = useState();
  const sendData = useRecoilValue(announcementFilterState);

  useEffect(() => {
    SearchAnnouncement(
      sendData,
      (response) => {
        console.log('announcement Search', response);
        console.log('😎😋', response.data.result.content);
        // list = response.data.result.content;
        setAnnouncementList(() => response.data.result.content);
      },
      () => {},
    );
  }, [sendData]);

  // console.log(list);
  // setAnnouncementList(useRecoilValue(announcementListState));

  console.log('😁👻👻👻', announcementList);

  // useEffect(() => {
  //   SearchAnnouncement
  // }, [])

  return (
    <div>
      <h2> 공고리스트페이지 </h2>

      <AnnouncementSearchBar />

      {announcementList.length > 0 &&
        announcementList.map(function (announcement) {
          console.log(announcement);
          return (
            <Link
              to={`/announcement/${announcement.id}`}
              key={announcement.id}
              style={{ textDecoration: 'none' }}
            >
              <Grid id="announcement-list">
                <Grid container spacing={2}>
                  <Grid item xs={2}>
                    <Chip
                      label={announcement.state.processState == 'RECRUITING' ? '모집중' : '마감'}
                      variant="outlined"
                      color="primary"
                    />
                  </Grid>
                  <Grid item xs={8} className="title-text">
                    {' '}
                    {announcement.title} - {announcement.producerName}{' '}
                  </Grid>
                  <Grid item xs={2}>
                    {announcement.createdDate.substring(0, 10)}
                  </Grid>
                  <Grid item xs={2}>
                    {announcement.isFollow === true ? '💙' : '🖤'}
                  </Grid>
                  <Grid item xs={8} className="announcement-list-castings casting-text">
                    {announcement.castings.map(function (cast, index) {
                      return <span key={cast.id}> {cast.name}역</span>;
                    })}
                  </Grid>
                  <Grid item xs={2}>
                    조회수 : {announcement.hit}
                  </Grid>
                </Grid>
              </Grid>
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
