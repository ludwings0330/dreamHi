import React from 'react';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import PropTypes from 'prop-types';
import { useRecoilValueLoadable } from 'recoil';
import { volunteerDetailSelector } from '../../../recoil/volunteer/volunteerStore';
import { MDBCard, MDBCardBody, MDBCardImage, MDBCardText, MDBCardTitle } from 'mdb-react-ui-kit';

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

export default function MakerAuditionNotice() {
  const volunteerDetail = useRecoilValueLoadable(volunteerDetailSelector());

  switch (volunteerDetail.state) {
    case 'hasValue':
      return volunteerDetail.contents === 'loading' ? (
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
      ) : (
        <MDBCard className="h-100">
          <MDBCardImage
            src={volunteerDetail.contents.picrtureUrl}
            alt={`${volunteerDetail.contents.name}'s picture`}
            className={'actor-list-img'}
            position="top"
            height="200px"
            object-fit="cover"
          />
          <MDBCardBody>
            <MDBCardTitle className="actor-card-title">
              {volunteerDetail.contents.title}
            </MDBCardTitle>
            <MDBCardText>
              <span className="card-info">
                <span className={'actor-name-info-span'}>
                  이름 : {volunteerDetail.contents.name}
                </span>
                <span>성별 : {volunteerDetail.contents.gender === 'MALE' ? '남자' : '여자'} </span>
                <span>나이 : {volunteerDetail.contents.age}</span>
                <span>키 : {volunteerDetail.contents.height}cm</span>
                <span>스타일 :</span>
                <span className="card-info-style">
                  {volunteerDetail.contents.styles.map((style, idx) => (
                    <span key={idx}>{style.description}</span>
                  ))}
                </span>
              </span>
              {volunteerDetail.contents.styles.description}
            </MDBCardText>
          </MDBCardBody>
        </MDBCard>
      );
    case 'loading':
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
            }
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
    case 'hasError':
    default:
      return <div>Error...</div>;
  }
}
