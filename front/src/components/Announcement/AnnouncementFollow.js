import Button from 'components/Common/CommonComponent/Button';
import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useRecoilValue, useRecoilState } from 'recoil';
import {
  announcementListDetailSelector,
  announcementDetailId,
  announcementListSelector,
  announcementListDetailState,
  followAnnouncementState,
} from 'recoil/announcement/announcementStore';
import jwtApi from 'util/JwtApi';
import Chip from '../../../node_modules/@mui/material/Chip';
import { styled } from '../../../node_modules/@mui/material/styles';

function AnnouncementFollow(props) {
  const { announcementId } = useParams();
  console.log('🐳🐳🐳🐳', announcementId);
  const { isFollow } = props;

  const announcement = useRecoilValue(announcementListDetailSelector(announcementId));
  const [annouoncementData, setAnnouncementData] = useRecoilState(announcementListDetailState);
  setAnnouncementData(announcement);
  const [followAnnouncement, setFollowAnnouncement] = useState(isFollow);
  const sendData = { type: 'ANNOUNCEMENT', id: announcementId };

  // useEffect(() => {
  //   jwtApi.get(`api/follow`, { params: sendData }).then((response) => {
  //     console.log('😫🥱🥱', response.data.result);
  //     setFollowAnnouncement(response.data.result);
  //   });
  // }, [followAnnouncement, annouoncementData]);
  // console.log('followAnnouncement', followAnnouncement);

  const handleClick = async () => {
    try {
      if (followAnnouncement == false) {
        await jwtApi.post('api/follow', sendData).then((response) => {
          console.log('😫🥱🥱', response);
          setFollowAnnouncement(true);
        });
      } else if (followAnnouncement == true) {
        await jwtApi.delete('api/follow', { params: sendData }).then((response) => {
          console.log('😫🥱🥱', response);
          setFollowAnnouncement(false);
        });
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div>
      <Chip
        onClick={handleClick}
        label={followAnnouncement == true ? '팔로잉중' : '언팔로잉중'}
        variant="outlined"
        color="primary"
      />
      {/* <Button onClick={handleClick} title={followAnnouncement == true ? '팔로우' : '언팔로우'} /> */}
    </div>
  );
}

export default AnnouncementFollow;
