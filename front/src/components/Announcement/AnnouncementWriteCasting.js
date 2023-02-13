import React, {useEffect, useState} from 'react';
import { useRecoilState } from 'recoil';
import { announcementCastingState } from 'recoil/announcement/announcement';

function AnnouncementWriteCasting(props) {
  // 전역으로 들고 다닐 casting array
  const [castingsArray, setCastingsArray] = useRecoilState(announcementCastingState);

  // recoil castingstate 에 추가하기 위한 이 컴포넌트 용 state
  const [casting, setCasting] = useState({
    name: '',
    description: '',
    headcount: '',
    minHeight: '',
    maxHeight: '',
    minAge: '',
    maxAge: '',
    gender: [],
    styles: [],
  });


  // style map 함수 쓰기 위해 따로 빼서 array 로
  const styles = [
    { value: 32, label: '귀여운' },
    { value: 33, label: '날카로운' },
    { value: 34, label: '눈물이 많은' },
    { value: 35, label: '다정한' },
    { value: 36, label: '든든한' },
    { value: 37, label: '밝은' },
    { value: 38, label: '어두운' },
    { value: 39, label: '웃음이 많은' },
    { value: 40, label: '일상적인' },
    { value: 41, label: '카리스마' },
    { value: 42, label: '화가 많은' },
    { value: 43, label: '섹시한' },
    { value: 44, label: '청초한' },
  ];

  // name 이랑 description 변경하는 함수
  const handleCastingChange = (e) => {
    setCasting({ ...casting, [e.target.name]: e.target.value });
  };


  // headcount state 및 변경 함수
  const [headcount, setHeadcount] = useState(0);
  const updateHeadcount = (value) => {
    setCasting({
      ...casting,
      headcount: value,
    });
  };


  //minHeight, maxHeight 설정 함수
  const updateMinHeight = (value) => {
    setCasting({
      ...casting,
      minHeight: value,
    });
  };

  const updateMaxHeight = (value) => {
      setCasting({
        ...casting,
        maxHeight: value,
      });
  };


  // minAge, maxAge 설정 함수
  const updateMinAge = (value) => {
    setCasting({
      ...casting,
      minAge: value,
    });
  };

  const updateMaxAge = (value) => {
      setCasting({
        ...casting,
        maxAge: value,
      });
    };



  //성별 설정 함수
  const [gender, setGender]= useState("");
  const handleGenderChange = (event) => {
    setGender(event.target.value);
    setCasting({ ...casting, [event.target.name]: event.target.value });
  };



  //스타일 설정 함수
  const [selectedStyles, setSelectedStyles] = useState([]);
  const MAX_SELECTED_OPTIONS = 5;

  const handleStyleClick = (e) => {
    const clickedValue = e.target.value;
    if (selectedStyles.includes(clickedValue)) {
      setSelectedStyles(selectedStyles.filter((style) => style !== clickedValue));
    } else if (selectedStyles.length >= MAX_SELECTED_OPTIONS){
      alert('최대 5개 까지만 선택가능합니다')
    } else if (selectedStyles.length < MAX_SELECTED_OPTIONS){
      setCasting({...casting, styles : [...selectedStyles, clickedValue]});
      setSelectedStyles([...selectedStyles, clickedValue]);
    };

    console.log(555666777)
    console.log(casting)


    console.log(18181818);
    console.log(selectedStyles);
  };

  //배역 추가 함수
  const addCasting = (e) => {

    // e.preventDefault()
    console.log(242422424);
    console.log(casting);
    console.log(announcementCastingState);
    console.log(casting.maxHeight)
    console.log(casting.minHeight)
    if (parseInt(casting.minHeight) > parseInt(casting.maxHeight)){alert('키 범위가 잘못되었습니다.')}
    else if (parseInt(casting.minAge) > parseInt(casting.maxAge)){alert('나이 범위가 잘못되었습니다.')}
    else {setCastingsArray([...castingsArray, casting])}

    setCasting({
      name: '',
      description: '',
      headcount: '',
      minHeight: '',
      maxHeight: '',
      minAge: '',
      maxAge: '',
      gender: [],
      styles: [],
    });

    console.log(castingsArray, '😭😭😭😭😭😭')
  };

  //배역 삭제 함수
  const deleteCasting = (index) => {
    let newArray = [...castingsArray];
    newArray.splice(index, 1);
    setCastingsArray(newArray);
    console.log(announcementCastingState)
    console.log(castingsArray)
  };

  useEffect(() => {
    setSelectedStyles(casting.styles)
    setGender(casting.gender)

    console.log('🐳🐳🐳🐳🐳')
    console.log('🤑🤑🤑',casting)
    console.log(castingsArray, '😭😭😭😭😭😭')

  }, [castingsArray]);

  return (
    <div>
      <h1> 배역 등록 </h1>


      <form>
        {/*// 배역 이름 입력*/}
        <p>
          <label>배역 이름</label> :{' '}
          <input 
          type="text" 
          name="name" 
          value={casting.name} 
          maxLength={20}
          onChange={handleCastingChange} />
        </p>

        {/*// 배역 상세 입력*/}
        <p>
          <label>배역 상세</label> :{' '}
          <input
            type="text"
            name="description"
            value={casting.description}
            maxLength={20}
            onChange={handleCastingChange}
          />
        </p>

        {/*// 배역 headcount 입력*/}
        <p>
        <label>배역 인원</label> :{' '}
        <input
            type="number"
            value={casting.headcount}
            onChange={(e) => updateHeadcount(e.target.value)}
            min='0'
        />
        </p>


        {/*키 입력*/}

        <p>
          <label>배역 키</label> :{' '}
        <input
            type="number"
            value={casting.minHeight}
            onChange={(e) => updateMinHeight(e.target.value)}
            min='0'
            max='210'
        />
          ~
        <input
            type="number"
            value={casting.maxHeight}
            onChange={(e) => updateMaxHeight(e.target.value)}
            min='0'
            max='210'
        />
        </p>



        {/*나이 입력*/}

        <p>
          <label>배역 나이</label> :{' '}
          <input
              type="number"
              value={casting.minAge}
              onChange={(e) => updateMinAge(e.target.value)}
              min='0'
              max='100'
          />
          ~
          <input
              type="number"
              value={casting.maxAge}
              onChange={(e) => updateMaxAge(e.target.value)}
              min='0'
              max='100'
          />
        </p>


        {/*성별 입력*/}
        <p>
          <label>배역 성별</label>
          <select name="gender" value={gender} onChange={handleGenderChange}>
            <option value=""></option>
            <option value="MALE">남성</option>
            <option value="FEMALE">여성</option>
          </select>
        </p>



        {/*// 스타일 입력*/}

        <select multiple size={5}>
          {styles.map((style) => (
            <option key={style.value} value={style.value} onClick={handleStyleClick}>
              {style.label}
            </option>
          ))}
        </select>
        {selectedStyles.map((style) => (
          <div key={style}>
            {styles[style-32].label}
            <button
              onClick={() =>
                setSelectedStyles(selectedStyles.filter((selectedStyle) => selectedStyle !== style))
              }
            >
              x
            </button>
          </div>
        ))}


        {/*전역에서 들고 다닐 casting array 업데이트*/}

        <button type="button" onClick={addCasting}>
          Add Casting
        </button>
        <ul>
          {castingsArray.map((casting, index) => (
              <li key={index}>
                {casting.name}역
                <button onClick={() => deleteCasting(index)}>Delete</button>
              </li>
          ))}
        </ul>
      </form>
    </div>
  );
}

export default AnnouncementWriteCasting;
