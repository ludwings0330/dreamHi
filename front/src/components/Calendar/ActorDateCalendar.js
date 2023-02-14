import React, { useState } from 'react';
import dayjs from 'dayjs';
import TextField from '@mui/material/TextField';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { StaticDatePicker } from '@mui/x-date-pickers/StaticDatePicker';
import { Box, ThemeProvider, createTheme } from '@mui/system';
import Grid from '@mui/material/Grid';
import { Paper } from '../../../node_modules/@mui/material/index';
import { useRecoilValue, useRecoilState } from 'recoil';
import { userTypeState } from 'recoil/user/userStore';
import {
  auditionStartState,
  auditionEndState,
  auditionSelectState,
  checkTimeState,
} from 'recoil/book/bookStore';
import { useEffect } from 'react';

export default function ActorDateCalendar() {
  const [startDate, setStartDate] = useRecoilState(auditionStartState);
  const [endDate, setEndDate] = useRecoilState(auditionEndState);
  const userType = useRecoilValue(userTypeState);
  const [selectDate, setSelectDate] = useRecoilState(auditionSelectState);
  const [checkTime, setCheckTime] = useRecoilState(checkTimeState);
  useEffect(() => {
    console.log('일 변경 시 checkTime 수정');
    console.log(checkTime);
  }, [checkTime]);
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
              borderRadius: 3,
              p: 2,
              maxWidth: 400,
            }}
          >
            <StaticDatePicker
              toolbarTitle="📅 오디션 일정 예약"
              onChange={(newDate) => {
                console.log('일 변경 시 checkTime 수정');
                console.log(checkTime);
                setSelectDate(newDate);
                setCheckTime();
              }}
              value={selectDate}
              renderInput={(params) => <TextField {...params} />}
              componentsProps={{
                actionBar: {
                  actions: ['today', 'clear'],
                },
              }}
              minDate={startDate}
              maxDate={endDate}
            />
          </Paper>
        </Grid>
      </LocalizationProvider>
    </Grid>
  );
}
