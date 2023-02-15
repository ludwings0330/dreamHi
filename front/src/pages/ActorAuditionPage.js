import React from 'react';
import dayjs from 'dayjs';
import { Box, Container } from '../../node_modules/@mui/material/index';
import Divider from '@mui/material/Divider';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import Grid from '@mui/material/Grid';
import BasicNotice from 'components/Calendar/notice/BasicNotice';
import { useRecoilValue } from 'recoil';
import ActorTimeTable from 'components/Calendar/ActorTimeTable';
import { announcementListDetailState } from 'recoil/announcement/announcementStore';
import DateCalendar from 'components/Calendar/DateCalendar';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import { isBookedSelector } from '../recoil/book/bookStore';

function ActorAuditionPage() {
  const announcementDetail = useRecoilValue(announcementListDetailState);
  const isBooked = useRecoilValue(isBookedSelector());

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
            {isBooked.reserved ? (
              <Paper
                elevation={8}
                sx={{
                  ml: 3,
                  borderRadius: 3,
                }}
              >
                <Box
                  sx={{
                    color: '#41424C',
                  }}
                >
                  <Typography variant="h6" align="center" mt={2}>
                    오디션 응시일 : {dayjs(isBooked.startDateTime).format('YYYY-MM-DD')}
                    <br />
                    시간 : {dayjs(isBooked.startDateTime).format('HH:mm')} ~{' '}
                    {dayjs(isBooked.endDateTime).format('HH:mm')}
                  </Typography>
                  <Divider variant="fullWidth" mb={2} sx={{ bgcolor: 'black' }} />
                  <Typography variant="subtitle1" align="center" mb={2}>
                    오디션 응시 시간에 맞춰 5분 이내에 입장해주세요.
                  </Typography>
                </Box>
              </Paper>
            ) : (
              <ActorTimeTable />
            )}
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

export default ActorAuditionPage;
