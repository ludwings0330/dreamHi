/* eslint-disable react/destructuring-assignment */
import styled from 'styled-components';
import OpenViduVideoComponent from './OvVideo';
import './UserVideo.css';
import React from 'react';

const StreamComponent = styled.div`
  display: flex;
  flex-direction: row;
`;

const Nickname = styled.div`
  text-align: center;
  position: absolute;
  width: auto;
  height: 20px;
  background-color: rgba(0, 0, 0, 0.5);
  font-weight: bold;
`;



const UserVideoComponent = (props) => {
  const getNicknameTag = () => {
    // Gets the nickName of the user
    return JSON.parse(props.streamManager.stream.connection.data).clientData;
  };

  return (
      <div>
        {props.streamManager !== undefined ? (
            <StreamComponent className="streamcomponent">
              <OpenViduVideoComponent streamManager={props.streamManager} />
              <Nickname>{getNicknameTag()}</Nickname>
            </StreamComponent>
        ) : null}
      </div>
  );
};

export default UserVideoComponent;

