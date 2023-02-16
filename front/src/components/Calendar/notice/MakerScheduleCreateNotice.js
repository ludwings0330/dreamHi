import React, { useEffect } from 'react';
import Box from '@mui/material/Box';
import PropTypes from 'prop-types';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import { useRecoilValue } from 'recoil';
import { announcementTitle } from 'recoil/announcement/announcement';
import { announcementListDetailState } from '../../../recoil/announcement/announcementStore';

function Item(props) {
  const { sx, ...other } = props;
  return (
    <Box
      sx={{
        p: 1,
        m: 1,
        border: '1px solid',
        borderColor: 'grey.300',
        borderRadius: 2,
        fontSize: '0.875rem',
        fontWeight: '700',
        ...sx,
      }}
      {...other}
    />
  );
}

Item.propTypes = {
  sx: PropTypes.oneOfType([
    PropTypes.arrayOf(PropTypes.oneOfType([PropTypes.func, PropTypes.object, PropTypes.bool])),
    PropTypes.func,
    PropTypes.object,
  ]),
};

export default function MakerScheduleCreateNotice() {
  const title = useRecoilValue(announcementTitle);
  const announcementDetail = useRecoilValue(announcementListDetailState);
  useEffect(() => {
    console.log(announcementDetail);
  }, []);
  return (
    <Paper
      elevation={8}
      sx={{
        ml: 3,
        borderRadius: 3,
      }}
    >
      <Box
        sx={{
          minHeight: 600,
          color: '#41424C',
        }}
      >
        <Typography variant="h5" align="center">
          {title}
        </Typography>
        <br />

        <Typography variant="subtitle1" align="center">
          화상 면접 가이드
        </Typography>
        <br />
        <Box
          sx={{
            display: 'flex',
            flexWrap: 'wrap',
            flexDirection: 'column',
            justifyContent: 'space-between',
            height: 500,
            // alignItems: 'flex-start',
          }}
        >
          <Item>
            <Typography
              xs
              variant="button"
              sx={{
                pl: 1,
                pr: 2,
                fontSize: 15,
              }}
            >
              1️⃣ 오디션 진행 가능한 시간대를 설정해주세요.
            </Typography>
          </Item>

          <Item>
            <Typography
              variant="button"
              sx={{
                pl: 1,
                pr: 2,
                fontSize: 15,
              }}
            >
              2️⃣ 면접은 30분 단위로 진행됩니다.
            </Typography>
          </Item>

          <Item>
            <Typography
              variant="button"
              sx={{
                pl: 1,
                pr: 2,
                fontSize: 15,
              }}
            >
              3️⃣ 공지사항 및 대본은 하단 버튼을 통해 업로드해주세요.
            </Typography>
          </Item>
        </Box>
      </Box>
    </Paper>
  );
}
