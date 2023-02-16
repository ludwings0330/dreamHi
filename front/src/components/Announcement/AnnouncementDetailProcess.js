import React from 'react';
import { useRecoilValue, useRecoilState } from 'recoil';
import {
  announcementListDetailProcessSelector,
  announcementProcessState,
} from 'recoil/process/processStore';
import AnnouncementDetailButton from './AnnouncementDetailButton';

export default function AnnouncementDetailProcess(props) {
  const { announcement } = props;
  console.log('28373764737', announcement.isEditor);
  const processData = useRecoilValue(announcementListDetailProcessSelector(announcement.id));
  console.log('🤑🤑', processData);
  const [testState, setTestState] = useRecoilState(announcementProcessState);
  setTestState(processData);
  console.log('😁😁😁', testState.processState);

  return (
    <div>
      {processData.processState === 'RECRUITING' ? '⭕' : '❌'}
      <AnnouncementDetailButton key={processData.id} processData={processData} />
      <p>너는 어딨는데??</p>
    </div>
  );
}
