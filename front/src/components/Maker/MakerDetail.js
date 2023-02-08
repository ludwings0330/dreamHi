import React, {useEffect, useState} from 'react';
import { useParams } from 'react-router-dom'
import axios from 'axios'

//import component
import MakerIntroduce from './Info/MakerIntroduce';
import MakerFilmo from './Filmo/MakerFilmo';
import MakerPeopleList from './MakerPeopleList/MakerPeopleList';
import MakerAnnouncementList from './AnnouncementList/MakerAnnouncementList';

const MakerDetail = () => {
    const { makerProfileId } = useParams();
    console.log(makerProfileId, '잘나옵니까???????????')
    const token = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAzIiwiYXV0aCI6IlJPTEVfVVNFUiIsImVtYWlsIjoiZGRmOTk4QGdtYWlsLmNvbSIsImV4cCI6MTY3ODIzNDgwMX0.B-xblykNgvy8DSacYxAUzQCxEkXxqdEi8yXJaKlm3p8Y96rxR0wkvTaEUU_0e-jLqXSXezDDLi5jSA9Imf_A1g';

    const [makerList, setMakerList] = useState([]);

    // api 요청 보내서 제작사 목록 확보
    useEffect(() => {
        axios.get('http://i8a702.p.ssafy.io:8085/api/producers',
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            })
            .then((res) => {
                setMakerList(res.data.result.content)
            })
            .catch((error) => {
                console.log('실패실패ㅠㅠ');
                console.log(error);
            });

    }, [setMakerList]);

    console.log(makerList, '제작사 목록');
    
  return (
    <>
      <MakerIntroduce />
      <MakerFilmo />
      <MakerPeopleList />
      <MakerAnnouncementList />
    </>
  );
};

export default MakerDetail;