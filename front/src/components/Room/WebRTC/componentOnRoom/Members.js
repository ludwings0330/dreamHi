import React from "react";
import { useContext, useState, useEffect } from "react";
import { getCookie } from "../../../../Cookie";
import { FetchUrl } from "../../../../store/communication";

import "./Members.css";

function Members() {
  const FETCH_URL = useContext(FetchUrl);
  const id = window.location.pathname.split("/")[2].substring(0);
  const url = `${FETCH_URL}/rooms/` + id + `/members`;

  const [mstudy, setStudy] = useState([]);

  //멤버 추방
  let leader = localStorage.getItem("L");
  const ban = (event, key) => {
    if (leader) {
    }
  };

  useEffect(() => {
    fetch(url, {
      headers: {
        accessToken: getCookie("accessToken"),
      },
    })
      .then((response) => {
        if (response.ok) {
          return response.json(); //ok떨어지면 바로 종료.
        } else {
          response.json().then((data) => {
            //console.log("ERROn_ResMem");
            let errorMessage = "";
            throw new Error(errorMessage);
          });
        }
      })
      .then((result) => {
        setStudy(result.responseData.logs);
      })
      .catch((err) => {
        //console.log("ERR_Member");
      });
  }, []);

  return (
    <div id="borders-members">
      <div id="member-list">
        {mstudy.map((name, memNo) => (
          <div
            key={memNo}
            className="member-comps"
            onClick={(event) => ban(event, memNo)}
          >
            <div className="clickDisable">
              <img
                src={mstudy[memNo].images}
                className="member-comps__images"
                alt="멤버사진"
              />
            </div>
            <div className="member-comps__images-infor clickDisable">
              <div className="member-list__nickname">
                {mstudy[memNo].nickName}
              </div>
              <div className="member-list__penalty">
                패널티 : {mstudy[memNo].penalty}
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Members;
