import React from 'react';
import SearchBar from '../Common/CommonComponent/SearchBar';
import PageBar from '../Common/CommonComponent/PageBar';
import {} from './MakerList.css'
import Button from '../Common/CommonComponent/Button';
import { Link, useNavigate } from 'react-router-dom';

const MakerList = () => {

  const navigate = useNavigate();

      return (
      <div>
        <SearchBar />
        <Link to={"/maker/detail"}>
          <div className="maker">
            <div className="maker_img"><img src="/img/elephant.png" className="actor_img"/></div>
            <h5 className="maker_title"> 제작사 소개</h5>
            <p className="maker_des"> 제작사 소개 요약</p>
        </div>
        </Link>

        <Button
          title="글작성"
          onClick={() => {
            navigate("/maker/write")
          }} />


        <div className={"page_bar"}>
          <PageBar />
        </div>
      </div>
      );
};

export default MakerList;