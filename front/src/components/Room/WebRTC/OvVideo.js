import React, { Component } from "react";


//open-vidu 설정은 여기에
export default class OpenViduVideoComponent extends Component {
  constructor(props) {
    super(props);
    //createRef()로 비디오 구성 요소 가져옴
    this.videoRef = React.createRef();
  }

  //구성 요소 출력이 DOM으로 렌더링 된 후 메소드 한번 호출
  componentDidUpdate(props) {
    if (props && !!this.videoRef) {
      this.props.streamManager.addVideoElement(this.videoRef.current);
    }
  }

  componentDidMount() {
    if (this.props && !!this.videoRef) {
      this.props.streamManager.addVideoElement(this.videoRef.current);
    }
  }

  render() {
    return (
      
      <video
        autoPlay={true}
        id={"video-" + this.props.streamManager.stream.streamId}
        ref={this.videoRef}
      />
    );
  }
}
