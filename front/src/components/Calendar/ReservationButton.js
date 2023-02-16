import React from 'react';
import dayjs from 'dayjs';
import IconButton from '@mui/material/IconButton';
import Tooltip from '@mui/material/Tooltip';
import Box from '@mui/material/Box';
import { useRecoilState, useRecoilValue } from 'recoil';
import { checkTimeState, isBookedSelector, selectedDateState } from '../../recoil/book/bookStore';
import EventAvailableIcon from '@mui/icons-material/EventAvailable';
import VideoCameraFrontOutlinedIcon from '@mui/icons-material/VideoCameraFrontOutlined';
import Swal from 'sweetalert2';
import { updateBook } from '../../service/audition/scheduleService';
import { announcementListDetailState } from '../../recoil/announcement/announcementStore';
import { announcementProcessState } from '../../recoil/process/processStore';
import { useNavigate } from 'react-router-dom';
import { sessionIdState } from '../../recoil/audition/auditionStore';
import jwtApi from '../../util/JwtApi';

function ReservationButton() {
  const navigate = useNavigate();
  const isBooked = useRecoilValue(isBookedSelector());
  const selectedDate = useRecoilValue(selectedDateState);
  const checkTime = useRecoilValue(checkTimeState);
  const announcementDetail = useRecoilValue(announcementListDetailState);
  const processDetail = useRecoilValue(announcementProcessState);
  const [sessionId, setSessionId] = useRecoilState(sessionIdState);
  const reserveAudition = () => {
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
      <br/>오디션 시간 : ${dayjs(checkTime.startDateTime).format('HH:mm')} ~ ${dayjs(
        checkTime.endDateTime,
      ).format('HH:mm')}`,
      icon: 'info',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '예약',
    }).then(async (result) => {
      if (result.isConfirmed) {
        updateBook(announcementDetail.id, processDetail.processId, checkTime.id);
      }
    });
  };

  const updateSessionIdState = (sessionId) => {
    setSessionId(sessionId);
  };

  const goToAudition = () => {
    Swal.fire({
      title: '오디션장으로 입장하시겠습니까?',
      html: `응시일 : ${dayjs(isBooked.startDateTime).format('YYYY.MM.DD')}
      <br/>오디션 시간 : ${dayjs(isBooked.startDateTime).format('HH:mm')} ~ ${dayjs(
        isBooked.endDateTime,
      ).format('HH:mm')}`,
      icon: 'info',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '입장',
    }).then(async (result) => {
      if (result.isConfirmed) {
        const params = {
          producerId: announcementDetail.isEditor ? announcementDetail.producer.id : null,
          now: dayjs(new Date()).format('YYYY-MM-DD HH:mm:ss'),
        };
        const data = await jwtApi
          .get(
            `/api/announcements/${announcementDetail.id}/audition/on/${processDetail.processId}/session`,
            {
              params: params,
            },
          )
          .then((response) => {
            updateSessionIdState(response.data.result);
            navigate(`/live`);
            return response.data.result;
          });
      }
    });
  };

  return (
    <Box>
      {isBooked.reserved ? (
        <Tooltip title="입장" placement="bottom">
          <IconButton color="primary" aria-label="audition" onClick={goToAudition}>
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
