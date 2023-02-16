import React, { useEffect, useState } from 'react';
import dayjs from 'dayjs';
import { useNavigate } from 'react-router-dom';
import { getDownloadURL, ref, uploadBytes } from 'firebase/storage';
import { storage } from 'imageup/firebase';
import { v4 } from 'uuid';
import Stack from '@mui/material/Stack';
import IconButton from '@mui/material/IconButton';
import AttachFileIcon from '@mui/icons-material/AttachFile';
import EventAvailableIcon from '@mui/icons-material/EventAvailable';
import Tooltip from '@mui/material/Tooltip';
import { useRecoilValue } from 'recoil';
import {
  auditionStartState,
  auditionEndState,
  auditionStartTimeState,
  auditionEndTimeState,
} from 'recoil/book/bookStore';
import { noticeFileUpload } from 'service/fileService';
import Swal from 'sweetalert2';
import { createAuditionSchedule } from 'service/audition/scheduleService';
import { FILE_REGEX, FILE_FOLDER } from 'constants';
import MakerScheduleCreateNotice from './MakerScheduleCreateNotice';
import { announcementProcessState } from '../../../recoil/process/processStore';
import { announcementListDetailState } from '../../../recoil/announcement/announcementStore';

export default function NoticeUpload() {
  const [files, setFiles] = useState([]);
  const [fileData, setfileData] = useState([]);
  const startDate = useRecoilValue(auditionStartState);
  const endDate = useRecoilValue(auditionEndState);
  const startTime = useRecoilValue(auditionStartTimeState);
  const endTime = useRecoilValue(auditionEndTimeState);
  const announcementDetail = useRecoilValue(announcementListDetailState);
  const processDetail = useRecoilValue(announcementProcessState);
  const navigate = useNavigate();

  useEffect(() => {
    if (files.length == fileData.length && fileData.length != 0) {
      const status = noticeFileUpload(announcementDetail.id, processDetail.processId, fileData);
      if (status === 201) {
        setFiles(() => []);
        setfileData(() => []);
      }
    }
  }, [fileData]);

  const changeFiles = (e) => {
    const list = Array.from(e.target.files);
    setFiles(list);
  };

  const uploadFiles = () => {
    if (files == null) return;
    files.forEach(async (file) => {
      const originName = file.name;
      const savedName = FILE_FOLDER + v4() + FILE_REGEX + originName.split(FILE_REGEX)[1];
      const fileRef = ref(storage, savedName);
      await uploadBytes(fileRef, file).then((snapshot) => {
        getDownloadURL(snapshot.ref).then((url) => {
          setfileData((prev) => [
            ...prev,
            {
              originName: originName,
              savedName: savedName,
              url: url,
            },
          ]);
        });
      });
    });
  };

  const createSchedule = () => {
    const payload = {
      startDate: startDate
        .set('h', startTime.get('h'))
        .set('m', startTime.get('m'))
        .format('YYYY-MM-DD HH:mm'),
      endDate: endDate
        .set('h', endTime.get('h'))
        .set('m', endTime.get('m'))
        .format('YYYY-MM-DD HH:mm'),
    };
    if (
      !startDate.isValid() ||
      !endDate.isValid() ||
      !startTime.isValid() ||
      !endTime.isValid() ||
      dayjs(payload.endDate).isBefore(dayjs(payload.startDate))
    ) {
      Swal.fire({
        title: '😉일정을 올바르게 입력해주세요.',
        icon: 'warning',
      });
      return;
    }
    Swal.fire({
      title: '다음 일정으로 오디션을 생성하시겠습니까?',
      html: `오디션 일정 : ${startDate.format('YYYY.MM.DD')} ~ ${endDate.format(
        'YYYY.MM.DD',
      )}<br/>면접 가능 시간 : ${startTime.format('HH:mm')} ~ ${endTime.format('HH:mm')}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '일정 확정',
    }).then(async (result) => {
      if (result.isConfirmed) {
        const status = createAuditionSchedule(
          announcementDetail.id,
          processDetail.processId,
          payload,
        );
        if (status === 201) {
          navigate('/casting');
        }
      }
    });
  };

  return (
    <>
      <MakerScheduleCreateNotice />
      <Stack direction="row" alignItems="center" justifyContent="flex-end" mt={3} ml={2}>
        <input multiple type="file" onChange={changeFiles} />
        <Tooltip title="업로드" placement="bottom">
          <IconButton color="primary" aria-label="upload file" onClick={uploadFiles}>
            <AttachFileIcon />
          </IconButton>
        </Tooltip>
        <Tooltip title="일정 확정" placement="bottom">
          <IconButton color="primary" aria-label="upload file" onClick={createSchedule}>
            <EventAvailableIcon />
          </IconButton>
        </Tooltip>
      </Stack>
    </>
  );
}
