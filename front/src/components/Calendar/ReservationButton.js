import React from 'react';
import dayjs from 'dayjs';
import IconButton from '@mui/material/IconButton';
import Tooltip from '@mui/material/Tooltip';
import Box from '@mui/material/Box';
import { useRecoilValue } from 'recoil';
import { checkTimeState, isBookedSelector, selectedDateState } from '../../recoil/book/bookStore';
import EventAvailableIcon from '@mui/icons-material/EventAvailable';
import VideoCameraFrontOutlinedIcon from '@mui/icons-material/VideoCameraFrontOutlined';
import Swal from 'sweetalert2';
import { updateBook } from '../../service/audition/scheduleService';
import { announcementListDetailState } from '../../recoil/announcement/announcementStore';
import { announcementProcessState } from '../../recoil/process/processStore';

function ReservationButton() {
  const isBooked = useRecoilValue(isBookedSelector());
  const selectedDate = useRecoilValue(selectedDateState);
  const checkTime = useRecoilValue(checkTimeState);
  const announcementDetail = useRecoilValue(announcementListDetailState);
  const processDetail = useRecoilValue(announcementProcessState);
  const reserveAudition = () => {
    console.log('--------------------------------');
    console.log(selectedDate);
    console.log(checkTime);
    console.log('--------------------------------');
    if (checkTime == null) {
      Swal.fire({
        title: '오디션 시간을 선택해주세요.',
        icon: 'warning',
      });
      return;
    }

    Swal.fire({
      title: '선택한 시간으로 예약하시겠습니까?',
      html: `예약일 : ${dayjs(selectedDate).format('YYYY.MM.DD')}
      <br/>면접 가능 시간 : ${dayjs(checkTime.startDateTime).format('HH:mm')} ~ ${dayjs(
        checkTime.endDateTime,
      ).format('HH:mm')}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '일정 확정',
    }).then(async (result) => {
      if (result.isConfirmed) {
        updateBook(announcementDetail.id, processDetail.processId, checkTime.id);
      }
    });
  };

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
          <IconButton color="primary" aria-label="reservation" onClick={reserveAudition}>
            <EventAvailableIcon fontSize="large" />
          </IconButton>
        </Tooltip>
      )}
    </Box>
  );
}

export default ReservationButton;
