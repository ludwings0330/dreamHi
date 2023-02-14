import React from 'react';

import { Box, Container } from '../../node_modules/@mui/material/index';
import Divider from '@mui/material/Divider';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import Grid from '@mui/material/Grid';
import BasicNotice from 'components/Calendar/notice/BasicNotice';
import { useRecoilValue } from 'recoil';
import MakerTimeTable from 'components/Calendar/MakerTimeTable';
import { announcementListDetailState } from 'recoil/announcement/announcementStore';
import { useEffect } from 'react';
import DateCalendar from 'components/Calendar/DateCalendar';

function MakerAuditionPage() {
  const announcementDetail = useRecoilValue(announcementListDetailState);

  useEffect(() => {}, []);

  return (
    <Container
      justify="center"
      sx={{
        mb: 5,
      }}
    >
      <Box
        align="center"
        sx={{
          typography: 'h3',
          mb: 8,
          fontWeight: 'bold',
        }}
      >
        🎬 {announcementDetail.title} 오디션
      </Box>
      <Grid container>
        <Grid item xs={6}>
          <Box>
            <Divider variant="fullWidth" sx={{ m: 'auto', bgcolor: 'black' }} />
            <CalendarMonthIcon sx={{ mt: 2, mb: 2, color: '#45b6fe' }} /> 날짜 선택
            <Divider variant="fullWidth" sx={{ m: 'auto', bgcolor: 'black' }} />
            <DateCalendar />
          </Box>
          <Divider variant="fullWidth" />
          <Box sx={{ mt: 5 }}>
            <Divider variant="fullWidth" sx={{ bgcolor: 'black' }} />
            <AccessTimeIcon sx={{ mt: 2, mb: 2, color: '#45b6fe' }} /> 시간 선택
            <Divider variant="fullWidth" sx={{ bgcolor: 'black' }} />
            <MakerTimeTable />
          </Box>
        </Grid>
        <Divider orientation="vertical" flexItem variant="inset" sx={{ bgcolor: 'black' }} />
        <Grid item xs={5}>
          <Container>
            <BasicNotice />
          </Container>
        </Grid>
      </Grid>
    </Container>
  );
}

export default MakerAuditionPage;
