import React, { useCallback, useState } from 'react';
import { storage } from 'imageup/firebase';
import DownloadIcon from '@mui/icons-material/Download';
import { Box, Tooltip, IconButton } from '@mui/material';
import { noticeFileSelector } from '../../../recoil/file/fileStore';
import { useRecoilValue } from 'recoil';
import { downloadFile } from 'util/FileUtil';
import axios from "axios";

export default function Attachment() {
  const noticeFiles = useRecoilValue(noticeFileSelector());

  const clickDownload = () => {
    if (noticeFiles.length === 0) return;
    for (const file of noticeFiles) {
      downloadFile(file)
    }
  };

  return (
    <>
      {noticeFiles != null && noticeFiles.length != 0 ? (
        <Box>
          첨부 파일 {noticeFiles.length} 개
          <Tooltip title="다운로드" placement="bottom">
            <IconButton color="primary" aria-label="download file" onClick={clickDownload}>
              <DownloadIcon fontSize="large" />
            </IconButton>
          </Tooltip>
        </Box>
      ) : null}
    </>
  );
}
