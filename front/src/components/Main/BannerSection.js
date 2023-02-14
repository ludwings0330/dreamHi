import React from 'react';

import './BannerSection.css';

function BannerSection() {
  return (
    <section id="mainpage__banner">
      {/* 배너섹션 수정사항 없을예정. */}
      <div id="banner__left">
        <div id="mainpage__banner__title">
          DreamHi에서
          <br />
          당신의 꿈을 펼쳐보세요.
        </div>
        <div id="mainpage__banner__description">
          누구나에게 열려있는
          <br />
          '우리 모두의 공간'
          <br />
          <br />
          Dream Hi와 함께라면 걱정하지 마세요!
          <br />
          영화인에 의한,
          <br /> 영화인을 위한,
          <br />
          영화인들의 사이트입니다.
        </div>
      </div>
      <div id="mainpage__banner__main">
        "영화를 만드는 사람들"
        <br />
        Dream Hi
        <br />
        빛나는 별들의
        <br />
        내일을 응원합니다👊
      </div>
    </section>
  );
}

export default BannerSection;
