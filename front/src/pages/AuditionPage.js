import React from 'react';
import { useNavigate } from 'react-router-dom';

import Button from '../components/Common/CommonComponent/Button';
import CalendarAnnounce from '../components/Calendar/calendarAnnounce';

import Notice from '../components/Audition/Notice';
import styled, { css } from 'styled-components'
import DateCalendar from 'components/Calendar/DateCalendar';
import { Box, Container } from '../../node_modules/@mui/material/index';
import Divider from '@mui/material/Divider';
import MakerDateCalendar from 'components/Calendar/DateCalendar';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import Grid from '@mui/material/Grid';
import BasicNotice from 'components/Calendar/BasicNotice';
import {useRecoilValue} from "recoil";
import { userTypeState } from 'recoil/user/userStore';
import ActorTimeTable from 'components/Calendar/ActorTimeTable';
import MakerTimeTable from 'components/Calendar/MakerTimeTable';

function AuditionPage() {

  const userType = useRecoilValue(userTypeState); 

  const navigate = useNavigate();
  return (
    <Container
    justify="center"
    sx={{
      mb:5
    }}
    >

      <Box align="center" sx={{
        typography: "h3",
        mb: 8,
        fontWeight: "bold"
      }}>🎬 오디션 일정 선택
      </Box>
      <Grid container >
        <Grid item xs={6} >
          <Box>
          <Divider variant="fullWidth" sx={{ m: "auto",  bgcolor: "black"}} />
            <CalendarMonthIcon sx={{mt:2, mb:2, color: "#45b6fe"}} /> 날짜 선택
          <Divider variant="fullWidth" sx={{ m:"auto",  bgcolor: "black"}} />
            <DateCalendar />
          </Box>
          <Divider variant="fullWidth" />
          <Box sx={{mt: 5}}>
          <Divider variant="fullWidth" sx={{ bgcolor: "black"}} />
            <AccessTimeIcon sx={{mt:2, mb:2, color: "#45b6fe"}} /> 시간 선택
          <Divider variant="fullWidth" sx={{ bgcolor: "black"}} />
          {
            userType === "producer".toUpperCase() ? 
            <MakerTimeTable /> :  <ActorTimeTable / >
          }
          </Box>
        </Grid>
        <Divider orientation="vertical" flexItem variant="inset" sx={{ bgcolor: "black"}} />
        <Grid item xs={5}>
          <Container>
            <BasicNotice/>
          </Container>
        </Grid>
      </Grid>
    </Container>
  );
}

export default AuditionPage;