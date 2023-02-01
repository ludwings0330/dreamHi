import React from "react";
import {  Route, Routes } from "react-router-dom";
import MainPage from './pages/MainPage';
import ActorPage from './pages/ActorPage';
import MakerPage from './pages/MakerPage';
import LoginPage from './pages/Login/LoginPage';
import ActorDetail from './components/Actor/ActorDetail';
import ActorWrite from './components/Actor/ActorWrite';
import ActorList from './components/Actor/ActorList';





function App() {
  return (
    <div className="app">

      <Routes>
          //메인페이지 관련 이동
          <Route index element={<MainPage />} />
          <Route path="/actor" element={<ActorPage />} />
          <Route path="/maker" element={<MakerPage />} />
          <Route path="/login" element={<LoginPage />} />


        //배우페이지 관련 이동
        <Route path={"actor/list"} element={<ActorList />} />
        <Route path={"/actor/detail"} element={<ActorDetail />} />
        <Route path={"/actor/write"} element={<ActorWrite />} />

      </Routes>

    </div>
  );
}

export default App;
