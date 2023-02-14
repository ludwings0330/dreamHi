import React, { useState } from 'react';
import Modal from 'react-modal';
import ActorWrite from 'components/Actor/ActorWrite';
import Button from 'components/Common/CommonComponent/Button';
import { useRecoilValue } from 'recoil';

const ApplyModal = ({ isOpen, onRequestClose }) => {
  // const { announcement } = props;
  // const { announcementId } = announcement.id;
  // const castings = useRecoilValue(announcementListDetailCastingSelector);
  // const applyProfile = async () => {
  //   await applyActorProfile(announcementId);
  // };

  return (
    <Modal isOpen={isOpen} onRequestClose={onRequestClose}>
      {/* <ActorWrite/> */}
      모달이당
      <Button title={'지원취소'} onClick={onRequestClose} />
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
