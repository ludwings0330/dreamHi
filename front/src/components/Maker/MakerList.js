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

//import common
import SearchBar from '../Common/CommonComponent/SearchBar';
import Button from '../Common/CommonComponent/Button';
import jwtApi from '../../util/JwtApi';
import Paging from "../Common/CommonComponent/Paging";

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
      .get(`/api/producers`, {params: makerFilter})
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
    <div>
      <SearchBar />
      <div>
        <MDBRow className="row-cols-1 row-cols-md-4 g-4">
          {MakerList.length > 0 &&
            MakerList.map((maker, idx) => (
              <Link to={`/maker/detail/${maker.id}`} key={idx}>
                <MDBCol key={idx} className="h-100">
                  <MDBCard className="h-100">
                    <MDBCardImage
                      src={maker.pictureUrl}
                      alt={`${maker.name}'s picture`}
                      position="top"
                      height="200px"
                      object-fit="cover"
                    />
                    <MDBCardBody>
                      <MDBCardTitle>{maker.name}</MDBCardTitle>
                      <MDBCardText>{maker.name}</MDBCardText>
                    </MDBCardBody>
                  </MDBCard>
                </MDBCol>
              </Link>
            ))}
        </MDBRow>
      </div>

      <Button
        title="글작성"
        onClick={() => {
          navigate('/maker/write');
        }}
      />

      {(pageable)?(
          <Paging totalPages={pageable.totalPages} action={setMakerFilter} />
      ):null}
    </div>
  );
};

export default MakerList;
