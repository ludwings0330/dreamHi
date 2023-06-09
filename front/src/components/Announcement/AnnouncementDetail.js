import React, { useEffect } from 'react';
import { useNavigate, useParams, Link } from 'react-router-dom';
import Button from '../Common/CommonComponent/Button';
import AnnouncementDetailItem from './AnnouncementDetailItem';
import { useRecoilValue, useRecoilState } from 'recoil';
import {
  announcementListDetailSelector,
  announcementDetailId,
  announcementListSelector,
  announcementListDetailState,
} from 'recoil/announcement/announcementStore';
import AnnouncementDetailProcess from './AnnouncementDetailProcess';
import AnnouncementDetailButton from './AnnouncementDetailButton';
import AnnouncementFollow from './AnnouncementFollow';
import jwtApi from 'util/JwtApi';

// import Css
import { styled } from '../../../node_modules/@mui/material/styles';
import Box from '../../../node_modules/@mui/material/Box';
import Grid from '../../../node_modules/@mui/material/Grid';
import Chip from '../../../node_modules/@mui/material/Chip';

function AnnouncementDetail(props) {
  const navigate = useNavigate();
  const { announcementId } = useParams();

  const announcement = useRecoilValue(announcementListDetailSelector(announcementId));
  const [annouoncementData, setAnnouncementData] = useRecoilState(announcementListDetailState);
  setAnnouncementData(announcement);

  useEffect(() => {
    console.log('change AnnouncementData', annouoncementData);
  }, [annouoncementData]);

  useEffect(() => {
    console.log('herehere');
  }, []);

  return (
    <div>
      <AnnouncementDetailProcess announcement={announcement} />

      <AnnouncementFollow isFollow={announcement.isFollow} />
      <AnnouncementDetailItem announcement={announcement} />

      {/* <AnnouncementDetailButton key={announcementId} announcement={announcement} /> */}
      <Link to="/casting" state={{ announcementId: announcement.id }}>
        지원자 관리
      </Link>
    </div>
  );
}

export default AnnouncementDetail;
