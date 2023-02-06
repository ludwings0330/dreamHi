import React from "react";
import {  Route, Routes } from "react-router-dom";
import MainPage from './pages/MainPage';
import ActorPage from './pages/ActorPage';
import MakerPage from './pages/MakerPage';
import CastingPage from './pages/CastingPage';
import AnnouncementPage from './pages/AnnouncementPage';
import AnnouncementList from './components/Announcement/AnnouncementList';
import AnnouncementWrite from './components/Announcement/AnnouncementWrite';
import AnnouncementDetail from './components/Announcement/AnnouncementDetail';
import CastingDetail from './components/Casting/CastingDetail';
import ActorList from './components/Actor/ActorList';
import ActorDetail from './components/Actor/ActorDetail';
import ActorWrite from './components/Actor/ActorWrite';
import LoginPage from './pages/Login/LoginPage';
import AuditionPage from './pages/AuditionPage';
import AuditionMeeting from './components/Audition/AuditionMeeting';

import Layout from './components/Common/Layout';
import MakerList from './components/Maker/MakerList';
import MakerDetail from './components/Maker/MakerDetail';



function App() {

  return (
    <Layout>
      <Routes>
        //메인페이지 관련 이동
        <Route index element={<MainPage />} />
        <Route path="/actor" element={<ActorPage />} />
        <Route path="/maker" element={<MakerPage />} />
        <Route path={"/login"} element={<LoginPage />} />

        //공고페이지 관련 이동
        <Route path="/announcement" element={<AnnouncementPage/>} />
        <Route path="/announcement/list" element={<AnnouncementList />} />
        <Route path="/announcement/write" element={<AnnouncementWrite />} />
        <Route path="/announcement/:announcementId" element={<AnnouncementDetail />} />


        //채용페이지 관련 이동
        <Route path="/casting" element={<CastingPage />} />
        <Route path="/casting/detail" element={<CastingDetail />} />


        //배우페이지 관련 이동
        <Route path={"actor/list"} element={<ActorList />} />
        <Route path={"/actor/detail"} element={<ActorDetail />} />
        <Route path={"/actor/write"} element={<ActorWrite />} />

        //오디션페이지 관련 이동
        <Route path={"/audition"} element={<AuditionPage />} />
        <Route path={"/audition/meeting"} element={<AuditionMeeting />} />

        //제작사페이지 관련 이동
        <Route path={"maker/list"} element={<MakerList />}/>
        <Route path={"maker/detail"} element={<MakerDetail />}/>
        <Route path={"maker/write"} element={<MakerWrite />}/>



      </Routes>
    </Layout>
  );
}

export default App;
