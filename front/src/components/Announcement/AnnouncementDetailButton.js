import React from 'react';
import { useState } from 'react';
import { useRecoilValue } from 'recoil';
import { announcementProcessState } from 'recoil/process/processStore';

function AnnouncementDetailButton(props) {
  const { announcement } = props;
  const { announcementId } = announcement.id;
  const { producerId } = announcement.producer.id;
  const processData = useRecoilValue(announcementProcessState);
  const [buttonState, setButtonState] = useState('지원하기');
  const buttonClick = () => {
    if (processData.processState == 'RECRUITING' && processData.userStageName == 'SUBMIT') {
      setButtonState('지원 완료');
    } else if (
      processData.processState == 'IN_PROGRESS' &&
      processData.userStageName == 'IN_PROGRESS'
    ) {
      setButtonState('오디션 진행중');
    } else if (processData.processState == 'IN_PROGRESS' && processData.userStageName == 'FAIL') {
      setButtonState('불합격');
    } else if (processData.processState == 'FINISH' && processData.userStageName == 'PASS') {
      setButtonState('합격');
    }
  };
  return <div></div>;
}

export default AnnouncementDetailButton;
