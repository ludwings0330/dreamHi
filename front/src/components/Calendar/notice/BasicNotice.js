import React, { useEffect } from 'react';
import Box from '@mui/material/Box';
import PropTypes from 'prop-types';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import Stack from '@mui/material/Stack';
import ActorAuditionNotice from './ActorAuditionNotice';
import MakerAuditionNotice from './MakerAuditionNotice';
import { useRecoilValue } from 'recoil';
import { announcementTitle } from 'recoil/announcement/announcement';
import { announcementListDetailState } from '../../../recoil/announcement/announcementStore';
import Attachment from './Attachment';
import ReservationButton from '../ReservationButton';
import MakerAuditionButton from '../MakerAuditionButton';

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

export default function BasicNotice() {
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
          }}
        >
          {announcementDetail.isEditor ? <MakerAuditionNotice /> : <ActorAuditionNotice /> }
        </Box>
        <Stack direction="row" alignItems="center" justifyContent="flex-end" mt={3} ml={2}>
          <Attachment />
          {announcementDetail.isEditor ? <MakerAuditionButton /> : <ReservationButton />}
        </Stack>
      </Box>
    </Paper>
  );
}
