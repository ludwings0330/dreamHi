import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

//import CSS
import {
  MDBCard,
  MDBCardBody,
  MDBCardImage,
  MDBCardText,
  MDBCardTitle,
  MDBCol,
  MDBRow,
} from 'mdb-react-ui-kit';
import {} from './MakerList.css';

//import common
import SearchMakerBar from '../Common/CommonComponent/SearchMakerBar';
import Button from '../Common/CommonComponent/Button';
import jwtApi from '../../util/JwtApi';
import Paging from '../Common/CommonComponent/Paging';

const MakerList = () => {
  const navigate = useNavigate();
  const [MakerList, setMakerList] = useState([]);
  const [pageable, setPageable] = useState(null);
  const [makerFilter, setMakerFilter] = useState({
    page: 0,
  });
  // api 요청 보내서 제작사 목록 확보
  useEffect(() => {
    jwtApi
      .get(`/api/producers`, { params: makerFilter })
      .then((response) => {
        console.log(response.data.result);
        setMakerList(response.data.result.content);
        setPageable(response.data.result);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [makerFilter]);

  return (
    <>
      <SearchMakerBar />
      <div id={'maker-list-main'}>
        <MDBRow className="row-cols-1 row-cols-md-4 g-4">
          {MakerList.length > 0 &&
            MakerList.map((maker, idx) => (
              <Link to={`/maker/detail/${maker.id}`} key={idx}>
                <MDBCol key={idx} className="h-100">
                  <MDBCard className="h-100">
                    <MDBCardImage
                      src={maker.pictureUrl}
                      alt={`${maker.name}'s picture`}
                      className={'maker-list-img'}
                      position="top"
                      height="200px"
                      object-fit="cover"
                    />
                    <MDBCardBody>
                      <MDBCardTitle className={'maker-card-title'}>{maker.name}</MDBCardTitle>
                      <MDBCardText className={'maker-card-name'}>{maker.name}</MDBCardText>
                    </MDBCardBody>
                  </MDBCard>
                </MDBCol>
              </Link>
            ))}
        </MDBRow>
      </div>

      <div className={'maker-insert-button'}>
        <Button
          title="제작사 등록"
          onClick={() => {
            navigate('/maker/write');
          }}
        />
      </div>

      {pageable ? <Paging totalPages={pageable.totalPages} action={setMakerFilter} /> : null}
    </>
  );
};

export default MakerList;
