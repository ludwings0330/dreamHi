import React from "react";
import { useState, useRef, useEffect } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";



const SearchBar = () => {

  //필터 -> 이름, 키, 나이, 성별, 스타일태그 + 검색


  const navigate = useNavigate();

  //useRef 관련
  const nameInputRef = useRef();
  const genderInputRef = useRef();
  const ageInputRef = useRef();
  const heightInputRef = useRef();
  const stylesInputRef = useRef();



  //이름 관련
  const [name, setName] = useState("");
  const handleChangeName = (e) => {
    setName(e.target.value);
  };

  //성별 관련
  const [selectGender, setSelectGender] = useState("");
  const handleSelectGender = (e) => {
    setSelectGender(e.target.value);
  };
  

  //나이 관련
  const [age, setAge] = useState("");
  const handleChangeAge = (e) => {
    setAge(e.target.value);
  };


  //키 관련
  const [height, setHeight] = useState("");
  const handleChangeHeight = (e) => {
    setHeight(e.target.value);
  };


  //스타일 관련
  const [checkedStyles, setCheckedList] = useState([]);

  const stylesList = [
    {id: '32', description: '귀여운' },
    {id: '33', description: '날카로운' },
    {id: '34', description: '눈물이 많은' },
    {id: '35', description: '다정한' },
    {id: '36', description: '든든한' },
    {id: '37', description: '밝은' },
    {id: '38', description: '어두운' },
    {id: '39', description: '웃음이 많은' },
    {id: '40', description: '일상적인' },
    {id: '41', description: '카리스마' },
    {id: '42', description: '화가 많은' },
    {id: '43', description: '섹시한' },
    {id: '44', description: '청초한' },
  ];


  const handleCheckedStyles = (checked, item) => {
    if (checked) {
      setCheckedList([...checkedStyles, item]);
    } else if (!checked) {
      setCheckedList(checkedStyles.filter(el => el !== item));
    }
  };

  //체크박스 해지 메소드
  const onRemove = item => {
    setCheckedList(checkedStyles.filter(el => el !== item));
  };




  return (

     <>

     </>



  );
}

export default SearchBar;