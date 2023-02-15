import React, { useEffect, useState } from 'react';
import { useRecoilState } from 'recoil';
import { useNavigate, useParams } from 'react-router-dom';

//import component
// import Button from '../Common/CommonComponent/Button';
import { Button } from '@mui/material';

//import recoil
import { makerAnnouncementLists, makerFilmoLists, makerMemberLists } from 'recoil/maker/makerStore';
import jwtApi from '../../util/JwtApi';
import MakerFilmo from './Filmo/MakerFilmo';
import MakerMembers from './MakerMembers/MakerMembers';
import MakerAnnouncements from './AnnouncementList/MakerAnnouncementList';

const MakerDetail = () => {
  const navigate = useNavigate();
  const { makerId } = useParams();
  const [makerFilmos, setMakerFilmos] = useRecoilState(makerFilmoLists);
  const [makerMembers, setMakerMembers] = useRecoilState(makerMemberLists);
  const [makerAnnouncements, setMakerAnnouncements] = useRecoilState(makerAnnouncementLists);
  const [makerInfo, setMakerInfo] = useState({});

  const getProducerDetailInfo = () => {
    jwtApi
      .get(`/api/producers/${makerId}`)
      .then((response) => {
        console.log(response.data.result);
        setMakerInfo(response.data.result);
      })
      .catch((e) => console.log(e));
  };

  const getProducerFilmos = () => {
    jwtApi
      .get(`/api/filmographies`, { params: { producerId: makerId } })
      .then((response) => {
        console.log(response.data.result);
        setMakerFilmos(response.data.result);
      })
      .catch((e) => console.log(e));
  };
  const getProducerMembers = () => {
    jwtApi
      .get(`/api/producers/${makerId}/users`)
      .then((response) => {
        console.log(response.data.result);
        setMakerMembers(response.data.result);
      })
      .catch((e) => console.log(e));
  };

  const getProducerAnnouncements = () => {
    jwtApi.get(`/api/announcements`, { params: { producerId: makerId } }).then((response) => {
      console.log(response.data.result);
      setMakerAnnouncements(response.data.result);
    });
  };

  // api 요청 보내서 제작사 목록 확보
  useEffect(() => {
    //제작사 정보 조회
    getProducerDetailInfo();
    // 제작사 필모그래피 조회
    getProducerFilmos();
    // 제작진 조회
    getProducerMembers();
    // 제작사 올린 공고 조회
    getProducerAnnouncements();
  }, []);

  return (
    <>
      <div>
        <h1>기본 정보 </h1>
        <div className="photo" width={'400px'} height={'500px'}>
          제작사 이미지 :
          <img src={makerInfo.pictureUrl} alt="image" className="object-center" />
        </div>

        <div>제작사 이름 : {makerInfo.name}</div>
        <div>제작사 설명 : {makerInfo.description}</div>
      </div>
      <hr />
      <MakerFilmo makerInfo={makerInfo} />
      <hr />
      <MakerMembers makerInfo={makerInfo} />
      <hr />
      <MakerAnnouncements makerInfo={makerInfo} />
      <hr />

      <Button variant="contained" onClick={() => navigate('/maker/write', { state: makerInfo })}>
        수정하기
      </Button>
      <Button variant="contained" onClick={() => navigate('/maker/delete')}>
        삭제하기
      </Button>
    </>
  );
};
export default MakerDetail;
