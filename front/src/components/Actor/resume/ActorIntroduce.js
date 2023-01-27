import React from 'react';
import Form from 'react-bootstrap/Form';
import "../Actor.css";

const ActorIntroduce = () => {
  return (
    <div className={"actor-introduce"}>



      <Form>
        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
          <Form.Label>이름</Form.Label>
          <Form.Control type="name" placeholder="이름을 입력하세요" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicCheckbox">
          <Form.Label>성별</Form.Label>
          <Form.Check type="checkbox" label="여자" />
          <Form.Check type="checkbox" label="남자" />
        </Form.Group>
        

        <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
          <Form.Label>나이</Form.Label>
          <Form.Control as="textarea" rows={3} />
        </Form.Group>

        <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
          <Form.Label>키</Form.Label>
          <Form.Control as="textarea" rows={3} />
        </Form.Group>

        <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
          <Form.Label>나이</Form.Label>
          <Form.Control as="textarea" rows={3} />
        </Form.Group>
        
        <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
          <Form.Label>체형</Form.Label>
          <Form.Control as="textarea" rows={3} />
        </Form.Group>

        <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
          <Form.Label>연락처</Form.Label>
          <Form.Control as="textarea" rows={3} />
        </Form.Group>

        <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
          <Form.Label>이메일</Form.Label>
          <Form.Control as="textarea" rows={3} />
        </Form.Group>


      </Form>
    </div>
  );
};

export default ActorIntroduce;