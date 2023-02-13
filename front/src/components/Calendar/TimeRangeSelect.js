import React, { useEffect, useState } from 'react';
import dayjs from 'dayjs';
import TextField from '@mui/material/TextField';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { TimePicker } from '@mui/x-date-pickers/TimePicker';
import Grid from '@mui/material/Grid';
import Divider from '@mui/material/Divider';
import Box from '@mui/material/Box';
import { useRecoilState } from 'recoil';
import { auditionEndTimeState, auditionStartTimeState } from 'recoil/book/bookStore';

export default function TimeRangeSelect() {
  const [start, setStart] = useRecoilState(auditionStartTimeState);
  const [end, setEnd] = useRecoilState(auditionEndTimeState);

  const setStartTime = (newValue) => {
    setStart(newValue);
  };
  const setEndTime = (newValue) => {
    setEnd(newValue);
  };

  return (
    <Box sx={{ mt: 3, mb: 3 }}>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <Grid container spacing={{ xs: 2 }} columns={{ xs: 8 }} align="center">
          <Grid item xs={4}>
            <TimePicker
              label="🎬 시작 시간"
              value={start}
              onChange={(newValue) => {
                setStartTime(newValue);
              }}
              renderInput={(params) => <TextField {...params} />}
            />
          </Grid>

          {/* <Divider  oriental="vertical" /> */}

          <Grid item xs={4}>
            <TimePicker
              label="🎬 종료 시간"
              value={end}
              onChange={(newValue) => {
                setEndTime(newValue);
              }}
              minTime={start}
              renderInput={(params) => <TextField {...params} />}
            />
          </Grid>
        </Grid>
      </LocalizationProvider>
    </Box>
  );
}
