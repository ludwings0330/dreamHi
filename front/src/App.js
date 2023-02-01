import React from 'react';
import { Route, Routes } from 'react-router-dom';
import AppHeader from './components/Common/AppHeader';
import AppFooter from './components/Common/AppFooter';
import AppMain from './pages/AppMain';
import AppActor from './pages/AppActor';
import AnnouncementList from './components/Announcement/AnnouncementList';
import AnnouncementWrite from './components/Announcement/AnnouncementWrite';
import AnnouncementDetail from './components/Announcement/AnnouncementDetail';
import AnnouncementPage from './pages/AnnouncementPage';
import AppCasting from './pages/AppCasting';
import CastingDetail from './components/Casting/CastingDetail';



const Layout = () => {
  return(
    <div>
      <AppHeader />

      <AppFooter />

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




        {/*</Route>*/}
      </Routes>
    </div>
  );
}

export default App;