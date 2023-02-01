import React from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';

// css
import 'swiper/css';
import "swiper/css/bundle";
import './styles.css';
import './Casting.css'

// modules
import { Pagination, Keyboard, Navigation } from "swiper";

function CastingRow(props) {
  // const dummydata = [
  //   {
  //     castingName: '학생',
  //     castingUndecide: 4,
  //     castingPass: 3,
  //     castingDefer: 13,
  //     castingReject: 4,
  //     castingLists: [
  //       {
  //         name: '정효상',
  //         userPk: '343423',
  //         castingState: '합격'
  //       },
  //       {
  //         name: '이여민',
  //         userPk: '3234243423',
  //         castingState: '보류'
  //       },
  //       {
  //         name: '정지은',
  //         userPk: '3243423',
  //         castingState: '불합격'
  //       },
  //     ]
  //   },
  //   {
  //     castingName: '선생',
  //     castingUndecide: 4,
  //     castingPass: 3,
  //     castingDefer: 13,
  //     castingReject: 4,
  //     castingLists: {
  //
  //     }
  //   },
  // ]
  //
  // console.log(dummydata.Casting_name)
  return (
    // 이 부분을 map method를 통해 공고의 수만큼 rendering
    <div className="casting-body">
      <div className="casting-head">
        <div className="casting-left">
          <p className="casting-title">test역 - test명</p>
          <div className="casting-state">
            <span>미정 : test명</span>
            <span>합격 : test명</span>
            <span>보류 : test명</span>
            <span>불합격 : test명</span>
          </div>
        </div>
        <div className="casting-right">
          <span>목록</span>
          <span>전체보기 ∨</span>
        </div>
      </div>
      <div className="casting-swiper">
        <Swiper
          keyboard={true}
          slidesPerView={3}
          spaceBetween={30}
          navigation={true}
          pagination={{
            clickable: true
          }}
          loop={true}
          modules={[Pagination, Navigation, Keyboard]}
          className="mySwiper"
        >
          <SwiperSlide>
            <div className="casting-swiper-item">
              <img src='logo192.png' alt='' />
              <span>Slide 1</span>
              <span>합격</span>
            </div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="casting-swiper-item">
              <img src='logo192.png' alt='' />
              <span>Slide 2</span>
              <span>합격</span>
            </div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="casting-swiper-item">
              <img src='logo192.png' alt='' />
              <span>Slide 3</span>
              <span>합격</span>
            </div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="casting-swiper-item">
              <img src='logo192.png' alt='' />
              <span>Slide 4</span>
              <span>합격</span>
            </div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="casting-swiper-item">
              <img src='logo192.png' alt='' />
              <span>Slide 5</span>
              <span>합격</span>
            </div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="casting-swiper-item">
              <img src='logo192.png' alt='' />
              <span>Slide 6</span>
              <span>합격</span>
            </div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="casting-swiper-item">
              <img src='logo192.png' alt='' />
              <span>Slide 7</span>
              <span>합격</span>
            </div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="casting-swiper-item">
              <img src='logo192.png' alt='' />
              <span>Slide 8</span>
              <span>합격</span>
            </div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="casting-swiper-item">
              <img src='logo192.png' alt='' />
              <span>Slide 9</span>
              <span>합격</span>
            </div>
          </SwiperSlide>
        </Swiper>
      </div>
    </div>
  );
}

export default CastingRow;