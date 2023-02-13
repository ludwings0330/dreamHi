import React from 'react';

import './audition.css';
const Notice = () => {
  return (
    <div className="notice-body">
      <div className="notice-text">
        <p>
          면접을 진행하고 싶은 시간을 선택해주세요.
          <span>'날짜' 선택 후 '시간' 선택</span>
        </p>
        <p>면접 시간은 20분 간격으로 설정할 수 있습니다.</p>
        <p>공지사항 및 대본은 하단 버튼을 통해 업로드 해주세요.</p>
      </div>
      <div className="notice-button">
        <button type="button" className="why">
          파일 업로드
        </button>
        <button type="button" className="why">
          일정 확정
        </button>
      </div>
    </div>
  );
};

export default Notice;
