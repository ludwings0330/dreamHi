import React, { useEffect, useState } from 'react';
import { getDownloadURL, listAll, ref, uploadBytes } from 'firebase/storage';
import { storage } from 'imageup/firebase';
import { v4 } from 'uuid';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import IconButton from '@mui/material/IconButton';
import AttachFileIcon from '@mui/icons-material/AttachFile';
import EventAvailableIcon from '@mui/icons-material/EventAvailable';
import Tooltip from '@mui/material/Tooltip';
import { Paper } from '../../../node_modules/@mui/material/index';
import BasicNotice from './BasicNotice';
import { useRecoilValue } from 'recoil';
import {
  auditionStartState,
  auditionEndState,
  auditionStartTimeState,
  auditionEndTimeState,
} from 'recoil/book/bookStore';
import { noticeFileUpload } from 'service/fileService';
import { vlounteerUserIdState } from 'recoil/volunteer/volunteerStore';
import { userTypeState } from 'recoil/user/userStore';
import VolunteerCard from 'components/Audition/VolunteerCard';

export default function NoticeUpload() {
  const [files, setFiles] = useState([]);
  const [fileUrls, setFileUrls] = useState([]);
  const startDate = useRecoilValue(auditionStartState);
  const endDate = useRecoilValue(auditionEndState);
  const startTime = useRecoilValue(auditionStartTimeState);
  const endTime = useRecoilValue(auditionEndTimeState);
  const checkVolunteerUserId = useRecoilValue(vlounteerUserIdState);
  const userType = useRecoilValue(userTypeState);
  useEffect(() => {
    console.log(files);
  }, [files]);

  useEffect(() => {
    if (files.length == fileUrls.length && fileUrls.length != 0) {
      console.log('useEffect!! ', fileUrls);
      // 💥💥💥 db에 url 저장 -> 구현해야함
      noticeFileUpload(fileUrls);
    }
  }, [fileUrls]);

  const changeFiles = (e) => {
    const list = Array.from(e.target.files);
    setFiles(list);
  };

  const uploadFiles = () => {
    if (files == null) return;
    files.forEach((file) => {
      const fileRef = ref(storage, `audition/${file.name + v4()}`);
      uploadBytes(fileRef, file).then((snapshot) => {
        getDownloadURL(snapshot.ref).then((url) => {
          setFileUrls((prev) => [...prev, url]);
        });
      });
    });
  };

  const createSchedule = () => {
    const payload = {
      startDate: startDate,
      endDate: endDate,
      startTime: startTime,
      endTime: endTime,
    };
    console.log(payload);
    // 💥💥💥 구현 해야함
  };

  return (
    <>
      {userType === 'producer'.toUpperCase() && checkVolunteerUserId != null ? (
        <VolunteerCard />
      ) : (
        <BasicNotice />
      )}
      <Stack direction="row" alignItems="center" justifyContent="flex-end" mt={3} ml={2}>
        <input multiple type="file" onChange={changeFiles} />
        <Tooltip title="다운로드" placement="bottom">
          <IconButton color="primary" aria-label="upload file" onClick={uploadFiles}>
            <AttachFileIcon />
          </IconButton>
        </Tooltip>
        {/* <Tooltip title="일정 확정" placement="bottom">
            <IconButton color="primary" aria-label="upload file" onClick={createSchedule}>
                    <EventAvailableIcon />
            </IconButton>
        </Tooltip> */}
      </Stack>
    </>
  );
}
