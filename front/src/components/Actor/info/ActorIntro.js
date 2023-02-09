import { React, useRef, useEffect, useState } from 'react';


//자기소개 component
const ActorIntro = () => {

    const textRef = useRef();
    const [textAreaHeight, setTextAreaHeight] = useState("auto");
    const [parentHeight, setParentHeight] = useState("auto");
    const [intro, setIntro] = useState("");

    const parentStyle = {
        minHeight: parentHeight,
    };

    const textAreaStyle = {
        height: textAreaHeight,
    };

    // 댓글의 내용이 변할 때마다 textAreaRef의 높이를 조정해 준다.
    useEffect(() => {
        if (textRef && textRef.current) {
            setParentHeight(`${textRef.current.scrollHeight}px`);
            setTextAreaHeight(`${textRef.current.scrollHeight}px`);
        }
    }, []);

    // 댓글의 값이 변할 때 추가로 높이를 보정해 준다.
    const onChangeHandler = (event) => {
        setTextAreaHeight("auto");
        setParentHeight(`${textRef.current.scrollHeight}px`);
        setIntro(event.target.value);
    };

    const handleChangeIntro = (e) => {
        setIntro(e.target.value);
    }

    return (
        <div style={parentStyle}>
            <h4>자기소개</h4>
    <textarea
        className={"actor-intro"}
        placeholder="자기소개"
        value={intro}
        onChange={(e) => onChangeHandler(e)}
        ref={textRef}
        style={{
            height: textAreaHeight,
            overflow: "hidden",
        }}
        rows={3}
    />
        </div>
    );
};

export default ActorIntro;