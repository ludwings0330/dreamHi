import React from 'react';
import Layout from '../components/Common/MainLayout/Layout';
import CastingRow from '../components/Casting/CastingRow';
import {useLocation} from "react-router-dom";

const CastingPage = () => {
  const location = useLocation();
  return (
    <>
      <h1>캐스팅 지원자 목록</h1>
      <CastingRow announcementId={location.state.announcementId}/>
    </>
  );
};

export default CastingPage;
