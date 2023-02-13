import React from "react";
import {Route, Routes} from "react-router-dom";

//import main page
import MainPage from './pages/MainPage';
import LoginPage from './pages/Login/LoginPage';
import Layout from './components/Common/MainLayout/Layout';
import OAuth2RedirectHandler from './user/oauth2/OAuth2RedirectHandler';

//import actor
import ActorPage from './pages/ActorPage';
import ActorList from './components/Actor/ActorList';
import ActorDetail from './components/Actor/ActorDetail';
import ActorWrite from './components/Actor/ActorWrite';
import ActorDelete from './components/Actor/ActorDelete';

//import maker
import MakerPage from './pages/MakerPage';
import MakerList from './components/Maker/MakerList';
import MakerDetail from './components/Maker/MakerDetail';
import MakerWrite from './components/Maker/MakerWrite';

//import casting
import CastingPage from './pages/CastingPage';
import CastingDetail from './components/Casting/CastingDetail';

//import announcement
import AnnouncementPage from './pages/AnnouncementPage';
import AnnouncementList from './components/Announcement/AnnouncementList';
import AnnouncementWrite from './components/Announcement/AnnouncementWrite';
import AnnouncementDetail from './components/Announcement/AnnouncementDetail';

//import audition
import AuditionPage from './pages/AuditionPage';
import AuditionMeeting from './components/Audition/AuditionMeeting';

//import collection
import Collection from './components/Collection/Collection';


function App() {

    return (
        <Layout>
            <Routes>
                //메인페이지 관련 이동
                <Route index element={<MainPage/>}/>
                <Route path="/actor" element={<ActorPage/>}/>
                <Route path="/maker" element={<MakerPage/>}/>
                <Route path={"/login"} element={<LoginPage/>}/>

                //공고페이지 관련 이동
                <Route path="/announcement" element={<AnnouncementPage/>}/>
                <Route path="/announcement/list" element={<AnnouncementList/>}/>
                <Route path="/announcement/write" element={<AnnouncementWrite/>}/>
                <Route path="/announcement/:announcementId" element={<AnnouncementDetail/>}/>


                //채용페이지 관련 이동
                <Route path="/casting" element={<CastingPage/>}/>
                <Route path="/casting/detail" element={<CastingDetail/>}/>


                //배우페이지 관련 이동
                <Route path={"/actor/list"} element={<ActorList/>}/>
                <Route path={"/actor/detail/:actorProfileId"} element={<ActorDetail/>}/>
                <Route path={"/actor/write"} element={<ActorWrite/>}/>
                <Route path={"/actor/delete"} element={<ActorDelete/>}/>

                //오디션페이지 관련 이동
                <Route path={"/audition"} element={<AuditionPage/>}/>
                <Route path={"/audition/meeting"} element={<AuditionMeeting/>}/>

                //제작사페이지 관련 이동
                <Route path={"/maker/list"} element={<MakerList/>}/>
                <Route path={"/maker/detail"} element={<MakerDetail/>}/>
                <Route path={"/maker/write"} element={<MakerWrite/>}/>

                //모아보기페이지 관련 이동
                <Route path={"/collection"} element={<Collection/>}/>

                //로그인 관련 이동
                <Route path="/oauth2/redirect" element={<OAuth2RedirectHandler/>}/>

            </Routes>
        </Layout>
    );
}

export default App;
