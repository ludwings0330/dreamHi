import React from "react";
import { useState, useContext, useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import { FetchUrl } from "../../store/communication";
import { getCookie } from "../../Cookie";

import "./RoomCreate.css";
import ErrorCode from "../../Error/ErrorCode";
import swal from "sweetalert";

function RoomCreate() {
  //방생성 요청 보내기
  const [inputs, setInputs] = useState({
    roomName: "",
    status: "MODE1", //MODE1, MODE2, MODE3
    roomPwd: "",
    description: "",
    categoryName: "", //TAG1, TAG2, TAG3
    num: 0,
    endTime: "",
    display: 1,
  });
  const navigate = useNavigate();
  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setInputs((values) => ({ ...values, [name]: value }));
  };

  //URL
  const FETCH_URL = useContext(FetchUrl);
  const url = `${FETCH_URL}/rooms`;

  const imgeRef = useRef();
  const handleSubmit = (event) => {
    event.preventDefault();
    // console.log(inputs);

    const formData = new FormData();
    formData.append("images", imgeRef.current.files[0]);
    formData.append(
      "postRoomReqDTO",
      new Blob([JSON.stringify(inputs)], { type: "application/json" })
    );

    fetch(url, {
      method: "POST",
      body: formData,
      headers: {
        accessToken: getCookie("accessToken"),
      },
    })
      .then((response) => response.json())
      .then((result) => {
        if (result.code === 200) {
          swal("방생성이 완료되었습니다", "", "success");
          navigate("/RoomDetail/" + result.responseData.roomId);
        } else {
          ErrorCode(result);
        }
      })
      .catch((err) => {
        //console.log("ERR");
      });
  };

  //비공개 공개 전환
  const [isChecked, setIsChecked] = useState(false);
  const handleChangeCheck = (event) => {
    setIsChecked((current) => !current);
    if (isChecked) setInputs((values) => ({ ...values, roomPwd: "" }));
  };

  const [fileImage, setFileImage] = useState("");
  const saveFileImage = (event) => {
    setFileImage(URL.createObjectURL(event.target.files[0]));
  };

  return (
    <div className="body-frame">
      <Link to="/RoomRecruit" className="back-to-recruit">
        &lt; 목록으로 돌아가기
      </Link>

      <form onSubmit={handleSubmit}>
        <div className="form-frame">
          <div className="room-image">
            {fileImage && (
              <img
                alt="sample"
                src={fileImage}
                style={{
                  position: "absolute",
                  marginTop: "55px",
                  width: "150px",
                  height: "150px",
                  borderRadius: "50%",
                  pointerEvents: "none",
                }}
              />
            )}
            {/*룸 이미지, 좌측부분 */}
            <input
              type="file"
              name="roomImage"
              accept="image/*"
              onChange={saveFileImage}
              className="room-image__upload"
              ref={imgeRef}
            />
            <div className="room-image__message">미팅룸 사진을 올리세요</div>
          </div>
          <div className="room-infor">
            {/*우측부분*/}

            <div className="input-type">
              <div className="line">
                <input
                  type="text"
                  name="roomName"
                  value={inputs.roomName || ""}
                  onChange={handleChange}
                  placeholder="미팅룸 이름을 적으세요"
                />
              </div>
              <div className="line">
                <input
                  type="text"
                  name="description"
                  value={inputs.description || ""}
                  onChange={handleChange}
                  placeholder="간단한 설명을 적으세요"
                />
              </div>
              <div className="line">
                <input
                  type="number"
                  name="num"
                  value={inputs.num ? inputs.num : ""}
                  onChange={handleChange}
                  accept="number"
                  min="1"
                  max="25"
                  placeholder="인원수를 선택하세요(1~25)"
                />
                <select
                  name="categoryName"
                  value={inputs.categoryName || ""}
                  onChange={handleChange}
                >
                  <option value="" disabled>
                    카테고리를 선택하세요
                  </option>
                  <option value="공무원">공무원</option>
                  <option value="취업">취업</option>
                  <option value="수능">수능</option>
                  <option value="자격증">자격증</option>
                  <option value="코딩">코딩</option>
                  <option value="기타">기타</option>
                </select>
              </div>
              <div className="line">
                <span>종료기간</span>
                <input
                  type="datetime-local"
                  name="endTime"
                  max="2030-12-31T23:59"
                  value={inputs.endTime || ""}
                  onChange={handleChange}
                  className="endTime"
                  required
                />

                <span>비공개</span>
                <label className="switch">
                  <input
                    type="checkbox"
                    name="secret"
                    value={isChecked}
                    onChange={handleChangeCheck}
                  />
                  <span className="slider round"></span>
                </label>
                {/*checkbox이외의 방법으로 구현예정시 알려주세요.*/}
                <input
                  type="password"
                  name="roomPwd"
                  value={inputs.roomPwd || ""}
                  onChange={handleChange}
                  disabled={!isChecked}
                  maxLength="4"
                  minLength="0"
                  placeholder="비밀번호 4자리"
                />
              </div>
            </div>

            <div className="input-rules">
              <div className="rules-title">📝 규칙</div>
              <div className="rules-box">
                <label>
                  <input
                    type="radio"
                    name="mode"
                    value="MODE1"
                    onChange={handleChange}
                  />
                  감시없음
                </label>
                <label>
                  <input
                    type="radio"
                    name="mode"
                    value="MODE3"
                    onChange={handleChange}
                  />
                  스마트폰감지
                </label>
                <label>
                  <input
                    type="radio"
                    name="mode"
                    value="MODE2"
                    onChange={handleChange}
                  />
                  졸음감지
                </label>
                <label>
                  <input
                    type="radio"
                    name="mode"
                    value="MODE4"
                    onChange={handleChange}
                  />
                  자리이탈 감지
                </label>
              </div>
            </div>
            <button type="submit">생성하기</button>
          </div>
        </div>
      </form>
    </div>
  );
}

export default RoomCreate;
