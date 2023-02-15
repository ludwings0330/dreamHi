import Button from 'components/Common/CommonComponent/Button';
import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import { useRecoilValue } from 'recoil';
import {
  announcementProcessState,
  announcementListDetailProcessSelector,
} from 'recoil/process/processStore';
import { userSimpleState } from 'recoil/user/userStore';
import { Tag } from '../../../node_modules/@mui/icons-material/index';
import AnnouncementApply from './AnnouoncementApply';

function AnnouncementDetailButton(props) {
  const user = useRecoilValue(userSimpleState);

  const { processData } = props;
  console.log(processData);
  //   const { announcementId } = announcement.id;
  //   const { producerId } = announcement.producer.id;
  console.log('😍🤗😏', processData);

  const [buttonState, setButtonState] = useState('');

  function setButtonStateFromProcessData(processData) {
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
    } else if (processData.processState !== 'RECRUITING') {
      setButtonState('모집 마감');
    } else if (processData.processState == 'RECRUITING' && processData.userStageName == 'NONE') {
      setButtonState('지원하기');
    } else {
      setButtonState('지원불가');
    }
  }

  useEffect(() => {
    setButtonStateFromProcessData(processData);
  }, []);

  let button;
  if (buttonState == '지원하기' && user) {
    button = <AnnouncementApply />;
  } else {
    button = <Button title={'지원완료'} />;
  }

  return (
    <div>
      {button} {/* <Button title={buttonState} />{' '} */}
    </div>
  );
}

export default AnnouncementDetailButton;
