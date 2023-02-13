import React, { useState } from 'react';
import dayjs from 'dayjs';
import TextField from '@mui/material/TextField';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { StaticDatePicker } from '@mui/x-date-pickers/StaticDatePicker';
import { Box, ThemeProvider, createTheme } from '@mui/system';
import Grid from '@mui/material/Grid';
import { Paper } from '../../../node_modules/@mui/material/index';
import { useRecoilValue } from 'recoil';
import { userTypeState } from 'recoil/user/userStore';

const isWeekend = (date) => {
  const day = date.day();

  return day === 0 || day === 6;
};
export default function MakerDateCalendar() {
  const [startDate, setStartDate] = useState(dayjs(new Date()));
  const [endDate, setEndDate] = useState(dayjs(new Date() + 7));
  const userType = useRecoilValue(userTypeState);

  return (
    <Grid container direction="row" justifyContent="space-around" alignItems="center">
      <Grid>
        <Paper
          evaluate={3}
          sx={{
            bgcolor: 'background.paper',
            boxShadow: 1,
            borderRadius: 2,
            p: 2,
            maxWidth: 400,
          }}
        >
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <StaticDatePicker
              onChange={(newStartDate) => setStartDate(newStartDate)}
              value={startDate}
              renderInput={(params) => <TextField {...params} />}
              componentsProps={{
                actionBar: {
                  actions: ['today'],
                  reset: ['reset'],
                },
              }}
            />
          </LocalizationProvider>
        </Paper>
      </Grid>
      {userType === 'PRODUCER' ? (
        <Grid>
          <Paper
            evaluate={3}
            sx={{
              bgcolor: 'background.paper',
              boxShadow: 1,
              borderRadius: 2,
              p: 2,
              maxWidth: 400,
            }}
          >
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <StaticDatePicker
                onChange={(newEndDate) => setEndDate(newEndDate)}
                value={endDate}
                renderInput={(params) => <TextField {...params} />}
                componentsProps={{
                  actionBar: {
                    actions: ['today'],
                  },
                }}
              />
            </LocalizationProvider>
          </Paper>
        </Grid>
      ) : null}
    </Grid>
  );
}
