import React, { useState, useEffect } from 'react';
import { OpenVidu } from 'openvidu-browser';
import axios from 'axios';
import UserVideoComponent from './UserVideoComponent';
import { useRecoilState } from 'recoil';

const APPLICATION_SERVER_URL = 'https://dreamhi.p-e.kr/';

const AuditionMeeting = (props) => {
  const [state, setState] = useState({
    mySessionId: 'SessionA',
    myUserName: 'Participant' + Math.floor(Math.random() * 100),
    session: undefined,
    mainStreamManager: undefined, // Main video of the page. Will be the 'publisher' or one of the 'subscribers'
    publisher: undefined,
    subscribers: [],
  });

  let OV = null;

  useEffect(() => {
    window.addEventListener('beforeunload', onbeforeunload);
    return () => {
      window.removeEventListener('beforeunload', onbeforeunload);
    };
  }, [state]);

  const onbeforeunload = (event) => {
    leaveSession();
  };

  const handleChangeSessionId = (e) => {
    setState((prev) => {
      return {
        ...prev,
        mySessionId: e.target.value,
      };
    });
    console.log('handlechangeSessionId');
    console.log(state);
  };

  const handleChangeUserName = (e) => {
    console.log('handleChangeUserName');
    setState((prev) => {
      return {
        ...prev,
        myUserName: e.target.value,
      };
    });

    console.log(state.myUserName);
  };

  const handleMainVideoStream = (stream) => {
    if (state.mainStreamManager !== stream) {
      setState((prev) => {
        return {
          ...prev,
          mainStreamManager: stream,
        };
      });
    }
  };

  const deleteSubscriber = (streamManager) => {
    let subscribers = state.subscribers;
    let index = subscribers.indexOf(streamManager, 0);
    if (index > -1) {
      subscribers.splice(index, 1);
      setState((prev) => {
        return {
          ...prev,
          subscribers: subscribers,
        };
      });
    }
  };

  const joinSession = () => {
    OV = new OpenVidu();
    console.log(OV);
    let mySession = OV.initSession();
    setState((prev) => {
      return {
        ...prev,
        session: mySession,
      };
    });

    console.log(mySession);
    mySession.on('streamCreated', (event) => {
      let subscriber = mySession.subscribe(event.stream, undefined);
      let subscribers = state.subscribers;
      subscribers.push(subscriber);

      setState((prev) => {
        return {
          ...prev,
          subscribers: subscribers,
        };
      });
    });

    mySession.on('streamDestroyed', (event) => {
      deleteSubscriber(event.stream.streamManager);
    });

    mySession.on('exception', (exception) => {
      console.warn(exception);
    });

    getToken().then((token) => {
      mySession
        .connect(token, { clientDate: state.myUserName })
        .then(async () => {
          let publisher = await OV.initPublisherAsync(undefined, {
            audioSource: undefined, // The source of audio. If undefined default microphone
            videoSource: undefined, // The source of video. If undefined default webcam
            publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
            publishVideo: true, // Whether you want to start publishing with your video enabled or not
            resolution: '640x480', // The resolution of your video
            frameRate: 30, // The frame rate of your video
            insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
            mirror: false, // Whether to mirror your local video or not
          });

          mySession.publish(publisher);

          let devices = await OV.getDevices();
          let videoDevices = devices.filter((device) => device.kind === 'videoinput');
          let currentVideoDeviceId = publisher.stream
            .getMediaStream()
            .getVideoTracks()[0]
            .getSettings().deviceId;
          let currentVideoDevice = videoDevices.find(
            (device) => device.deviceId === currentVideoDeviceId,
          );

          setState((prev) => {
            return {
              ...prev,
              currentVideoDevice: currentVideoDevice,
              mainStreamManager: publisher,
              publisher: publisher,
            };
          });
        })
        .catch((error) => {
          console.log('There was an error connecting to the session:', error.code, error.message);
        });
    });
  };

  const leaveSession = () => {
    const mySession = state.session;

    if (mySession) {
      mySession.disconnect();
    }

    OV = null;
    setState((prev) => {
      return {
        ...prev,
        session: undefined,
        subscribers: [],
        mySessionId: 'SessionA',
        myUserName: 'Participant' + Math.floor(Math.random() * 100),
        mainStreamManager: undefined,
        publisher: undefined,
      };
    });
  };

  const switchCamera = async () => {
    try {
      const devices = await OV.getDevices();
      let videoDevices = devices.filter((device) => device.kind === 'videoinput');

      if (videoDevices && videoDevices.length > 1) {
        let newVideoDevice = videoDevices.filter(
          (device) => device.deviceId !== state.currentVideoDevice.deviceId,
        );

        if (newVideoDevice.length > 0) {
          // Creating a new publisher with specific videoSource
          // In mobile devices the default and first camera is the front one
          let newPublisher = OV.initPublisher(undefined, {
            videoSource: newVideoDevice[0].deviceId,
            publishAudio: true,
            publishVideo: true,
            mirror: true,
          });

          //newPublisher.once("accessAllowed", () => {
          await state.session.unpublish(state.mainStreamManager);

          await state.session.publish(newPublisher);
          setState((prev) => {
            return {
              ...prev,
              currentVideoDevice: newVideoDevice[0],
              mainStreamManager: newPublisher,
              publisher: newPublisher,
            };
          });
        }
      }
    } catch (e) {
      console.error(e);
    }
  };

  const getToken = async () => {
    const sessionId = await createSession(state.mySessionId);
    return await createToken(sessionId);
  };

  const createSession = async (sessionId) => {
    try {
      const response = await axios.post(
        APPLICATION_SERVER_URL + 'openvidu/api/sessions',
        { customSessionId: sessionId },
        {
          headers: {
            Authorization: `Basic ${btoa('OPENVIDUAPP:1q2w3e4r-_-')}`,
            'Content-Type': 'application/json',
          },
        },
      );

      return response.data.id; // The sessionId
    } catch (e) {
      if (e?.response.status === 409) {
        return sessionId;
      }
    }
  };

  const createToken = async (sessionId) => {
    const response = await axios.post(
      APPLICATION_SERVER_URL + 'openvidu/api/sessions/' + sessionId + '/connection',
      {},
      {
        headers: {
          Authorization: `Basic ${btoa('OPENVIDUAPP:1q2w3e4r-_-')}`,
          'Content-Type': 'application/json',
        },
      },
    );

    return response.data.token; // The token
  };

  return (
    <>
      <div>
        <h1>오디션!!! 오픈비두!!!</h1>
      </div>
      <div className="container">
        {state.session === undefined ? (
          <div id="join">
            <div id="img-div">
              <img src="resources/images/openvidu_grey_bg_transp_cropped.png" alt="OpenVidu logo" />
            </div>
            <div id="join-dialog" className="jumbotron vertical-center">
              <h1> Join a video session </h1>
              <form className="form-group" onSubmit={joinSession}>
                <p>
                  <label>Participant: </label>
                  <input
                    className="form-control"
                    type="text"
                    id="userName"
                    value={state.myUserName}
                    onChange={handleChangeUserName}
                    required
                  />
                </p>
                <p>
                  <label> Session: </label>
                  <input
                    className="form-control"
                    type="text"
                    id="sessionId"
                    value={state.mySessionId}
                    onChange={handleChangeSessionId}
                    required
                  />
                </p>
                <p className="text-center">
                  <input
                    className="btn btn-lg btn-success"
                    name="commit"
                    type="submit"
                    value="JOIN"
                  />
                </p>
              </form>
            </div>
          </div>
        ) : null}

        {state.session !== undefined ? (
          <div id="session">
            <div id="session-header">
              <h1 id="session-title">{state.mySessionId}</h1>
              <input
                className="btn btn-large btn-danger"
                type="button"
                id="buttonLeaveSession"
                onClick={leaveSession}
                value="Leave session"
              />
            </div>

            {state.mainStreamManager !== undefined ? (
              <div id="main-video" className="col-md-6">
                <UserVideoComponent streamManager={state.mainStreamManager} />
                <input
                  className="btn btn-large btn-success"
                  type="button"
                  id="buttonSwitchCamera"
                  onClick={switchCamera}
                  value="Switch Camera"
                />
              </div>
            ) : null}
            <div id="video-container" className="col-md-6">
              {state.publisher !== undefined ? (
                <div
                  className="stream-container col-md-6 col-xs-6"
                  onClick={() => handleMainVideoStream(state.publisher)}
                >
                  <UserVideoComponent streamManager={state.publisher} />
                </div>
              ) : null}
              {state.subscribers.map((sub, i) => (
                <div
                  key={i}
                  className="stream-container col-md-6 col-xs-6"
                  onClick={() => handleMainVideoStream(sub)}
                >
                  <UserVideoComponent streamManager={sub} />
                </div>
              ))}
            </div>
          </div>
        ) : null}
      </div>
    </>
  );
};

export default AuditionMeeting;
