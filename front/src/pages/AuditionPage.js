import React from 'react';
import { useNavigate } from 'react-router-dom';

import Button from '../components/Common/CommonComponent/Button';
import CalendarAnnounce from '../components/Calendar/calendarAnnounce';

import Notice from '../components/Audition/Notice';
import styled, { css } from 'styled-components'

function AuditionPage(props) {
  const navigate = useNavigate()
  return (
    <>
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
      </AuditionContent>

    </>
  );
}

export default AuditionPage;

const AuditionContent = styled.div`
  display: flex;
  left: 50%;
  position: absolute;
  transform: translateX(-50%);
  margin-top: 30px;
  `

const AuditionContentLeft = styled.div`
  margin-right: 200px;
`

const AuditionContentRight = styled.div`

`