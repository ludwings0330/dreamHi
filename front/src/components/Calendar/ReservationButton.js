import React from 'react';
import IconButton from '@mui/material/IconButton';
import Tooltip from '@mui/material/Tooltip';
import Box from '@mui/material/Box';
import { useRecoilValue } from 'recoil';
import { isBookedSelector } from '../../recoil/book/bookStore';
import EventAvailableIcon from '@mui/icons-material/EventAvailable';
import VideoCameraFrontOutlinedIcon from '@mui/icons-material/VideoCameraFrontOutlined';

function ReservationButton() {
  const isBooked = useRecoilValue(isBookedSelector());
  return (
    <Box>
      {isBooked.reserved ? (
        <Tooltip title="입장" placement="bottom">
          <IconButton color="primary" aria-label="audition">
            <VideoCameraFrontOutlinedIcon fontSize="large" />
          </IconButton>
        </Tooltip>
      ) : (
        <Tooltip title="예약" placement="bottom">
          <IconButton color="primary" aria-label="reservation">
            <EventAvailableIcon fontSize="large" />
          </IconButton>
        </Tooltip>
      )}
    </Box>
  );
}

export default ReservationButton;
