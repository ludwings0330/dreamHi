import React from 'react';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import PropTypes from 'prop-types';

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
  /**
   * The system prop that allows defining system overrides as well as additional CSS styles.
   */
  sx: PropTypes.oneOfType([
    PropTypes.arrayOf(PropTypes.oneOfType([PropTypes.func, PropTypes.object, PropTypes.bool])),
    PropTypes.func,
    PropTypes.object,
  ]),
};

export default function MakerAuditionNotice() {
  return (
    <>
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
          1️⃣ 지원자 예약 정보를 확인할 수 있습니다.
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
    </>
  );
}
