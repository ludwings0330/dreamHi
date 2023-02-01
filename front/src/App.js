import React from "react";
import { Outlet, Route, Routes } from "react-router-dom";
import MainHeader from './components/Common/MainHeader';
import MainFooter from './components/Common/MainFooter';
import MainPage from './pages/MainPage';
import ActorPage from './pages/ActorPage';
import AnnouncementListPage from './components/Announcement/AnnouncementListPage';
import AnnouncementWritePage from './components/Announcement/AnnouncementWritePage';
import AnnouncementDetailPage from './components/Announcement/AnnouncementDetailPage';
import MakerPage from './pages/MakerPage';



const Layout = () => {
  return(
    <div>
      <MainHeader />

      <MainFooter />

    </div>
  );
};


function App() {
  return (
    <div className="app">
      <Routes>
        {/*<Route path="/" element={<Layout />}>*/}
          <Route index element={<AppMain />} />
          <Route path="/actor" element={<AppActor />} />
          <Route path="/announcement" element={<AnnouncementPage/>} />
          <Route path="/announcement/list" element={<AnnouncementList />} />
          <Route path="/announcement/write" element={<AnnouncementWrite />} />
          <Route path="/announcement/:announcementId" element={<AnnouncementDetail />} />
          <Route path="/casting" element={<AppCasting />} />
          <Route path="/casting/detail" element={<CastingDetail />} />

          <Route index element={<MainPage />} />
          <Route path="/actor" element={<ActorPage />} />
          <Route path="/maker" element={<MakerPage />} />
          <Route path="/announcementlist" element={<AnnouncementListPage />} />
          <Route path="/announcementwrite" element={<AnnouncementWritePage />} />
          <Route path="/announcementdetail" element={<AnnouncementDetailPage />} />



        {/*</Route>*/}
      </Routes>
    </div>
  );
}

export default App;
