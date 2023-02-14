import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';

import './Announcement.css';

import { useRecoilValue } from 'recoil';
import {
  announcementListSelector,
  announcementListState,
} from 'recoil/announcement/announcementStore';
import AnnouncementSearchBar from './AnnouncementSearchBar';

// import Css
import { styled } from '../../../node_modules/@mui/material/styles';
import Box from '../../../node_modules/@mui/material/Box';
import Grid from '../../../node_modules/@mui/material/Grid';
import Chip from '../../../node_modules/@mui/material/Chip';

// import { styled } from '../../../node_modules/@mui/material/styles'

function AnnouncementList(props) {
  // const { announcements, onClickItem } = props;
  console.log('😍😎😍');

  const announcementList = useRecoilValue(announcementListSelector());

  useEffect(() => {
    console.log('😁👻👻👻', announcementList);
    console.log(
      announcementList.find((item) => {
        return item.id == 50002;
      }),
    );
  }, []);

  return (
    <div>
      <h2> 공고리스트페이지 </h2>

      <AnnouncementSearchBar />

      {announcementList.length > 0 &&
        announcementList.map(function (announcement) {
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
                      label={announcement.state.processState === 'RECRUITING' ? '모집중' : '마감'}
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
                      return <span>{cast.name}역</span>;
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
