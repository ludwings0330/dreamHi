import React, { useState, useEffect } from 'react';

import {
  MDBCard,
  MDBCardImage,
  MDBCardBody,
  MDBCardTitle,
  MDBCardText,
  MDBRow,
  MDBCol,
} from 'mdb-react-ui-kit';
import {
  Button,
  ButtonGroup
} from 'reactstrap';

function CastingDetail(props) {
  const [rSelected, setRSelected] = useState(null);
  const [posts, setPosts] = useState([]);
  const [page, setPage] = useState(1);

  const limit = 8
  const offset = (page - 1) * limit

  useEffect(() => {
    fetch("https://jsonplaceholder.typicode.com/posts")
      .then((res) => res.json())
      .then((data) => setPosts(data));
  }, []);

  return (
    <div className="casting-body">
      <MDBRow className='row-cols-1 row-cols-md-4 g-4'>
        {posts.slice(offset, offset + limit).map(({id, title, body}) => (
          <MDBCol key={id}>
            <MDBCard className='h-100'>
              <MDBCardImage
                src='https://mdbootstrap.com/img/new/standard/city/041.webp'
                alt={title}
                position='top'
              />
              <MDBCardBody className="bg-dark text-white text-center">
                <MDBCardTitle>{title}</MDBCardTitle>
                <MDBCardText>
                  {body}
                </MDBCardText>
              </MDBCardBody>
              <ButtonGroup>
                <Button
                  color="primary"
                  outline
                  onClick={() => setRSelected(`${id} pass`)}
                  active={rSelected === `${id} pass`}
                  className="casting-button"
                >
                <span className="casting-button-text">
                  합격
                </span>
                </Button>
                <Button
                  color="success"
                  outline
                  onClick={() => setRSelected(`${id} yet`)}
                  active={rSelected === `${id} yet`}
                  className="casting-button"
                >
                <span className="casting-button-text">
                  보류
                </span>
                </Button>
                <Button
                  color="danger"
                  outline
                  onClick={() => setRSelected(`${id} fail`)}
                  active={rSelected === `${id} fail`}
                  className="casting-button"
                >
                <span className="casting-button-text">
                  불합격
                </span>
                </Button>
              </ButtonGroup>
            </MDBCard>
          </MDBCol>
        ))}
        {/*<MDBCol>*/}
        {/*  <MDBCard className='h-100'>*/}
        {/*    <MDBCardImage*/}
        {/*      src='https://mdbootstrap.com/img/new/standard/city/041.webp'*/}
        {/*      alt='...'*/}
        {/*      position='top'*/}
        {/*    />*/}
        {/*    <MDBCardBody className="bg-dark text-white text-center">*/}
        {/*      <MDBCardTitle>Card title</MDBCardTitle>*/}
        {/*      <MDBCardText>*/}
        {/*        This is a longer card with supporting text below as a natural lead-in to additional content.*/}
        {/*        This content is a little bit longer.*/}
        {/*      </MDBCardText>*/}
        {/*    </MDBCardBody>*/}
        {/*    <ButtonGroup>*/}
        {/*      <Button*/}
        {/*        color="primary"*/}
        {/*        outline*/}
        {/*        onClick={() => setRSelected(1)}*/}
        {/*        active={rSelected === 1}*/}
        {/*        className="casting-button"*/}
        {/*      >*/}
        {/*        <span className="casting-button-text">*/}
        {/*          합격*/}
        {/*        </span>*/}
        {/*      </Button>*/}
        {/*      <Button*/}
        {/*        color="success"*/}
        {/*        outline*/}
        {/*        onClick={() => setRSelected(2)}*/}
        {/*        active={rSelected === 2}*/}
        {/*        className="casting-button"*/}
        {/*      >*/}
        {/*        <span className="casting-button-text">*/}
        {/*          보류*/}
        {/*        </span>*/}
        {/*      </Button>*/}
        {/*      <Button*/}
        {/*        color="danger"*/}
        {/*        outline*/}
        {/*        onClick={() => setRSelected(3)}*/}
        {/*        active={rSelected === 3}*/}
        {/*        className="casting-button"*/}
        {/*      >*/}
        {/*        <span className="casting-button-text">*/}
        {/*          불합격*/}
        {/*        </span>*/}
        {/*      </Button>*/}
        {/*    </ButtonGroup>*/}
        {/*  </MDBCard>*/}
        {/*</MDBCol>*/}
        {/*<MDBCol>*/}
        {/*  <MDBCard className='h-100'>*/}
        {/*    <MDBCardImage*/}
        {/*      src='https://mdbootstrap.com/img/new/standard/city/042.webp'*/}
        {/*      alt='...'*/}
        {/*      position='top'*/}
        {/*    />*/}
        {/*    <MDBCardBody>*/}
        {/*      <MDBCardTitle>Card title</MDBCardTitle>*/}
        {/*      <MDBCardText>This is a short card.</MDBCardText>*/}
        {/*    </MDBCardBody>*/}
        {/*  </MDBCard>*/}
        {/*</MDBCol>*/}
        {/*<MDBCol>*/}
        {/*  <MDBCard className='h-100'>*/}
        {/*    <MDBCardImage*/}
        {/*      src='https://mdbootstrap.com/img/new/standard/city/043.webp'*/}
        {/*      alt='...'*/}
        {/*      position='top'*/}
        {/*    />*/}
        {/*    <MDBCardBody>*/}
        {/*      <MDBCardTitle>Card title</MDBCardTitle>*/}
        {/*      <MDBCardText>*/}
        {/*        This is a longer card with supporting text below as a natural lead-in to additional content.*/}
        {/*      </MDBCardText>*/}
        {/*    </MDBCardBody>*/}
        {/*  </MDBCard>*/}
        {/*</MDBCol>*/}
        {/*<MDBCol>*/}
        {/*  <MDBCard className='h-100'>*/}
        {/*    <MDBCardImage*/}
        {/*      src='https://mdbootstrap.com/img/new/standard/city/044.webp'*/}
        {/*      alt='...'*/}
        {/*      position='top'*/}
        {/*    />*/}
        {/*    <MDBCardBody>*/}
        {/*      <MDBCardTitle>Card title</MDBCardTitle>*/}
        {/*      <MDBCardText>*/}
        {/*        This is a longer card with supporting text below as a natural lead-in to additional content.*/}
        {/*        This content is a little bit longer.*/}
        {/*      </MDBCardText>*/}
        {/*    </MDBCardBody>*/}
        {/*  </MDBCard>*/}
        {/*</MDBCol>*/}
        {/*<MDBCol>*/}
        {/*  <MDBCard className='h-100'>*/}
        {/*    <MDBCardImage*/}
        {/*      src='https://mdbootstrap.com/img/new/standard/city/044.webp'*/}
        {/*      alt='...'*/}
        {/*      position='top'*/}
        {/*    />*/}
        {/*    <MDBCardBody>*/}
        {/*      <MDBCardTitle>Card title</MDBCardTitle>*/}
        {/*      <MDBCardText>*/}
        {/*        This is a longer card with supporting text below as a natural lead-in to additional content.*/}
        {/*        This content is a little bit longer.*/}
        {/*      </MDBCardText>*/}
        {/*    </MDBCardBody>*/}
        {/*  </MDBCard>*/}
        {/*</MDBCol>*/}
        {/*<MDBCol>*/}
        {/*  <MDBCard className='h-100'>*/}
        {/*    <MDBCardImage*/}
        {/*      src='https://mdbootstrap.com/img/new/standard/city/044.webp'*/}
        {/*      alt='...'*/}
        {/*      position='top'*/}
        {/*    />*/}
        {/*    <MDBCardBody>*/}
        {/*      <MDBCardTitle>Card title</MDBCardTitle>*/}
        {/*      <MDBCardText>*/}
        {/*        This is a longer card with supporting text below as a natural lead-in to additional content.*/}
        {/*        This content is a little bit longer.*/}
        {/*      </MDBCardText>*/}
        {/*    </MDBCardBody>*/}
        {/*  </MDBCard>*/}
        {/*</MDBCol>*/}
      </MDBRow>
    </div>
  );
}

export default CastingDetail;