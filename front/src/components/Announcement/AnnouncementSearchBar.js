import React from 'react';
import { useState, useRef, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { SearchAnnouncement } from './AnnouncementAxios';
import api from 'util/APIUtils';
import {
  announcementFilterState,
  announcementListState,
} from 'recoil/announcement/announcementStore';
import { useRecoilState } from 'recoil';

// import Css
import { MDBBtn, MDBCol, MDBContainer, MDBRow } from 'mdb-react-ui-kit';
import './Announcement.css';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
//import component
// import Button from '../CommonComponent/Button';

//import css

function AnnouncementSearchBar() {
  //필터 -> 이름, 키, 나이, 성별, 스타일태그 + 검색

  const navigate = useNavigate();
  const [list, setList] = useRecoilState(announcementListState);

  //이름 관련
  const [name, setName] = useState('');
  const handleChangeName = (e) => {
    setName(e.target.value);
  };

  //성별 관련
  const [selectGender, setSelectGender] = useState('');
  const handleSelectGender = (e) => {
    setSelectGender(e.target.value);
  };

  //나이 관련
  const [age, setAge] = useState('');
  const handleChangeAge = (e) => {
    setAge(e.target.value);
  };

  //키 관련
  const [height, setHeight] = useState('');
  const handleChangeHeight = (e) => {
    setHeight(e.target.value);
  };

  //스타일 관련
  const [checkedStyles, setCheckedList] = useState([]);

  const stylesList = [
    { id: '32', description: '귀여운' },
    { id: '33', description: '날카로운' },
    { id: '34', description: '눈물이 많은' },
    { id: '35', description: '다정한' },
    { id: '36', description: '든든한' },
    { id: '37', description: '밝은' },
    { id: '38', description: '어두운' },
    { id: '39', description: '웃음이 많은' },
    { id: '40', description: '일상적인' },
    { id: '41', description: '카리스마' },
    { id: '42', description: '화가 많은' },
    { id: '43', description: '섹시한' },
    { id: '44', description: '청초한' },
  ];

  const handleCheckedStyles = (checked, item) => {
    if (checked) {
      setCheckedList([...checkedStyles, item]);
    } else if (!checked) {
      setCheckedList(checkedStyles.filter((el) => el !== item));
    }
  };

  //체크박스 해지 메소드
  const onRemove = (item) => {
    setCheckedList(checkedStyles.filter((el) => el !== item));
  };

  const searchData = {
    minHeight: height,
    maxHeight: '',
    minAge: age,
    maxAge: '',
    gender: selectGender,
    keyword: name,
    styles: checkedStyles,
    isFollow: '',
    isVolunteer: '',
    page: 0,
    size: 10,
  };

  console.log('💩🤖🤖', searchData);

  const searchAnnouncement = async () => {
    await SearchAnnouncement(
      searchData,
      (response) => {
        console.log('announcement Search Filter', response);
        setList(response.data.result.content);
      },
      () => {},
    );
  };

  return (
    <>
      {/*최상위 tag에는 id로 할당하자*/}
      <form id="announcement-search-info">
        <div className="search-title">
          <label>
            <span className="text-search-span">공고 제목</span>
            <input
              className="text-search-input"
              type="text"
              value={name}
              onChange={handleChangeName}
            />
          </label>
        </div>

        <div className={'search-title'}>
          <label>
            <span className="text-search-span">배역 성별</span>
            <select
              className="gender-search-input"
              name="gender"
              onChange={handleSelectGender}
              value={selectGender}
            >
              <option value="male">남</option>
              <option value="female">여</option>
            </select>
          </label>
        </div>

        <div className={'search-age'}>
          <label>
            <span className="text-search-span">배역 나이</span>
            <input
              className="number-search-input"
              type={'number'}
              value={age}
              onChange={handleChangeAge}
            />
          </label>
        </div>

        <div className={'search-height'}>
          <label>
            <span className="text-search-span">배역 키</span>
            <input
              className="number-search-input"
              type={'number'}
              value={height}
              onChange={handleChangeHeight}
            />
          </label>
        </div>

        <div className={'search-styles'}>
          <label>
            <span className="text-search-span">배역 스타일 </span>
          </label>
          {stylesList.map((item) => {
            return (
              <label key={item.id}>
                <input
                  className="style-checkbox"
                  type={'checkbox'}
                  value={item.id}
                  onChange={(e) => {
                    handleCheckedStyles(e.target.checked, e.target.value);
                  }}
                  checked={checkedStyles.includes(item.id) ? true : false}
                />
                <span className={'style-search-items-label'}> {item.description} </span>
              </label>
            );
          })}
          <Button onClick={searchAnnouncement} className="search-buttons">
            <span className="search-button-text">검색</span>
          </Button>
        </div>
      </form>
    </>
  );
}

export default AnnouncementSearchBar;
