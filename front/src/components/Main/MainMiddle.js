import React from 'react';
import {} from './MainMiddle.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFilm, faCalendarDays } from '@fortawesome/free-solid-svg-icons';

const MainMiddle = () => {
  return (
    <>
      <div id={'mainmiddle-whole'}>
        <div className={'mainmiddle-top-ten'}>
          <h1>
            <FontAwesomeIcon icon={faFilm} />
            인기 공고 TOP 10
          </h1>
        </div>
        <div className={'mainmiddle-weekly'}>
          <h1>
            <FontAwesomeIcon icon={faCalendarDays} />
            이번 주 오디션 일정
          </h1>
        </div>
      </div>
    </>
  );
};

export default MainMiddle;
