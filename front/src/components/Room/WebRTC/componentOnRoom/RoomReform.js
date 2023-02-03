import React, { Component } from "react";
import swal from "sweetalert";

import { FetchUrl } from "../../../../store/communication";
import ErrorCode from "../../../../Error/ErrorCode";
import { getCookie } from "../../../../Cookie";

import "./RoomReform.css";
import "./RoomReform.css";

export class RoomReform extends Component {
  constructor(props) {
    super(props);
    this.state = {
      starDistroyed: true,
      imgeRef: "",
      fileImage: "",
    };
  }

  handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    this.setState((values) => ({ ...values, [name]: value }));
  };

  handleSubmit = (event) => {
    event.preventDefault();
    const formData = new FormData();
    let outputs = {
      roomName: this.state.roomName,
      mode: this.state.mode, //MODE1, MODE2, MODE3
      pwd:
        this.state.roomPwd === null || this.state.roomPwd === ""
          ? null
          : this.state.roomPwd,
      roomDescription: this.state.description,
      roomCategory: this.state.categoryName,
      roomMemberMaxNo: this.state.num,
      endAt: this.state.endTime,
    };
    formData.append(
      "roomUpdateDTO",
      new Blob([JSON.stringify(outputs)], { type: "application/json" })
    );

    if (
      this.state.imgeRef !== null &&
      this.state.imgeRef !== "" &&
      this.state.imgeRef.current.files[0] !== undefined
    ) {
      formData.append("images", this.state.imgeRef.current.files[0]);
    }
    fetch(
      FetchUrl._currentValue +
        "/rooms/" +
        window.location.pathname.split("/")[2].substring(0) +
        "/update",
      {
        method: "POST",
        body: formData,
        headers: { accessToken: getCookie("accessToken") },
      }
    )
      .then((response) => {
        if (response.ok) {
          return response.json(); //ok떨어지면 바로 종료.
        } else {
          response.json().then((errorResult) => {
            ErrorCode(errorResult);
            let errorMessage = "리스폰스오류입니다";
            throw new Error(errorMessage);
          });
        }
      })
      .then((result) => {
        if (result != null) {
          swal("방정보가 수정되었습니다");
          this.exitModal();
        }
      })
      .catch((err) => {
        swal("에러가 발생하였습니다.");
      });
  };

  saveFileImage = (event) => {
    this.setState({
      fileImage: URL.createObjectURL(event.target.files[0]),
    });
  };

  //시작시 한번만 실행
  getRoomDataSetting() {
    fetch(
      FetchUrl._currentValue +
        "/rooms/" +
        window.location.pathname.split("/")[2].substring(0) +
        "/settings",
      {
        headers: {
          accessToken: getCookie("accessToken"),
        },
      }
    )
      .then((response) => {
        if (response.ok) {
          return response.json(); //ok떨어지면 바로 종료.
        } else {
          response.json().then((errorResult) => {
            let errorMessage = "리스폰스오류입니다";
            throw new Error(errorMessage);
          });
        }
      })
      .then((result) => {
        if (result != null) {
          let datas = result.responseData.room;
          this.setState(datas);
        }
      })
      .catch((err) => {
        //console.log("에러체크입니다.");
      });

    this.setState({ starDistroyed: null });
  }

  exitModal() {
    this.setState({ starDistroyed: true });
    this.props.close();
  }

  render() {
    // 열기, 닫기, 모달 헤더 텍스트를 부모로부터 받아옴
    const { open, close, header } = this.props;
    if (
      this.state.starDistroyed !== null &&
      this.state.starDistroyed === true
    ) {
      this.getRoomDataSetting();
    }
    return (
      <div className={open ? "openModal modal" : "modal"}>
        {open ? (
          <section>
            <header>
              {header}
              <button className="close" onClick={close}>
                &times;닫기
              </button>
            </header>
            <main>
              <form onSubmit={this.handleSubmit}>
                {/*form과 input의 name, type 수정시 연락부탁드립니다. 그외 구조나 id는 편하신대로 수정하셔도 됩니다. input추가시에는 말해주시면 감사하겠습니다.*/}
                <div className="form-frame">
                  <div className="room-imageReform">
                    <img
                      alt="sample"
                      src={
                        this.state.fileImage !== "" &&
                        this.state.fileImage !== null
                          ? this.state.fileImage
                          : this.state.img
                      }
                      style={{
                        position: "absolute",
                        marginTop: "55px",
                        width: "150px",
                        height: "150px",
                        borderRadius: "50%",
                        pointerEvents: "none",
                      }}
                      className="room-image__uploadReformFile"
                    />
                    {/*룸 이미지, 좌측부분 */}
                    <input
                      type="file"
                      name="roomImage"
                      accept="image/*"
                      onChange={this.saveFileImage}
                      className="room-image__uploadReform"
                      ref={this.state.imgeRef}
                    />
                    <div className="room-image__messageReform">
                      미팅룸 사진을 올리세요
                    </div>
                  </div>
                  <div className="room-inforReform">
                    {/*우측부분*/}

                    <div className="input-typeReform">
                      <div className="lineReform">
                        <div className="lineReformLeftDescript">방이름</div>
                        <input
                          type="text"
                          name="roomName"
                          value={this.state.roomName || ""}
                          onChange={this.handleChange}
                          placeholder="미팅룸 이름을 적으세요"
                        />
                      </div>
                      <div className="lineReform">
                        <div className="lineReformLeftDescriptLine2">설명</div>
                        <input
                          type="text"
                          name="description"
                          value={this.state.description || ""}
                          onChange={this.handleChange}
                          placeholder="간단한 설명을 적으세요"
                        />
                      </div>
                      <div className="lineReform">
                        {/* <div className="lineReformLeftDescriptLine3">
                          인원수
                        </div> */}
                        <label>
                          인원수
                          <input
                            type="number"
                            name="num"
                            value={this.state.num ? this.state.num : ""}
                            onChange={this.handleChange}
                            accept="number"
                            min="1"
                            max="25"
                            placeholder="인원수를 선택하세요(1~25)"
                          />
                        </label>
                        {/* <div className='lineReformDescriptLine4'>목표</div> */}
                        <label>
                          카테고리
                          <select
                            name="categoryName"
                            value={this.state.categoryName || ""}
                            onChange={this.handleChange}
                          >
                            <option value="" disabled>
                              카테고리를 선택하세요
                            </option>
                            <option value="공무원">공무원</option>
                            <option value="취업">취업</option>
                            <option value="수능">수능</option>
                            <option value="자격증">자격증</option>
                            <option value="기타">기타</option>
                          </select>
                        </label>
                      </div>
                      <div className="lineReform">
                        <span>종료기간</span>
                        <input
                          type="datetime-local"
                          name="endTime"
                          max="2030-12-31T23:59"
                          value={this.state.endTime || ""}
                          onChange={this.handleChange}
                          className="endTime"
                        />

                        <span>비공개</span>
                        <label className="switch">
                          <input
                            type="checkbox"
                            name="secret"
                            value={this.state.isChecked}
                            onChange={this.handleChangeCheck}
                          />
                          <span className="slider round"></span>
                        </label>
                        {/*checkbox이외의 방법으로 구현예정시 알려주세요.*/}
                        <input
                          type="password"
                          name="roomPwd"
                          value={this.state.roomPwd || ""}
                          onChange={this.handleChange}
                          maxLength="4"
                          minLength="0"
                          placeholder="비밀번호 4자리"
                        />
                      </div>
                    </div>

                    <div className="input-rules">
                      {/*규칙입니다. 현재 진행파트아닙니다. */}
                      <div className="rules-title">📝 규칙</div>
                      <div className="rules-box-reform">
                        <label>
                          <input
                            type="radio"
                            name="mode"
                            value="MODE1"
                            checked={
                              this.state.mode === "MODE1" ? "checked" : ""
                            }
                            onChange={this.handleChange}
                          />
                          감시없음
                        </label>
                        <label>
                          <input
                            type="radio"
                            name="mode"
                            value="MODE3"
                            checked={
                              this.state.mode === "MODE3" ? "checked" : ""
                            }
                            onChange={this.handleChange}
                          />
                          스마트폰감지
                        </label>
                        <label>
                          <input
                            type="radio"
                            name="mode"
                            value="MODE2"
                            checked={
                              this.state.mode === "MODE2" ? "checked" : ""
                            }
                            onChange={this.handleChange}
                          />
                          졸음감지
                        </label>
                        <label>
                          <input
                            type="radio"
                            name="mode"
                            value="MODE4"
                            checked={
                              this.state.mode === "MODE4" ? "checked" : ""
                            }
                            onChange={this.handleChange}
                          />
                          자리이탈 감지
                        </label>
                      </div>
                    </div>
                    <button type="submit">수정하기</button>
                  </div>
                </div>
              </form>
            </main>
          </section>
        ) : null}
      </div>
    );
  }
}
