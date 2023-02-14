import React from 'react';
import { useRecoilValue, useRecoilState } from 'recoil';
import {
  announcementListDetailProcessSelector,
  announcementProcessState,
} from 'recoil/process/processStore';

export default function AnnouncementDetailProcess(props) {
  const { announcement } = props;
  console.log(announcement);
  const processData = useRecoilValue(announcementListDetailProcessSelector(announcement.id));
  console.log('🤑🤑', processData);
  const [testState, setTestState] = useRecoilState(announcementProcessState);
  setTestState(processData);

  return (
    <div>
      {processData.processState === 'RECRUITING' ? '⭕' : '❌'}
      <p>너는 어딨는데??</p>
    </div>
  );
}
