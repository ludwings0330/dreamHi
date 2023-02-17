import React from 'react';
import { useRecoilState } from 'recoil';
import { makerAnnouncementLists } from '../../../recoil/maker/makerStore';
import {} from './MakerAnnouncementList.css';

const MakerAnnouncements = ({ makerInfo }) => {
  const [makerAnnouncements, setMakerAnnouncements] = useRecoilState(makerAnnouncementLists);
  if (!makerAnnouncements) {
    return null;
  }

  console.log(makerAnnouncements.content);
  return (
    <>
      <div id={'maker-announcement-whole'}>
        <h1>공고리스트</h1>
        {makerAnnouncements
          ? makerAnnouncements.content.map((announcement) => (
              <div key={announcement.id}>
                {announcement.title}, {announcement.id}, {announcement.createdDate},{' '}
              </div>
            ))
          : null}
      </div>
    </>
  );
};

export default MakerAnnouncements;
