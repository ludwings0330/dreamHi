import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
// import css
import {
  MDBCard,
  MDBCardBody,
  MDBCardImage,
  MDBCardText,
  MDBCardTitle,
  MDBCol,
  MDBRow,
} from 'mdb-react-ui-kit';
import './ActorList.css';

// import components
import SearchBar from '../Common/CommonComponent/SearchBar';
import Button from '../Common/CommonComponent/Button';
import jwtApi from '../../util/JwtApi';
import Paging from '../Common/CommonComponent/Paging';

const ActorList = () => {
  const navigate = useNavigate();
  const [actorList, setActorList] = useState([]);
  const [pageable, setPageable] = useState();
  const [actorFilter, setActorFilter] = useState({
    name: '',
    height: '',
    age: '',
    gender: '',
    styles: [],
    isFollow: '',
    page: 0,
    size: 8,
  });
  // api 요청 보내서 배우 목록 확보
  useEffect(() => {
    jwtApi.get(`/api/actors`, { params: actorFilter }).then((response) => {
      setActorList(response.data.result.content);
      setPageable(response.data.result.pageable);
      console.log(response.data.result.pageable);
    });
  }, [actorFilter]);

  return (
    <div className={'actor-body'}>
      <SearchBar actorList={actorList} setActorList={setActorList} />
      <div id={'actor-list-main'}>
        <MDBRow className="row-cols-1 row-cols-md-4 g-4">
          {actorList.map((actor) => (
            <Link
              to={`/actor/detail/${actor.userId}`}
              key={actor.userId}
              style={{ textDecoration: 'none' }}
            >
              <MDBCol className="h-100">
                <MDBCard className="h-100">
                  <MDBCardImage
                    src={actor.pictureUrl}
                    alt={`${actor.name}'s picture`}
                    position="top"
                    height="200px"
                    object-fit="cover"
                  />
                  <MDBCardBody>
                    <MDBCardTitle className="card-text">{actor.title}</MDBCardTitle>
                    <MDBCardText>
                      <span className="card-info">
                        <span>이름 : {actor.name}</span>
                        <span>성별 : {actor.gender === 'MALE' ? '남자' : '여자'} </span>
                        <span>나이 : {actor.age}</span>
                        <span>키 : {actor.height}cm</span>
                        <span>스타일 :</span>
                        <span className="card-info-style">
                          {actor.styles.map((style, idx) => (
                            <span key={idx}>{style.description}</span>
                          ))}
                        </span>
                      </span>
                      {actor.styles.description}
                    </MDBCardText>
                  </MDBCardBody>
                </MDBCard>
              </MDBCol>
            </Link>
          ))}
        </MDBRow>
      </div>

      <Button title="글작성" onClick={() => navigate('/actor/write')} />

      <Paging contents={pageable} action={setActorFilter} />
    </div>
  );
};

export default ActorList;
