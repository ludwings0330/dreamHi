import React, { useState } from 'react';
import dayjs from 'dayjs';
import TextField from '@mui/material/TextField';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { StaticDatePicker } from '@mui/x-date-pickers/StaticDatePicker';
import Grid from '@mui/material/Grid';
import { Paper } from '../../../node_modules/@mui/material/index';
import { useRecoilValue, useRecoilState } from 'recoil';
import { userTypeState } from 'recoil/user/userStore';
import { auditionStartState, auditionEndState } from 'recoil/book/bookStore';

export default function MakerDateCalendar() {
  const [startDate, setStartDate] = useRecoilState(auditionStartState);
  const [endDate, setEndDate] = useRecoilState(auditionEndState);
  const userType = useRecoilValue(userTypeState);

  return (
    <Grid
      container
      direction="row"
      justifyContent="space-around"
      alignItems="center"
      sx={{
        mt: 3,
        mb: 3,
      }}
    >
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <Grid>
          <Paper
            elevation={8}
            sx={{
              p: 2,
              borderRadius: 3,
              maxWidth: 350,
            }}
          >
            <StaticDatePicker
              toolbarTitle="📅 오디션 시작일"
              onChange={(newStartDate) => setStartDate(newStartDate)}
              value={startDate}
              renderInput={(params) => <TextField {...params} />}
              componentsProps={{
                actionBar: {
                  actions: ['today', 'clear'],
                },
              }}
              minDate={dayjs(new Date())}
            />
          </Paper>
        </Grid>
        <Grid>
          <Paper
            elevation={8}
            sx={{
              p: 2,
              borderRadius: 3,
              maxWidth: 350,
            }}
          >
            <StaticDatePicker
              toolbarTitle="📅 오디션 종료일"
              minDate={startDate}
              onChange={(newEndDate) => setEndDate(newEndDate)}
              value={endDate}
              renderInput={(params) => <TextField {...params} />}
              vertical
              componentsProps={{
                actionBar: {
                  actions: ['today', 'clear'],
                },
              }}
            />
          </Paper>
        </Grid>
      </LocalizationProvider>
    </Grid>
  );
}
