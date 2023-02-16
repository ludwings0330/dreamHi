import React from 'react';
import { useRecoilValue } from 'recoil';
import { announcementListDetailCastingSelector } from 'recoil/announcement/announcementStore';
import './AnnouncementDetail.css';
import { styled } from '../../../node_modules/@mui/material/styles';
import Box from '../../../node_modules/@mui/material/Box';
import Grid from '../../../node_modules/@mui/material/Grid';
import Chip from '../../../node_modules/@mui/material/Chip';

function AnnouncementDetailCasting(props) {
  const { announcement } = props;
  const CastingData = useRecoilValue(announcementListDetailCastingSelector(announcement.id));

  const data = CastingData;

  return (
    <Grid container spacing={2}>
      {data.map(function (casting, index) {
        return (
          <Grid item xs={12} sm={6} md={3} lg={2} key={casting.id}>
            <div className="announcement-post-container">
              <div> 배역 명 : {casting.name} </div>
              <div> 성별 : {casting.gender} </div>
              <div> 인원 : {casting.headcount} 명 </div>
              <div> 기타 상세 : {casting.description} </div>
              <div className="announcement-post-container-style">
                {' '}
                스타일{' '}
                {casting.styles.map(function (style, index) {
                  return <li key={style.id}>{style.description}</li>;
                })}{' '}
              </div>
            </div>
          </Grid>
        );
      })}
    </Grid>
  );
}

export default AnnouncementDetailCasting;
