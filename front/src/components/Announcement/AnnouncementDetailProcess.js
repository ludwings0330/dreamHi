import React from 'react'
import { useRecoilValue } from 'recoil';
import { announcementListDetailProcessSelector } from 'recoil/announcement/announcementStore';

export default function AnnouncementDetailProcess(props) {
  const { announcement } = props;
  console.log(announcement)
  const processData = useRecoilValue(announcementListDetailProcessSelector(announcement.id))
  console.log('🤑🤑', processData)



  return (
    <div>
      {processData.processState === 'RECRUITING' ? '⭕' : '❌' }
    </div>
  )
}
