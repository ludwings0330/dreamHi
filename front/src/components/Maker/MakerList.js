import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {Link, useNavigate} from 'react-router-dom';
import api from "util/APIUtils";

//import CSS
import {
    MDBCard,
    MDBCardImage,
    MDBCardBody,
    MDBCardTitle,
    MDBCardText,
    MDBRow,
    MDBCol
} from 'mdb-react-ui-kit';
import {} from './MakerList.css';

//import common
import SearchBar from '../Common/CommonComponent/SearchBar';
import Button from '../Common/CommonComponent/Button';
import {googleToken} from 'recoil/recoilActorState';
import {makerListSelector} from "recoil/maker/makerStore"


import {useRecoilValue} from "recoil";


const MakerList = () => {
    const navigate = useNavigate();
    const token = useRecoilValue(googleToken);
    const [MakerList, setMakerList] = useState([]);

    // api 요청 보내서 제작사 목록 확보
    useEffect(() => {
        api.get(`/api/producers`)
            .then((response) => {
                console.log("GET /api/producers");
                console.log(response);
                setMakerList(response.data.result.content);
            })
            .catch((error) => {
                console.log('실패실패ㅠㅠ');
                console.log(error);
            });
    }, [setMakerList]);

    return (
        <div>
            <SearchBar/>
            <div>
                <MDBRow className="row-cols-1 row-cols-md-4 g-4">
                    {MakerList.length > 0 &&
                    MakerList.map((maker, idx) => (
                        <Link to={`/maker/detail/${maker.makerProfileId}`} key={idx}>
                            <MDBCol key={idx} className="h-100">
                                <MDBCard className="h-100">
                                    <MDBCardImage
                                        src={maker.pictureUrl}
                                        alt={`${maker.name}'s picture`}
                                        position="top"
                                        height="200px"
                                        object-fit="cover"
                                    />
                                    <MDBCardBody>
                                        <MDBCardTitle>{maker.title}</MDBCardTitle>
                                        <MDBCardText>
                                            {maker.title}
                                        </MDBCardText>
                                    </MDBCardBody>
                                </MDBCard>
                            </MDBCol>
                        </Link>
                    ))}
                </MDBRow>
            </div>

            <Button
                title="글작성"
                onClick={() => {
                    navigate('/maker/write');
                }}
            />
        </div>
    );
};

// <Link to={"/maker/detail"}>
//     <div className="maker">
//         <div className="maker_img"><img src="/img/elephant.png" className="actor_img"/></div>
//         <h5 className="maker_title"> 제작사 소개</h5>
//         <p className="maker_des"> 제작사 소개 요약</p>
//     </div>
// </Link>

export default MakerList;