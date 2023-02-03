import React from "react";
import { useState, useContext, useEffect } from "react";
import { Link } from "react-router-dom";
import { FetchUrl } from "../../store/communication";
import RoomItem from "./RoomItem";

import "./RoomRecruit.css";

import btnPlane from "../../img/Icons/btn-plane.png";
import down from "../../img/Icons/down.png";
import json from "../json/roomrecruit.json";
import ErrorCode from "../../Error/ErrorCode";

function RoomRecruit() {
  const FETCH_URL = useContext(FetchUrl);
  const [inputs, setInputs] = useState({
    category: "",
    roomSearch: "",
    keyword: "",
    secret: "",
    mode: "",
  });
  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setInputs((values) => ({ ...values, [name]: value }));
  };

  const [rooms, setRooms] = useState(json.responseData.rooms);
  const [roomPage, setRoomPage] = useState(1);
  //URL
  const url = `${FETCH_URL}/rooms`;
  useEffect(() => {
    setRoomPage(1);
    fetch(url)
      .then((response) => response.json())
      .then((result) => {
        if (result.code === 200) {
          setRooms(result["responseData"]["rooms"]);
        } else {
          ErrorCode(result);
        }
      })
      .catch((err) => {
        // console.log("ERROR");
      });
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();
    fetch(
      url +
        "?" +
        (inputs.category === "" || inputs.category === "all"
          ? ""
          : "category=" + inputs.category + "&") +
        (inputs.keyword === "" ? "" : "keyword=" + inputs.keyword)
    )
      .then((response) => response.json())
      .then((result) => {
        if (result.code === 200) {
          setRooms(result["responseData"]["rooms"]);
          setInputs({ keyword: "" });
        } else {
          ErrorCode(result);
        }
      })
      .catch((err) => {
        // console.log("ERROR");
      });
  };
  const ctgChange = (event) => {
    event.preventDefault();
    setRoomPage(1);
    const ARR = ["all", "공무원", "취업", "수능", "자격증", "코딩", "기타"];
    setInputs((values) => ({ ...values, category: ARR[event.target.value] }));
    fetch(
      url +
        "?" +
        (ARR[event.target.value] === "" || ARR[event.target.value] === "all"
          ? ""
          : "category=" + ARR[event.target.value] + "&") +
        (inputs.keyword === "" ? "" : "keyword=" + inputs.keyword) +
        (inputs.secret === "" ? "" : "secret=" + inputs.secret)
    )
      .then((response) => response.json())
      .then((result) => {
        if (result.code === 200) {
          setRooms(result["responseData"]["rooms"]);
        }
      })
      .catch((err) => {
        //console.log("ERROR");
      });
  };
  const addMore = (event) => {
    let page = parseInt(parseInt(roomPage) + 1);
    fetch(
      url +
        "?" +
        (inputs.category === "" || inputs.category === "all"
          ? ""
          : "category=" + inputs.category + "&") +
        "page=" +
        page +
        "&" +
        (inputs.keyword === "" ? "" : "keyword=" + inputs.keyword)
    )
      .then((response) => response.json())
      .then((result) => {
        if (result.code === 200) {
          setRooms([...rooms, ...result.responseData.rooms]);
        } else {
          ErrorCode(result);
        }
      })
      .catch((err) => {
        //console.log("ERROR");
      });
    setRoomPage(page);
  };

  return (
    <div id="open-room">
      {/* 공개룸 찾기 section */}
      <form onSubmit={handleSubmit}>
        <div className="open-room__search">
          <div className="search__info">
            <strong>공개룸 찾기</strong>
            <small>Search Open Room</small>
          </div>
          <div className="search__input">
            <input
              type="text"
              name="keyword"
              value={inputs.keyword || ""}
              onChange={handleChange}
              placeholder="찾는 공개룸을 입력하세요"
            />
            <button type="submit">
              <img src={btnPlane} alt="검색" />
            </button>
          </div>
        </div>
      </form>

      {/* 아래 공개룸 보여지는 페이지 */}
      <div className="open-room__module">
        <div className="module__header">
          <ul className="header__tags">
            <li
              className={
                inputs.category === "" ||
                inputs.category === "all" ||
                inputs.category === "all"
                  ? "active"
                  : ""
              }
              onClick={ctgChange}
              value={0}
            >
              All
            </li>
            <li
              className={inputs.category === "공무원" ? "active" : ""}
              onClick={ctgChange}
              value={1}
            >
              공무원
            </li>
            <li
              className={inputs.category === "취업" ? "active" : ""}
              onClick={ctgChange}
              value={2}
            >
              취업
            </li>
            <li
              className={inputs.category === "수능" ? "active" : ""}
              onClick={ctgChange}
              value={3}
            >
              수능
            </li>
            <li
              className={inputs.category === "자격증" ? "active" : ""}
              onClick={ctgChange}
              value={4}
            >
              자격증
            </li>
            <li
              className={inputs.category === "코딩" ? "active" : ""}
              onClick={ctgChange}
              value={5}
            >
              코딩
            </li>
            <li
              className={inputs.category === "기타" ? "active" : ""}
              onClick={ctgChange}
              value={6}
            >
              기타
            </li>
          </ul>
          <div className="header__right">
            <Link className="header__link" to="/RoomCreate">
              공개룸 만들기
            </Link>
          </div>
        </div>

        {/* 공개룸 컴포넌트 */}
        <ul className="rooms__whole">
          {rooms.map((room, index) => {
            return (
              <li key={index}>
                <RoomItem room={room} />
              </li>
            );
          })}
        </ul>
        <button type="button" id="more-btn" name="roompage" onClick={addMore}>
          <img src={down} alt="+" />
          더보기
        </button>
      </div>
    </div>
  );
}

export default RoomRecruit;
