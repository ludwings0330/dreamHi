import React, { useEffect, useState } from 'react';
import axios from 'axios'
import { Link, useNavigate } from 'react-router-dom';

// import css
import {
    MDBCard,
    MDBCardImage,
    MDBCardBody,
    MDBCardTitle,
    MDBCardText,
    MDBRow,
    MDBCol
} from 'mdb-react-ui-kit';
import "./ActorList.css";

// import components
import PageBar from '../Common/CommonComponent/PageBar';
import SearchBar from '../Common/CommonComponent/SearchBar';
import Button from '../Common/CommonComponent/Button';
import { googleToken } from 'recoil/recoilActorState'

import {useRecoilValue} from "recoil";
import {actorListSelector} from "recoil/actor/actorStore";

const ActorList = () => {
  const navigate = useNavigate();
  const token = useRecoilValue(googleToken)

  const [actorList, setActorList] = useState([]);

  // api 요청 보내서 배우 목록 확보
  useEffect(() => {
    axios.get('http://i8a702.p.ssafy.io:8085/api/actors',
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setActorList(res.data.result.content)
        console.log(res.data.result.content,'데이터');
      })
      .catch((error) => {
        console.log('실패실패ㅠㅠ');
        console.log(error);
      });

  }, [setActorList]);
  
  

  

  return (
    <div>
      <SearchBar />
      <div>
      <MDBRow className='row-cols-1 row-cols-md-4 g-4'>
        {actorList.length > 0 && actorList.map((actor, idx) => (
          <Link to={`/actor/detail/${actor.actorProfileId}`} key={idx}>
            <MDBCol key={idx} className='h-100'>
              <MDBCard className='h-100'>
                <MDBCardImage
                  src={actor.pictureUrl}
                  alt={`${actor.name}'s picture`}
                  position='top'
                  height='200px'
                  object-fit='cover'
                />
                <MDBCardBody>
                  <MDBCardTitle>{actor.title}</MDBCardTitle>
                  <MDBCardText>
                    {actor.height}
                    {actor.pictureUrl}
                  </MDBCardText>
                </MDBCardBody>
              </MDBCard>
            </MDBCol>
          </Link>
        ))}
      </MDBRow>
      </div>
      {/*<Link to={`/actor/detail/`}>*/}
      {/*  <div className="actor">*/}
      {/*    <div className="actor_img">*/}
      {/*      <img src="/img/elephant.png" />*/}
      {/*    </div>*/}
      {/*    <h5 className="actor_title"> 배우 소개</h5>*/}
      {/*    <p className="actor_des"> 배우 소개 요약</p>*/}
      {/*  </div>*/}
      {/*</Link>*/}

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