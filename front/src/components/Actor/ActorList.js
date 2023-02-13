import React, {useEffect, useState} from 'react';
import axios from 'axios'
import {Link, useNavigate} from 'react-router-dom';
import api from "util/APIUtils";

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
import {googleToken} from 'recoil/recoilActorState'
import {actorListSelector} from "recoil/actor/actorStore"

import {useRecoilValue} from "recoil";


const ActorList = () => {
    const navigate = useNavigate();
    const token = useRecoilValue(googleToken)


    //   const sendData = {
    //
    //       "filter": {
    //           "name": "user10",
    //           "height": 161,
    //           "age": 61,
    //           "gender": "MALE",
    //           "styles": [7, 8, 20, 23],
    //           "isFollow": false,
    //       },
    //       "page": 0,
    //       "size": 8
    //
    //
    //   };
    // const actorList = useRecoilValue(actorListSelector(sendData));
    //
    // useEffect(() => {
    //   console.log(actorList,444444444444);
    // }, []);


    const [actorList, setActorList] = useState([]);

    // api 요청 보내서 배우 목록 확보
    useEffect(() => {
        const actorFilter = {
            filter: {
                name: "",
                height: "",
                age: "",
                gender: "",
                styles: [],
                isFollow: "",
            },
            page: 0,
            size: 8
        };
        api.get(`/api/actors`, actorFilter)
            .then((response) => {
                console.log("GET /api/actors");
                console.log(response);
                setActorList(response.data.result.content)

            }).catch((error) => {
            console.log('실패실패ㅠㅠ');
            console.log(error);
        });

        // api.get('http://localhost:8080/api/actors',
        // {
        //   headers: {
        //     Authorization: `Bearer ${token}`,
        //   },
        // })
        // .then((res) => {
        //   setActorList(res.data.result.content)
        //   console.log(res.data.result.content,'데이터');
        // })
        // .catch((error) => {
        //   console.log('실패실패ㅠㅠ');
        //   console.log(error);
        // });

    }, [setActorList]);


    return (
        <div>
            <SearchBar actorList={actorList} setActorList={setActorList}/>
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
                }}/>

            <div className={"page_bar"}>
                <PageBar/>
            </div>
        </div>
    );
};

export default ActorList;