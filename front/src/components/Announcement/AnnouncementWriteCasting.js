import React, { useState } from 'react';
import { useRecoilState } from 'recoil';
import { announcementCastingState } from '../../recoil/announcement';

function AnnouncementWriteCasting(props) {
  const [castingsArray, setCastingsArray] = useRecoilState(announcementCastingState);
  const [casting, setCasting] = useState({
    name: '',
    description: '',
    headcount: 0,
    // minHeight: 130,
    // maxHeight: 150,
    // minAge: 5,
    // maxAge: 10,
    // gender: "MALE",
    styles: [1, 3],
  });

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

  const [headcount, setHeadcount] = useState(0);


  const [selectedStyles, setSelectedStyles] = useState([]);
  const MAX_SELECTED_OPTIONS = 5;


  const handleCastingChange = (e) => {
    setCasting({ ...casting, [e.target.name]: e.target.value });
    console.log(87898)
    console.log(casting)
  };

  // const handleHeadcountChange = (e) => {
  //   setHeadcount(e)
  // }



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

  const addCasting = () => {
    console.log(242422424);
    console.log(casting);
    console.log(announcementCastingState);

    setCastingsArray([...castingsArray, casting]);
    setCasting({});
  };

  return (
    <div>
      <h1> 배역 등록 </h1>

      <form>
        <p>
          <label>작품 제목</label> :{' '}
          <input type="text" name="name" value={casting.name} onChange={handleCastingChange} />
        </p>
        <p>
          <label>배역 상세</label> :{' '}
          <input
            type="text"
            name="description"
            value={casting.description}
            onChange={handleCastingChange}
          />
        </p>

        {/*<button onClick={() => setHeadcount(headcount - 1)}>-</button>*/}
        <input type="number" value={casting.headcount} onChange={handleCastingChange} min="0" />
        {/*<button onClick={() => setHeadcount(headcount + 1)}>+</button>*/}


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

        <button type="button" onClick={addCasting}>
          Add Casting
        </button>
      </form>
    </div>
  );
}

export default AnnouncementWriteCasting;
