import React from 'react';
import { useNavigate } from 'react-router-dom';

import Button from '../components/Common/CommonComponent/Button';
import CalendarAnnounce from '../components/Calendar/calendarAnnounce';

import Notice from '../components/Audition/Notice';
import styled, { css } from 'styled-components'
import DateCalendar from 'components/Calendar/DateCalendar';
import { Box, Container } from '../../node_modules/@mui/material/index';
import Divider from '@mui/material/Divider';
import MakerDateCalendar from 'components/Calendar/DateCalendar';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import TimeTable from 'components/Calendar/TimeTable';

function AuditionPage() {

  const navigate = useNavigate();
  return (
    <>
    <Container
    justify="center"
    // minWidth="60%"
    >

      <h1>Audition!!</h1>
      <Box sx={{
          mt: 5,
          backgroudColor: "#fff"
      }}>
      <Divider variant="fullWidth" sx={{mt:2, mb:2,  bgcolor: "black"}} />
        <CalendarMonthIcon sx={{color: "#45b6fe"}} /> 날짜 선택
      <Divider variant="fullWidth" sx={{mt:2, mb:2,  bgcolor: "black"}} />
        <MakerDateCalendar />
      </Box>
      <Divider variant="fullWidth" />
      <Box sx={{mt: 5}}>
      <Divider variant="fullWidth" sx={{mt:2, mb:2,  bgcolor: "black"}} />
        <AccessTimeIcon sx={{color: "#45b6fe"}} /> 시간 선택
      <Divider variant="fullWidth" sx={{mt:2, mb:2,  bgcolor: "black"}} />
        <TimeTable />
      </Box>
    </Container>
        {/* <div className={"audition-date-pick"}>
            <h1>면접 일정 선택</h1>
        </div>



      <Button
        title="면접보기"
        onClick={() => {
          navigate("/audition/meeting")
        }}
      />
      <AuditionContent>
       <AuditionContentLeft>
         <CalendarAnnounce />
       </AuditionContentLeft>
       <AuditionContentRight>
         <Notice />
       </AuditionContentRight>
      </AuditionContent> */}

    </>
  );
}

export default AuditionPage;

// const AuditionContent = styled.div`
//   display: flex;
//   left: 50%;
//   position: absolute;
//   transform: translateX(-50%);
//   margin-top: 30px;
//   `
//
// const AuditionContentLeft = styled.div`
//   margin-right: 200px;
// `
//
// const AuditionContentRight = styled.div`
//
// `