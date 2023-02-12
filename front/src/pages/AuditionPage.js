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
import TimeTable from 'components/Calendar/TimeTable';
import Grid from '@mui/material/Grid';

function AuditionPage() {

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
            <TimeTable />
          </Box>
        </Grid>
        <Divider orientation="vertical" flexItem variant="inset" sx={{ bgcolor: "black"}} />
        <Grid item xs={3} sx={{
          mt: 5
      }}>
          <Container>
            saljkdnasldnkas
          </Container>
        </Grid>
      </Grid>
    </Container>
  );
}

export default AuditionPage;