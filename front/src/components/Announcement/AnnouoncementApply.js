import React, { useState } from 'react';
import Modal from 'react-modal';
import ActorWrite from 'components/Actor/ActorWrite';
import Button from 'components/Common/CommonComponent/Button';
import { useRecoilValue } from 'recoil';
import {
  announcementListDetailState,
  announcementListDetailCastingSelector,
} from 'recoil/announcement/announcementStore';
import { userSimpleState } from 'recoil/user/userStore';
import jwtApi from '../../util/JwtApi';
import ActorDetail from 'components/Actor/ActorDetail';

const ApplyModal = ({ isOpen, onRequestClose }) => {
  const user = useRecoilValue(userSimpleState);
  const userId = user.id;
  console.log('😎😋', userId);
  const announcementData = useRecoilValue(announcementListDetailState);
  const announcementId = announcementData.id;
  console.log('💕💕💕💕', announcementData);
  console.log(announcementId);
  const CastingData = useRecoilValue(announcementListDetailCastingSelector(announcementId));
  const data = CastingData;
  console.log(data);
  const castings = data.map((casting) => casting.id);
  console.log(castings);

  const profileApply = () => {
    jwtApi
      .post(`/api/announcements/${announcementId}/volunteers`, { castingIds: castings })
      .then((response) => {
        console.log('post/apply/profile');
        console.log(response);
      });
  };

  // const castings = useRecoilValue(announcementListDetailCastingSelector);

  return (
    <Modal isOpen={isOpen} onRequestClose={onRequestClose}>
      <ActorDetail userId={userId} />
      모달이당
      <Button title={'지원취소'} onClick={onRequestClose} />
      <Button title={'프로필 제출'} onClick={profileApply} />
    </Modal>
  );
};

const AnnouncementApply = () => {
  const [modalIsOpen, setModalIsOpen] = useState(false);

  const openModal = () => {
    setModalIsOpen(true);
  };

  const closeModal = () => {
    setModalIsOpen(false);
  };

  return (
    <div>
      <Button title={'지원하기'} onClick={openModal} />
      <ApplyModal isOpen={modalIsOpen} onRequestClose={closeModal} />
    </div>
  );
};

export default AnnouncementApply;
