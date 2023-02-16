import React from 'react';
import { useRecoilValue, useRecoilState } from 'recoil';
import {
  announcementListDetailProcessSelector,
  announcementProcessState,
} from 'recoil/process/processStore';
import AnnouncementDetailButton from './AnnouncementDetailButton';
import { styled } from '../../../node_modules/@mui/material/styles';
import Box from '../../../node_modules/@mui/material/Box';
import Grid from '../../../node_modules/@mui/material/Grid';
import Chip from '../../../node_modules/@mui/material/Chip';
import './AnnouncementDetail.css';

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
      <div className="announcement-detail-process">
        <Chip
          label={processData.processState == 'RECRUITING' ? '모집중' : '마감'}
          variant="outlined"
          color="primary"
        />
      </div>
      <AnnouncementDetailButton key={processData.id} processData={processData} />
    </div>
  );
}
