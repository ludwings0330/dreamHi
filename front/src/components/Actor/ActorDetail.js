import React, {useEffect, useState} from 'react';
import { useParams } from 'react-router-dom'
import axios from 'axios'

import ActorIntroduce from './info/ActorIntroduce';
import ActorFilmo from './filmo/ActorFilmo';
import ActorPhoto from './photo/ActorPhoto';
import ActorVideo from './video/ActorVideo';

const ActorDetail = () => {
  const { actorProfileId } = useParams()
  console.log(actorProfileId, '잘나옵니까???????????')
  const token = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAzIiwiYXV0aCI6IlJPTEVfVVNFUiIsImVtYWlsIjoiZGRmOTk4QGdtYWlsLmNvbSIsImV4cCI6MTY3ODIzNDgwMX0.B-xblykNgvy8DSacYxAUzQCxEkXxqdEi8yXJaKlm3p8Y96rxR0wkvTaEUU_0e-jLqXSXezDDLi5jSA9Imf_A1g';

  const [actorList, setActorList] = useState([]);

  // api 요청 보내서 배우 목록 확보
  useEffect(() => {
    axios.get('http://i8a702.p.ssafy.io:8085/api/actors',
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setActorList(res.data.result.content)
      })
      .catch((error) => {
        console.log('실패실패ㅠㅠ');
        console.log(error);
      });

  }, [setActorList]);
  
  console.log(actorList, '배우모ㅗㅗㅗㅗㅗㅗㅗㅗㅗ록')

  return (
      <>
        <ActorIntroduce />
        <ActorFilmo />
        <ActorPhoto />
        <ActorVideo />
      </>
  );
};

export default ActorDetail;