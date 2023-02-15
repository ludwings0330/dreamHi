import React from 'react';

import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import BasicNotice from 'components/Calendar/notice/BasicNotice';
import { useRecoilValue } from 'recoil';
import ActorTimeTable from 'components/Calendar/ActorTimeTable';
import { announcementListDetailState } from 'recoil/announcement/announcementStore';
import DateCalendar from 'components/Calendar/DateCalendar';
import Attachment from 'components/Calendar/notice/Attachment';
import { IconButton, Tooltip, Box, Container, Stack, Divider, Grid } from '@mui/material';
import EventAvailableIcon from '@mui/icons-material/EventAvailable';
function ActorAuditionPage() {
  const announcementDetail = useRecoilValue(announcementListDetailState);

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
            <ActorTimeTable />
          </Box>
        </Grid>
        <Divider orientation="vertical" flexItem variant="inset" sx={{ bgcolor: 'black' }} />
        <Grid item xs={5}>
          <Container>
            <BasicNotice />
            <Stack direction="row" alignItems="center" justifyContent="flex-end" mt={3} ml={2}>
              <Attachment />
              <Tooltip title="예약" placement="bottom">
                <IconButton color="primary" aria-label="upload file">
                  <EventAvailableIcon fontSize="large" />
                </IconButton>
              </Tooltip>
            </Stack>
          </Container>
        </Grid>
      </Grid>
    </Container>
  );
}

export default ActorAuditionPage;
