import React, { useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';

//import component
//import css
import { MDBBtn, MDBCol, MDBContainer, MDBRow } from 'mdb-react-ui-kit';
import './SearchMakerBar.css';
import jwtApi from '../../../util/JwtApi';

const SearchBar = ({ actorList, setActorList }) => {
  const search = (e) => {
    e.preventDefault();
    const actorFilter = {
      name: name,
      height: height,
      age: age,
      gender: selectGender,
      styles: checkedStyles,
      isFollow: false,
    };

    jwtApi
      .get(`/api/actors`, {
        params: { ...actorFilter },
      })
      .then((response) => {
        console.log(actorFilter, '검색 필터값');
        console.log('GET /api/actors');
        console.log(response, '필터를 통해 배우 목록 조회');
        setActorList(response.data.result.content);
      })
      .catch((error) => {
        setActorList([]);
        console.log('배우 필터링 조회 실패');
        console.log(actorFilter);
        console.log(error);
      });
  };

  //필터 -> 이름, 키, 나이, 성별, 스타일태그 + 검색

  const navigate = useNavigate();

  //useRef 관련
  const nameInputRef = useRef();
  const genderInputRef = useRef();
  const ageInputRef = useRef();
  const heightInputRef = useRef();
  const stylesInputRef = useRef();

  //이름 관련
  const [name, setName] = useState('');
  const handleChangeName = (e) => {
    setName(e.target.value);
  };

  //성별 관련
  const [selectGender, setSelectGender] = useState('');
  const handleSelectGender = (e) => {
    console.log(e.target.value);
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

  return (
    <>
      {/*최상위 tag에는 id로 할당하자*/}
      <form id={'maker-search-info'}>
        <div className="maker-info-top">
          <div className={'search-maker-name'}>
            <label>
              <span className={'maker-name-span'}>제작사 명</span>
              <input
                type={'text'}
                className={'maker-name-input'}
                placeholder={'검색어를 입력하세요'}
                value={name}
                ref={nameInputRef}
                onChange={handleChangeName}
              />
            </label>
          </div>
        </div>

        <MDBBtn onClick={search} className={'maker-search-buttons'}>
          검색
        </MDBBtn>
      </form>
    </>
  );
};

export default SearchBar;
