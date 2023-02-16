import React, { useEffect, useState } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';

// css
import 'swiper/css';
import 'swiper/css/bundle';
import './styles.css';
import './Casting.css';

// modules
import { Pagination, Keyboard, Navigation } from 'swiper';
import Button from '../Common/CommonComponent/Button';
import { useNavigate, Link } from 'react-router-dom';
import jwtApi from '../../util/JwtApi';

const CastingRow = ({ announcementId }) => {
  const navigate = useNavigate();
  const [castings, setCastings] = useState(null);
  console.log(announcementId);

  // 공고 아이디 가져왔으니 지원자 목록 조회
  useEffect(() => {
    jwtApi
      .get(`/api/announcements/${announcementId}/volunteers`)
      .then((response) => {
        console.log(response);
        setCastings(response.data.result);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

  const getVolunteerState = (state) => {
    switch (state) {
      case 'NONE':
        return '미정';
      case 'WAIT':
        return '보류';
      case 'PASS':
        return '합격';
      case 'FAIL':
        return '불합격';
    }
  };

  return (
    <>
      {castings
        ? castings.map((casting, idx) => {
            return (
              <div className="casting-body" key={casting.castingId}>
                <div className="casting-head">
                  <div className="casting-left">
                    <p className="casting-title">
                      {casting.castingName}역 - {casting.headCount}명
                    </p>
                    <div className="casting-state">
                      <span>미정 : {casting.stateSummary.NONE || 0}명</span>
                      <span>합격 : {casting.stateSummary.PASS || 0}명</span>
                      <span>보류 : {casting.stateSummary.WAIT || 0}명</span>
                      <span>불합격 : {casting.stateSummary.FAIL || 0}명</span>
                    </div>
                  </div>
                  <div className="casting-right">
                    <span>목록</span>
                    <Link
                      to="/casting/detail"
                      state={{ announcementId: announcementId, castingId: casting.castingId }}
                    >
                      <Button title="전체 보기" />
                    </Link>
                  </div>
                </div>
                <div className="casting-swiper">
                  <Swiper
                    keyboard={true}
                    slidesPerView={3}
                    spaceBetween={30}
                    navigation={true}
                    pagination={{
                      clickable: true,
                    }}
                    loop={true}
                    modules={[Pagination, Navigation, Keyboard]}
                    className="mySwiper"
                  >
                    {casting.volunteers.content.map((volunteer) => {
                      return (
                        <SwiperSlide key={volunteer.userId}>
                          <div className="casting-swiper-item">
                            <img src={volunteer.url || 'logo192.png'} alt="" />
                            <span>{volunteer.name}</span>
                            <span>{getVolunteerState(volunteer.state)}</span>
                          </div>
                        </SwiperSlide>
                      );
                    })}
                  </Swiper>
                </div>
              </div>
            );
          })
        : null}
    </>
  );
};

export default CastingRow;
