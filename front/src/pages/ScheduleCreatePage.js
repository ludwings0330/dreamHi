import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';

import Button from '../components/Common/CommonComponent/Button';
import CalendarAnnounce from '../components/Calendar/calendarAnnounce';

import Notice from '../components/Audition/Notice';
import styled, { css } from 'styled-components'
import DateCalendar from 'components/Calendar/DateCalendar';
import { Box, Container } from '../../node_modules/@mui/material/index';
import Divider from '@mui/material/Divider';
import Grid from '@mui/material/Grid';
import MakerDateCalendar from 'components/Calendar/MakerDateCalendar';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import TimeRangeSelect from 'components/Calendar/TimeRangeSelect';
import NoticeUpload from "components/Calendar/NoticeUpload";

function ScheduleCreatePage() {

  const navigate = useNavigate();
  const totalVolunteer = useState(50);
  return (
    <Container
    justify="center"
    sx={{
      mb:5
    }}
    >

      <Box align="center" sx={{
        typography: "h3",
        fontWeight: "bold"
      }}>🎬 오디션 일정 선택
      </Box>
      <Box align="center" sx={{
        typography: "h6",
        mb: 8
      }}>총원 {totalVolunteer} 명</Box>
      <Grid container >
        <Grid item xs={8} >
          <Box>
          <Divider variant="fullWidth" sx={{ m: "auto",  bgcolor: "black"}} />
            <CalendarMonthIcon sx={{mt:2, mb:2, color: "#45b6fe"}} /> 날짜 선택
          <Divider variant="fullWidth" sx={{ m:"auto",  bgcolor: "black"}} />
            <MakerDateCalendar />
          </Box>
          <Divider variant="fullWidth" />
          <Box sx={{mt: 5}}>
          <Divider variant="fullWidth" sx={{ bgcolor: "black"}} />
            <AccessTimeIcon sx={{mt:2, mb:2, color: "#45b6fe"}} /> 시간 선택
          <Divider variant="fullWidth" sx={{ bgcolor: "black"}} />
            <TimeRangeSelect />
          </Box>
        </Grid>
        <Divider orientation="vertical" flexItem variant="inset" sx={{ bgcolor: "black"}} />
        <Grid item xs={3} >
          <NoticeUpload />
        </Grid>
      </Grid>
    </Container>
  );
}

export default ScheduleCreatePage;