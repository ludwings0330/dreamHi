import React from "react";
import { Outlet, Route, Routes } from "react-router-dom";
import AppHeader from './components/Common/AppHeader';
import AppFooter from './components/Common/AppFooter';
import AppMain from './pages/AppMain';
import AppActor from './pages/AppActor';
import AnnouncementListPage from './pages/AnnouncementListPage';
import AnnouncementWritePage from './pages/AnnouncementWritePage';
import AnnouncementDetailPage from './pages/AnnouncementDetailPage';



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
          <Route path="/announcementlist" element={<AnnouncementListPage />} />
          <Route path="/announcementwrite" element={<AnnouncementWritePage />} />
          <Route path="/announcementdetail" element={<AnnouncementDetailPage />} />



        {/*</Route>*/}
      </Routes>
    </div>
  );
}

export default App;
