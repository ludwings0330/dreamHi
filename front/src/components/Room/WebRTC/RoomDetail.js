import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import React, { Component } from "react";
import UserVideoComponent from "./UserVideoComponent";
import ChatComponent from "./chat/ChatComponent";
import { FetchUrl } from "../../../store/communication";
import { Routes, Route, Link } from "react-router-dom";

import Members from "./componentOnRoom/Members";
import MyStudy from "./componentOnRoom/MyStudy";
import { getCookie } from "../../../Cookie";

import screen_off from "../../../img/Icons/screen_off.png";
import screen from "../../../img/Icons/screen.png";
import mic from "../../../img/Icons/mic.png";
import mic_mute from "../../../img/Icons/mic_mute.png";
import video from "../../../img/Icons/video.png";
import video_off from "../../../img/Icons/video_off.png";
import out from "../../../img/Icons/out.png";
import btn_plane from "../../../img/Icons/btn-plane.png";
import swal from "sweetalert";

import ErrorCode from "../../../Error/ErrorCode";
import { RoomReform } from "./componentOnRoom/RoomReform.js";
import "./RoomDetail.css";

//강제 리브세션=추방
//방장 전용 기능 구현.
const OPENVIDU_SERVER_URL = "https://watchme1.shop:4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

class RoomDetail extends Component {
  constructor(props) {
    super(props);
    
    
    //값이 변경될 때마다 HTML을 다시 렌더링하기 위한 설정
    this.state = {
      //방데이터
      mySessionId: "SessionA", //세션이름
      roomName: "",
      myUserName: "Participant" + Math.floor(Math.random() * 100), //내 닉네임.
      isRoomLeader: true, //방장인지 체크->방장전용 데이터 보임
      mode: null,
      newErrorDetected: false,

      //카메라 설정 데이터
      videoState: true, //보이도록
      audioState: true, //마이크 on
      screenShare: true, //화면공유 버튼,
      //채팅관련 설정 데이터
      chatDisplay: "none",

      //내 카메라 및 영상 배치 관련 데이터
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      latestPublisher: undefined,

      //전체 카메라 관련 데이터
      subscribers: [],
      isScreenShareNow: false,
      screenShareCameraNeeded: false,
      firstTimeToCreateSreenShare: true,
      fristCameraChange: true,

      modalOpen: false,
    };

    //사용자가 클릭할 때마다 joinSession 메소드 호출됨
    this.joinSession = this.joinSession.bind(this);
    this.getUserPermission = this.getUserPermission.bind(this);
    this.leaveSession = this.leaveSession.bind(this);
    this.videoHandlerOn = this.videoHandlerOn.bind(this);
    this.videoHandlerOff = this.videoHandlerOff.bind(this);
    this.audioHandlerOn = this.audioHandlerOn.bind(this);
    this.audioHandlerOff = this.audioHandlerOff.bind(this);
    this.shareScreen = this.shareScreen.bind(this);
    this.handleChangeSessionId = this.handleChangeSessionId.bind(this);
    this.handleChangeUserName = this.handleChangeUserName.bind(this);
    this.handleMainVideoStream = this.handleMainVideoStream.bind(this);
    this.onbeforeunload = this.onbeforeunload.bind(this);
    this.toggleChat = this.toggleChat.bind(this);
    this.checkNotification = this.checkNotification.bind(this);
    this.shareScreenCancle = this.shareScreenCancle.bind(this);
  }

  componentDidMount() {
    this.joinSession();
    window.addEventListener("beforeunload", this.onbeforeunload);
  }
  componentWillUnmount() {
    window.removeEventListener("beforeunload", this.onbeforeunload);
  }
  onbeforeunload(event) {
    this.leaveSession();
  }
  handleChangeSessionId(e) {
    this.setState({
      mySessionId: e.target.value,
    });
  }
  handleChangeUserName(e) {
    this.setState({
      myUserName: e.target.value,
    });
  }
  handleMainVideoStream(stream) {
    if (this.state.mainStreamManager !== stream) {
      this.setState({
        mainStreamManager: stream,
      });
    }
  }
  deleteSubscriber(streamManager) {
    let subscribers = this.state.subscribers;
    let index = subscribers.indexOf(streamManager, 0);
    if (index > -1) {
      subscribers.splice(index, 1);
      this.setState({
        subscribers: subscribers,
      });
    }
  }
  async getUserPermission() {}

  leaveSession() {
    //세션 탈출
    // --- 7) Leave the session by calling 'disconnect' method over the Session object ---
    clearInterval();

    const mySession = this.state.session;
    const FETCH_URL = FetchUrl._currentValue;
    const id = window.location.pathname.split("/")[2].substring(0);
    const url = `${FETCH_URL}/rooms/` + id + "/leave";

    fetch(url, {
      method: "POST",
      headers: { accessToken: getCookie("accessToken") },
    })
      .then((response) => {
        if (response.ok) {
          return response.json(); //ok떨어지면 바로 종료.
        } else {
          response.json().then((resPoseError) => {
            swal(resPoseError.message);
            let errorMessage = "오류로 방나가기 안됨";
            throw new Error(errorMessage);
          });
        }
      })
      .then((result) => {
        if (result != null) {
          if (result.code === 200) {

            swal("퇴장하셨습니다.", "", "info");
            setTimeout(() => {
              window.location.href = "../../";
            }, [2000]);
          } else {
            //console.log("오류가 발생하였습니다.");
            window.location.href = "../../";
          }
        }
      })
      .catch((err) => {
        //console.log("ERR여기 못나감");
      });

    // Empty all properties...
    this.OV = null;
    mySession.disconnect();
    this.setState({
      session: undefined,
      subscribers: undefined,
      mySessionId: undefined,
      myUserName: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
    });
  }

  async switchCamera() {
    //카메라 교환
    try {
      const devices = await this.OV.getDevices();
      var videoDevices = devices.filter(
        (device) => device.kind === "videoinput"
      );

      if (videoDevices && videoDevices.length > 1) {
        var newVideoDevice = videoDevices.filter(
          (device) => device.deviceId !== this.state.currentVideoDevice.deviceId
        );

        if (newVideoDevice.length > 0) {
          // Creating a new publisher with specific videoSource
          // In mobile devices the default and first camera is the front one
          var newPublisher = this.OV.initPublisher(undefined, {
            videoSource: newVideoDevice[0].deviceId,
            publishAudio: true,
            publishVideo: true,
            mirror: true,
          });

          //newPublisher.once("accessAllowed", () => {
          await this.state.session.unpublish(this.state.mainStreamManager);
          await this.state.session.publish(newPublisher);

          this.setState({
            currentVideoDevice: newVideoDevice,
            mainStreamManager: newPublisher,
            publisher: newPublisher,
          });
        }
      }
    } catch (e) {
      console.error(e);
    }
  }

  //화면 공유 기능
  async shareScreen() {
    const latestPublisher = this.state.publisher;
    var newPublisher = this.OV.initPublisher(undefined, {
      videoSource: "screen",
    });
    await this.state.session.unpublish(this.state.mainStreamManager);
    await this.state.session.publish(newPublisher);
    this.setState({
      mainStreamManager: latestPublisher,
      publisher: newPublisher,
      isScreenShareNow: true,
    });
  }
  async shareScreenCancle() {
    const latestPublisher = this.state.publisher;
    this.setState({
      publisher: this.state.mainStreamManager,
      mainStreamManager: latestPublisher,
      isScreenShareNow: false,
    });

    await this.state.session.unpublish(latestPublisher);
    await this.state.session.publish(this.state.publisher);
  }

  //방 기본설정들, 문제없이 진행됨.
  //비디오 키고 끄기관련
  videoHandlerOn() {
    this.setState({
      videoState: true,
    });
    this.state.publisher.publishVideo(true);
  }
  videoHandlerOff() {
    this.setState({
      videoState: false,
    });
    this.state.publisher.publishVideo(false);
  }
  //오디오 관련
  audioHandlerOn() {
    this.state.publisher.publishAudio(true);

    // Update the state with the new subscribers
    this.setState({
      audioState: true,
    });
  }
  audioHandlerOff() {
    this.state.publisher.publishAudio(false);

    this.setState({
      audioState: false,
    });
  }
  //채팅관련
  toggleChat(property) {
    //채팅 열고 닫기
    let display = property;

    if (display === undefined) {
      display = this.state.chatDisplay === "none" ? "block" : "none";
    }
    if (display === "block") {
      this.setState({ chatDisplay: display, messageReceived: false });
    } else {
      // console.log("chat", display);
      this.setState({ chatDisplay: display });
    }
  }
  checkNotification(event) {
    //채팅용
    this.setState({
      messageReceived: this.state.chatDisplay === "none",
    });
  }

  closeRoom() {
    //아직 구현안됨.
    const FETCH_URL = FetchUrl._currentValue;
    fetch(FETCH_URL, {
      method: "POST",
      headers: {
        accessToken: this.getCookie("accessToken"),
      },
    });

    this.closeSession();
    this.banALL();
  }
  closeSession() {}
  banALL() {}

  joinSession() {
    //방데이터 세팅을 위한 백과의 통신
    const FETCH_URL = FetchUrl._currentValue;
    const id = window.location.pathname.split("/")[2].substring(0);
    //통신용 개인 닉네임 확인
    let myNickName = localStorage.getItem("nickName");
    this.setState({
      myUserName: myNickName,
    });

    if (myNickName === null) {
      swal("로그인후 접근부탁드립니다.", "", "error");
      setTimeout(this.leaveSession(), 1000);
    }
    setInterval(() => {
      fetch(`${FETCH_URL}/rooms/` + id, {
        headers: {
          accessToken: getCookie("accessToken"),
        },
      })
        .then((response) => {
          if (response.ok) {
            return response.json(); //ok떨어지면 바로 종료.
          } else {
            clearInterval();
            response.json().then((responseDataError) => {
              ErrorCode(responseDataError);
              setTimeout(1000);
              setTimeout(this.leaveSession(), 5000);
              let errorMessage = "";
              throw new Error(errorMessage);
            });
          }
        })
        .then((result) => {
          if (result != null) {
            this.setState({
              roomName: result.responseData.room.name,
              isRoomLeader:
                result.responseData.room.leaderTrue === 0 ? false : true,
              screenShare:
                result.responseData.room.mode === "MODE1" ? false : true,
              mode: result.responseData.room.mode,
            });
            sessionStorage.setItem("roomName", result.responseData.room.name);
          }
        })
        .catch((err) => {
          ErrorCode(err);
        });
      if (this.state.mode !== "MODE1") {
        this.openTeli(id);
      }
    }, 5000);
    this.joinSessionSetOpenVidu(id);
  }
  async joinSessionSetOpenVidu(newSessionId) {
    this.OV = new OpenVidu();
    this.setState(
      {
        session: this.OV.initSession(),
      },
      () => {
        var mySession = this.state.session;
        // --- 3) Specify the actions when events take place in the session ---
        // On every new Stream received...
        mySession.on("streamCreated", (event) => {
          // Subscribe to the Stream to receive it. Second parameter is undefined
          // so OpenVidu doesn't create an HTML video by its own
          var subscriber = mySession.subscribe(event.stream, undefined);
          var subscribers = this.state.subscribers;
          subscribers.push(subscriber);
          this.setState({ subscribers: subscribers });
        });

        this.sessionStreamCheck();

        // --- 4) Connect to the session with a valid user token ---
        // 'getToken' method is simulating what your server-side should do.
        // 'token' parameter should be retrieved and returned by your own backend
        this.getToken(newSessionId).then((token) => {
          // First param is the token got from OpenVidu Server. Second param can be retrieved by every user on event
          // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
          mySession
            .connect(token, { clientData: this.state.myUserName })
            .then(async () => {
              //브라우저 비디오, 오디오 권한 설정
              try {
                var devices = await navigator.mediaDevices.getUserMedia({
                  video: true,
                  audio: true,
                });
              } catch (e) {
                swal(
                  "서비스 사용을 위해 카메라와 마이크 권한이 필요합니다",
                  "권한 허용 후 빨간색 방 나가기 버튼을 누르고 나갔다 들어와주세요",
                  "error"
                );
              }

              devices = await this.OV.getDevices(); //디바이스 없으면 가져옴
              var videoDevices = devices.filter(
                (device) => device.kind === "videoinput"
              );
              // --- 5) Get your own camera stream ---
              // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
              // element: we will manage it on our own) and with the desired properties
              let publisher = this.OV.initPublisher(undefined, {
                audioSource: undefined, // The source of audio. If undefined default microphone
                videoSource: videoDevices[0].deviceId, // The source of video. If undefined default webcam
                publishAudio: this.state.audioState, // Whether you want to start publishing with your audio unmuted or not
                publishVideo: this.state.videoState, // Whether you want to start publishing with your video enabled or not
                resolution: "640x480", // The resolution of your video
                frameRate: 30, // The frame rate of your video
                insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
                mirror: false, // Whether to mirror your local video or not
              });

              // --- 6) Publish your stream ---

              mySession.publish(publisher);
              // Set the main video in the page to display our webcam and store our Publisher
              this.setState({
                currentVideoDevice: videoDevices[0],
                mainStreamManager: publisher,
                publisher: publisher,
              });
            })
            .catch((error) => {
              // console.log("오픈비드 JoinSession에러입니다.");
              // console.log(
              //   "There was an error connecting to the session:",
              //   error.code,
              //   error.message
              // );
            });
        });
      }
    );
  }

  sessionStreamCheck() {
    var mySession = this.state.session;
    // 스트림 파괴될때마다 'subscribers' array에서 스트림 제거
    mySession.on("streamDestroyed", (event) => {
      this.deleteSubscriber(event.stream.streamManager);
    });
    //비정상적인 예외사항 발생시 경고날림
    mySession.on("exception", (exception) => {
      console.warn(exception);
    });
  }
  async openTeli(id) {
    //특정 기간마다 반복해서
    //포멧 만들기
    //JSON넣기
    const formData = new FormData();
    const json = {
      nickName: this.state.myUserName,
      roomId: id,
      mode: this.state.mode,
    };
    formData.append(
      "flaskDTO",
      new Blob([JSON.stringify(json)], { type: "application/json" }),
      "flaskDTO"
    );

    //이미지 넣기
    let imageCapture;
    try {
      const stream = await navigator.mediaDevices.getUserMedia({
        video: { pan: true, tilt: true, zoom: true },
      });
      const [track] = stream.getVideoTracks();
      imageCapture = new ImageCapture(track);
    } catch (err) {
      console.error(err);
    }
    const blob = await imageCapture.takePhoto();
    formData.append("img", blob, "img");

    this.viduSendFormatToVidu(formData);
    this.sessionStreamCheck();
  }
  viduSendFormatToVidu(formData) {
    fetch("https://watchme1.shop/flask/openCV", {
      method: "POST",
      body: formData,
    })
      .then((response) => {
        if (response.ok) {
          return response.json(); //ok떨어지면 바로 종료.
        } else {
          response.json().then((responseDataError) => {
            let errorMessage = "에러입니다.";
            this.ErrorCode(responseDataError);
            throw new Error(errorMessage);
          });
        }
      })
      .then((result) => {
        if (result != null) {
          if (result.code === 200) {
            // console.log("오류없음");
          } else if (result.code === 205) {
            this.errorFound();
            this.setState({
              newErrorDetected: !this.state.newErrorDetected,
            });
          } else if (result.code === 202) {
            this.ban();
          } else {
            this.ErrorCode(result);
          }
        }
      })
      .catch((err) => {
        //console.log("ERR여기임");
      });
  }

  errorFound() {
    if (this.state.mode === "MODE2") {
      swal("졸음이 감지되었습니다", "", "error");
    } else if (this.state.mode === "MODE3") {
      swal("스마트폰이 감지되었습니다", "", "error");
    } else if (this.state.mode === "MODE4") {
      swal("자리이탈이 감지되었습니다.", "", "error");
    } else {
      //console.log("알수없는에러");
    }
  }
  ban() {
    swal("벌점이 과다로 추방되었습니다", "", "error");
    clearInterval();

    const mySession = this.state.session;
    const FETCH_URL = FetchUrl._currentValue;
    const id = window.location.pathname.split("/")[2].substring(0);
    const url = `${FETCH_URL}/rooms/` + id + "/leave";

    fetch(url, {
      method: "POST",
      headers: { accessToken: getCookie("accessToken") },
    })
      .then((response) => {
        if (response.ok) {
          return response.json(); //ok떨어지면 바로 종료.
        } else {
          response.json().then((resPoseError) => {
            swal(resPoseError.message);
            let errorMessage = "오류로 방나가기 안됨";
            throw new Error(errorMessage);
          });
        }
      })
      .then((result) => {
        if (result != null) {
          if (result.code === 200) {

            swal("방에서 퇴장되셨습니다", "", "error");
            setTimeout(() => {
              window.location.href = "../../";
            }, [2000]);
          } else {
            swal("오류가 발생하였습니다", "", "error");
            setTimeout(() => {
              window.location.href = "../../";
            }, [2000]);
          }
        }
      })
      .catch((err) => {
        //console.log("ERR여기 못나감");
      });

    // Empty all properties...
    this.OV = null;
  

    try {
      setTimeout(() => {
      }, [1000]);
    } catch {
      swal("오류가 발생하였습니다.", "", "error");
      window.location.href = "../../";
    }
    mySession.disconnect();
    this.setState({
      session: undefined,
      subscribers: undefined,
      mySessionId: undefined,
      myUserName: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
    });
    mySession.disconnect();
  }

  

  openModal = () => {
    this.setState({ modalOpen: true });
  };
  closeModal = () => {
    this.setState({ modalOpen: false });
  };
  render() {
    var chatDisplay = { display: this.state.chatDisplay };
    return (
      <>
        <React.Fragment>
          <RoomReform
            open={this.state.modalOpen}
            close={this.closeModal}
            title="Create a chat room"
          >
            내용
          </RoomReform>
        </React.Fragment>

        {this.state.session === undefined ? null : (
          <div id="session">
            <div id="Main">
              <div id="session-header">
                <h1 id="session-title" className="headerTitle">
                  {this.state.roomName}
                </h1>
                <div id="headerButtons">
                  <div id="btnTotal">
                    {this.state.videoState && (
                      <img
                        src={video_off}
                        onClick={this.videoHandlerOff}
                        alt={"video-off"}
                      />
                    )}
                    {!this.state.videoState && (
                      <img
                        src={video}
                        onClick={this.videoHandlerOn}
                        alt={"video-on"}
                      />
                    )}
                    {this.state.audioState && (
                      <img
                        src={mic_mute}
                        onClick={this.audioHandlerOff}
                        alt={"audio-off"}
                      />
                    )}
                    {!this.state.audioState && (
                      <img
                        src={mic}
                        onClick={this.audioHandlerOn}
                        alt={"audio-on"}
                      />
                    )}
                    {this.state.screenShare && !this.state.isScreenShareNow && (
                      <img
                        src={screen}
                        onClick={this.shareScreen}
                        alt={"screenshare-on"}
                      />
                    )}
                    {this.state.screenShare && this.state.isScreenShareNow && (
                      <img
                        src={screen_off}
                        onClick={this.shareScreenCancle}
                        alt={"screenshare-off"}
                      />
                    )}
                  </div>
                  <div id="btnOut">
                    <img
                      src={out}
                      alt={"leave-room"}
                      id="buttonLeaveSession"
                      onClick={this.leaveSession}
                      title="방 나가기"
                    />
                  </div>
                </div>
              </div>
              <div id="video-container" className="subVideo">
                {/* 나 */}
                <div className="stream-container">
                  <UserVideoComponent streamManager={this.state.publisher} />
                </div>
                {/* 남 */}
                {this.state.subscribers.map((sub, i) => (
                  <div key={i} className="stream-container">
                    <UserVideoComponent streamManager={sub} />
                  </div>
                ))}
              </div>
            </div>
            <div id="Aside">
              <div id="chat-container" className="chatBoards">
                {this.state.publisher !== undefined &&
                  this.state.publisher.stream !== undefined && (
                    <div
                      className="OT_root OT_publisher custom-class"
                      style={chatDisplay}
                    >
                      <ChatComponent
                        user={this.state.publisher}
                        chatDisplay={this.state.chatDisplay}
                        close={this.toggleChat}
                        messageReceived={this.checkNotification}
                      />
                    </div>
                  )}
              </div>
              <div id="side-nav">
                <Link to="./">내 공부</Link>
                <Link to="./members">멤버</Link>
                {this.state.isRoomLeader && (
                  <div onClick={this.openModal}> 방 수정</div>
                )}
              </div>
              <div id="AsideMain">
                {
                  <div id="aside-board">
                    <Routes>
                      {/* <Route path="/" element={<MyStudy />} /> */}
                      <Route
                        path="/"
                        element={
                          <MyStudy
                            mode={this.state.mode}
                            newError={this.state.newErrorDetected}
                          />
                        }
                      />
                      {/* <MyStudy
                        mode={this.state.mode}
                        newError={this.state.newErrorDetected}
                      /> */}
                      <Route path="/members" element={<Members />} />
                    </Routes>
                  </div>
                }
              </div>
              <div id="button-bottom">
                <img
                  src={btn_plane}
                  alt={"chat-btn"}
                  id="toggleChat"
                  onClick={() => this.toggleChat()}
                />
              </div>
            </div>
          </div>
        )}
      </>
    );
  }

  createSession(sessionId) {
    return new Promise((resolve, reject) => {
      var data = JSON.stringify({ customSessionId: sessionId });
      axios
        .post(OPENVIDU_SERVER_URL + "/openvidu/api/sessions", data, {
          headers: {
            Authorization:
              "Basic " + btoa("OPENVIDUAPP:" + OPENVIDU_SERVER_SECRET),
            "Content-Type": "application/json",
          },
        })
        .then((response) => {;
          resolve(response.data.id);
        })
        .catch((response) => {
          var error = Object.assign({}, response);
          if (error?.response?.status === 409) {
            resolve(sessionId);
          } else {
            console.warn(
              "No connection to OpenVidu Server. This may be a certificate error at " +
                OPENVIDU_SERVER_URL
            );
            if (
              window.confirm(
                'No connection to OpenVidu Server. This may be a certificate error at "' +
                  OPENVIDU_SERVER_URL +
                  '"\n\nClick OK to navigate and accept it. ' +
                  'If no certificate warning is shown, then check that your OpenVidu Server is up and running at "' +
                  OPENVIDU_SERVER_URL +
                  '"'
              )
            ) {
              window.location.assign(
                OPENVIDU_SERVER_URL + "/accept-certificate"
              );
            }
          }
        });
    });
  }

  getToken(newSessionId) {
    return this.createSession(newSessionId).then((sessionId) =>
      this.createToken(sessionId)
    );
  }
  createToken(sessionId) {
    return new Promise((resolve, reject) => {
      var data = {};
      axios
        .post(
          OPENVIDU_SERVER_URL +
            "/openvidu/api/sessions/" +
            sessionId +
            "/connection",
          data,
          {
            headers: {
              Authorization:
                "Basic " + btoa("OPENVIDUAPP:" + OPENVIDU_SERVER_SECRET),
              "Content-Type": "application/json",
            },
          }
        )
        .then((response) => {
          resolve(response.data.token);
        })
        .catch((error) => {
          //console.log("생성실패");
          reject(error);
        });
    });
  }
}

export default RoomDetail;
