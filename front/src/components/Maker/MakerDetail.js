import React, {useEffect, useState} from 'react';
import {useRecoilState, useRecoilValue} from 'recoil';
import {useParams, useNavigate} from 'react-router-dom';
import axios from 'axios';
import api from "util/APIUtils";
import {useLocation} from "react-router";

//import component
import MakerIntroduce from './Info/MakerIntroduce';
import MakerFilmo from './Filmo/MakerFilmo';
import MakerPeopleList from './MakerPeopleList/MakerPeopleList';
import MakerAnnouncementList from './AnnouncementList/MakerAnnouncementList';
import Button from '../Common/CommonComponent/Button';

//import recoil
import {
    makerProfile,
    makerFilmoUrl,
    makerPhotoUrl,
    makerFilmoLists,
    googleToken
} from 'recoil/recoilMakerState';

const MakerDetail = () => {
    const {state} = useLocation();

    const navigate = useNavigate();
    const {makerProfileId} = useParams()
    const token = useRecoilValue(googleToken)


    const [makerFilmos, setMakerFilmos] = useRecoilState(makerFilmoLists);

    const [makerInfo, setMakerInfo] = useState({
        name: "",
        pictureUrl: "",
        description: "",
        isFollow: true,
    });

    // api 요청 보내서 제작사 목록 확보
    useEffect(() => {
        if (state != null) {
            console.log(state, "등록하기에서 state값")

            const data = {
                name: state.name,
                description: state.description
            };
        
        //수정
        axios.put('http://localhost:8080/api/producers',
                data, {
                    headers: {
                        Authorization: `Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAwMDEiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZW1haWwiOiJkZGY5OThAZ21haWwuY29tIiwiZXhwIjoxNjc4MjU2MjEyfQ.gSBnEPdb7LPDgTMwi5fDDlEdYxgbdJ6hInbddudS9suerZhCPuHDV3P9C6ygWTacOvhfT9tS8i94LP1qSszc0w`
                    },
        })
            .then((res) => {
                console.log(res, "get 이후")
                state = res.data.result
            })
            .catch((error) => {
                console.log(error, '에러 발생')
            });
    }
            //상세 조회
            axios.get(`http://localhost:8080/api/producers/${makerProfileId}`,
                {
                    headers: {
                        Authorization: `Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAwMDEiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZW1haWwiOiJkZGY5OThAZ21haWwuY29tIiwiZXhwIjoxNjc4MjU2MjEyfQ.gSBnEPdb7LPDgTMwi5fDDlEdYxgbdJ6hInbddudS9suerZhCPuHDV3P9C6ygWTacOvhfT9tS8i94LP1qSszc0w`,
                    },
                })
            .then((res) => {
                console.log(res.data.result, '잘 찍히는지 확인');
                setMakerInfo(res.data.result)

                console.log(makerInfo, '잘 찍히는지 확인');
                state = makerInfo
            })
            .catch((error) => {
                console.log(error, '실패 확인필요');
            });
    }, [setMakerInfo]);

    const [makerFilmoUrl, setMakerFilmoUrl] = useRecoilState(makerFilmoUrl);
    const [makerPhotoUrl, setMakerPhotoUrl] = useRecoilState(makerFilmoUrl);

return (
    <>
        <div className='photo'
             width={"400px"}
             height={"500px"}>
            <img src={makerInfo.picrtureUrl}
                 alt='image'
                 className="object-center"
            />
        </div>

        <div>{makerInfo.title}</div>
        <div>{makerInfo.description}</div>

        <Button
            title="수정하기"
            onClick={() => {
                navigate("/maker/write", {state: makerInfo})
            }}/>
        <Button
            title="삭제하기"
            onClick={() => {
                navigate("/maker/delete")
            }}/>
    </>
);
}
;

export default MakerDetail;