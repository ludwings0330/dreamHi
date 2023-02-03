import React, { Component } from "react";
import OpenViduVideoComponent from "./OvVideo";
import "./UserVideo.css";

//해당 컴포넌트는 StreamManager를 관리함
//사용자 지정정보(닉네임)을 표시하고 클릭 이벤트를 처리함
//
export default class UserVideoComponent extends Component {
  getNicknameTag() {
    // Gets the nickName of the user
    return JSON.parse(this.props.streamManager.stream.connection.data)
      .clientData;
  }

  render() {
    return (
      <>
        {this.props.streamManager !== undefined ? (
          <div className="streamcomponent">
            <OpenViduVideoComponent streamManager={this.props.streamManager} />
            <p>{this.getNicknameTag()}</p>
          </div>
        ) : null}
      </>
    );
  }
}
