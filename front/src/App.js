import React from "react";
import { Outlet, Route, Routes } from "react-router-dom";
import AppHeader from './components/Common/AppHeader';
import AppFooter from './components/Common/AppFooter';
import AppMain from './pages/AppMain';
import AppActor from './pages/AppActor';
import AppPost from './pages/AppPost';



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
          <Route path="/post" element={<AppPost />} />

        {/*</Route>*/}
      </Routes>
    </div>
  );
}

export default App;
