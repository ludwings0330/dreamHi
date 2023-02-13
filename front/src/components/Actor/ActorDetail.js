import React, {useEffect, useState} from 'react';
import {useRecoilState, useRecoilValue} from 'recoil';
import {useParams, useNavigate} from 'react-router-dom'
import axios from 'axios'
import api from "util/APIUtils";

//import component
import ActorIntroduce from './info/ActorIntroduce';
import ActorFilmo from './filmo/ActorFilmo';
import ActorPhoto from './photo/ActorPhoto';
import ActorVideo from './video/ActorVideo';
import Button from '../Common/CommonComponent/Button';

import {useLocation} from "react-router";

//import recoil
import {
    actorProfile,
    actorFilmoUrl,
    actorPhotoUrl,
    actorVideoUrl,
    actorPhotoLists,
    googleToken
} from 'recoil/recoilActorState';


const ActorDetail = () => {
    const {state} = useLocation();

    const navigate = useNavigate();
    const {actorProfileId} = useParams()
    const token = useRecoilValue(googleToken)

    const [actorPhotos, setActorPhotos] = useRecoilState(actorPhotoLists);
    const [actorInfo, setActorInfo] = useState({
            "styles": [
                {
                    "id": 1,
                    "description": "키가 큰"
                },
                {
                    "id": 3,
                    "description": "날씬한"
                },
                {
                    "id": 10,
                    "description": "복장이 훌륭한"
                }
            ],
            "userId": 2,
            "email": "user3@gmail.com",
            "name": "user3",
            "phone": "01012341234",
            "picrtureUrl": "https://firebasestorage.googleapis.com/v0/b/dreamhi-17f24.appspot.com/o/logo.png?alt=media&token=a3ff4d49-9210-44d1-94e3-6d9d2dd64f22",
            "actorProfileId": 22224,
            "age": 57,
            "description": "actor-profile-description-2",
            "gender": "MALE",
            "height": 130,
            "title": "actor-profile-2",
            "visible": true
        }
    );


    // api 요청 보내서 배우 목록 확보
    useEffect(() => {
        if (state != null) {
            console.log(state, " 등록하기에서 state값")

            const data = {


                name: state.name,
                actorProfileId: state.userId,
                title: state.title,
                gender: state.gender,
                age: state.age,
                height: state.height,
                description: state.description,
                deleteStyles: [1, 2, 6, 7],
                insertStyles: [5, 8]
            };

            axios.put(`http://localhost:8080/api/my/actor-profile`,
                data, {
                    headers: {
                        Authorization: `Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAwMDEiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZW1haWwiOiJkZGY5OThAZ21haWwuY29tIiwiZXhwIjoxNjc4MjU2MjEyfQ.gSBnEPdb7LPDgTMwi5fDDlEdYxgbdJ6hInbddudS9suerZhCPuHDV3P9C6ygWTacOvhfT9tS8i94LP1qSszc0w`
                    },
                }
            )
                .then((res) => {
                    // setActorInfo(res.data.result.filter(actor => actor.actorProfileId == actorProfileId)[0])
                    console.log(res, "put 수정 이후")
                    state = res.data.result
                    // setActorInfo(res.data.result)
                })
                .catch((error) => {
                    console.log(error, '이래서 실패했다');
                });
        }
        axios.get(`http://localhost:8080/api/users/${actorProfileId}/actor-profile`,
            {
                headers: {
                    Authorization: `Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAwMDEiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZW1haWwiOiJkZGY5OThAZ21haWwuY29tIiwiZXhwIjoxNjc4MjU2MjEyfQ.gSBnEPdb7LPDgTMwi5fDDlEdYxgbdJ6hInbddudS9suerZhCPuHDV3P9C6ygWTacOvhfT9tS8i94LP1qSszc0w`,
                },
            })
            .then((res) => {
                // setActorInfo(res.data.result.filter(actor => actor.actorProfileId == actorProfileId)[0])
                console.log(res.data.result, 6666666666666666666666666666)
                setActorInfo(res.data.result)

                console.log(actorInfo, 777777777777)
                state = actorInfo
            })
            .catch((error) => {
                console.log(error, '이래서 실패했다');
            });
    }, [setActorInfo]);


    useEffect(() => {
        axios.get(`http://localhost:8080/api/actors/100001/media`)
            // axios.get(`http://i8a702.p.ssafy.io:8085/api/actors/${actorInfo.actorProfileId}/media`)
            .then((res) => {
                console.log(res.data.result.pictures, 88888888888888)
                setActorPhotos(res.data.result.pictures)
            })
            .catch((err) => {
                console.log(err)
            })
    }, [setActorPhotos]);

    // const actorUrl = `${actorInfo.name}_${actorInfo.actorProfileId}`
    const [actFilmoUrl, setActFilmoUrl] = useRecoilState(actorFilmoUrl);
    const [actPhotoUrl, setActPhotoUrl] = useRecoilState(actorPhotoUrl);
    const [actVideoUrl, setActVideoUrl] = useRecoilState(actorVideoUrl);

    // setActFilmoUrl(`images/${actorUrl}/filmo`)
    // setActPhotoUrl(`images/${actorUrl}/photo`)
    // setActVideoUrl(`images/${actorUrl}/video`)

    return (
        <>
            <div className='photo'
                 width={"400px"}
                 height={"500px"}>
                <img src={actorInfo.picrtureUrl}
                     alt='image'
                     className="object-center"
                />
            </div>
            <div>{actorInfo.description}</div>

            <div>{actorInfo.title}</div>
            <div>{actorInfo.gender}</div>
            <div>{actorInfo.age}</div>
            <div>{actorInfo.height}</div>
            {actorInfo.styles.length > 0 && actorInfo.styles.map((actor, idx) => (
                <div>{actor['description']}</div>

            ))}
            <div>{actorInfo.phone}</div>
            <div>{actorInfo.email}</div>
            <ActorFilmo/>
            <ActorPhoto/>
            <ActorVideo/>

            <Button
                title="수정하기"
                onClick={() => {
                    navigate("/actor/write", {state: actorInfo})
                }}/>
            <Button
                title="삭제하기"
                onClick={() => {
                    navigate("/actor/delete")
                }}/>
        </>
    );
};

export default ActorDetail;