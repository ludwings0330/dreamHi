import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useRecoilState, useRecoilValue } from 'recoil';
import { checkTimeState } from '../../recoil/book/bookStore';
import { announcementListDetailState } from '../../recoil/announcement/announcementStore';
import { announcementProcessState } from '../../recoil/process/processStore';
import { sessionIdState } from '../../recoil/audition/auditionStore';
import Swal from 'sweetalert2';
import dayjs from 'dayjs';
import jwtApi from '../../util/JwtApi';
import Box from '@mui/material/Box';
import Tooltip from '@mui/material/Tooltip';
import IconButton from '@mui/material/IconButton';
import VideoCameraFrontOutlinedIcon from '@mui/icons-material/VideoCameraFrontOutlined';

function MakerAuditionButton() {
  const navigate = useNavigate();
  const checkTime = useRecoilValue(checkTimeState);
  const announcementDetail = useRecoilValue(announcementListDetailState);
  const processDetail = useRecoilValue(announcementProcessState);
  const [sessionId, setSessionId] = useRecoilState(sessionIdState);

  const updateSessionIdState = (sessionId) => {
    setSessionId(sessionId);
  };

  const goToAudition = () => {
    Swal.fire({
      title: '오디션장으로 입장하시겠습니까?',
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
            console.log(response);
            updateSessionIdState(response.data.result);
            navigate(`/live`);
            return response.data.result;
          });
      }
    });
  };

  return (
    <Box>
      <Tooltip title="입장" placement="bottom">
        <IconButton color="primary" aria-label="audition" onClick={goToAudition}>
          <VideoCameraFrontOutlinedIcon fontSize="large" />
        </IconButton>
      </Tooltip>
    </Box>
  );
}

export default MakerAuditionButton;
