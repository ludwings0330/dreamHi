import { React, useState, useRef } from 'react';
import { useNavigate } from "react-router-dom";
import SearchBar from '../../Common/CommonComponent/SearchBar';



//개인정보 입력 component
const ActorInfo = () => {

    const navigate = useNavigate();

    //useRef 관련
    const titleInputRef = useRef();
    const nameInputRef = useRef();
    const genderInputRef = useRef();
    const ageInputRef = useRef();
    const heightInputRef = useRef();
    const stylesInputRef = useRef();
    const phoneInputRef = useRef();
    const emailInputRef = useRef();



    //한줄 소개 관련
    const [title, setTitle] = useState("");
    const handleChangeTitle = (e) => {
        setTitle(e.target.value);
    }


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
    const MAX_SELECTED_OPTIONS = 5; 

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


    //전화번호 관련
    const [phone, setPhone] = useState("");
    const handleChangePhone = (event) => {
        setPhone(event.target.value);
    };

    //이메일 관련
    const [email, setEmail] = useState("");
    const handleChangeEmail = (event) => {
        setEmail(event.target.value);
    };



    return (
        <>
            <SearchBar />
            <form className={"actor-info"}>

                <div>
                    <label>
                        한줄소개
                        <input type={"text"}
                               value={title}
                               required
                               ref={titleInputRef}
                               onChange={handleChangeTitle}/>
                    </label>
                </div>

                <div>
            <label>
                이름
                <input type={"text"}
                       value={name}
                       required
                       ref={nameInputRef}
                       onChange={handleChangeName}/>
            </label>
                </div>

                <div>
                    <label>
                        성별
                    <select
                        name="gender"
                        onChange={handleSelectGender}
                        value={selectGender}
                        required
                        ref={genderInputRef}
                    >
                        <option value="male">남</option>
                        <option value="female">여</option>
                    </select>
                    </label>
                </div>


            <div>
            <label>
                나이
                <input type={"number"}
                       value={age}
                       required
                       ref={ageInputRef}
                       onChange={handleChangeAge}/>
            </label>
            </div>

                <div>
            <label>
                키
                <input type={"number"}
                       value={height}
                       required
                       ref={heightInputRef}
                       onChange={handleChangeHeight}/>
            </label>
                </div>

               <div>
                   <label>스타일</label>
                   {stylesList.map(item => {
                       return (
                           <label key={item.id}>
                           <input type={"checkbox"}
                                  value={item.description}
                                  onChange={e => {
                                      handleCheckedStyles(e.target.checked, e.target.value);
                                  }}
                                  checked={checkedStyles.includes(item.description) ? true : false} />
                               {item.description}
                           </label>
                       )
                   })}
               </div>

                <div>
            <label>
                연락처
                <input type={"text"}
                       value={phone}
                       required
                       ref={phoneInputRef}
                       onChange={handleChangePhone}/>
            </label>
                </div>

                <div>
            <label>
                이메일
                <input type={"text"}
                       value={email}
                       required
                       ref={emailInputRef}
                       onChange={handleChangeEmail}/>
            </label>
                </div>

            </form>
        </>
    );
};

export default ActorInfo;