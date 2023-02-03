import React, { useEffect, useState } from 'react';
import "./ActorList.css";
import PageBar from '../Common/PageBar';
import SearchBar from '../Common/SearchBar';
import { Link, useNavigate } from 'react-router-dom';
import Button from '../Common/Button';
import { collection, addDoc, getDocs, query } from 'firebase/firestore'
import {db} from '../../firebaseConfig'


const ActorList = () => {

  const navigate = useNavigate();

  // db의 users 컬렉션을 가져옴
  useEffect(() => {
    console.log('useEffect');
    const addData = async() => {
      console.log('addData');
      const data = await getDocs(collection(db, "dreamhi"));
      try{

        const docRef = await addDoc(collection(db, "dreamhi"), {
          age: 1,
          gender: 'woman',
          height: 100,
          name: 'lee'
        });
        console.log(docRef);
      } catch(e) {
        console.log(e);
      }
    }
    addData();
  },[]);



  return (
    <div>
      <SearchBar />
      <Link to={"/actor/detail"}>
        <div className="actor">
          <div className="actor_img">
            <img src="/img/elephant.png" />
          </div>
          <h5 className="actor_title"> 배우 소개</h5>
          <p className="actor_des"> 배우 소개 요약</p>
        </div>
      </Link>

      <Button
        title="글작성"
        onClick={() => {
          navigate("/actor/write")
        }} />

      <div className={"page_bar"}>
        <PageBar />
      </div>
    </div>
  );
};

export default ActorList;