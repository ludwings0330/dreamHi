import React, { useState } from 'react';
import AnnouncementWriteItem from './AnnouncementWriteItem';
import Button from '../Common/CommonComponent/Button';
import { useNavigate } from 'react-router-dom';
import Layout from '../Common/Layout';
import axios from 'axios';


import { useRecoilState } from 'recoil';

function AnnouncementWrite(props) {
  const navigate = useNavigate();



  // const postClick = () => {
  //     axios.post('http://i8a702.p.ssafy.io:8085/api/producers',{sendData}
  //       )
  //       .then((res) => {
  //         alert('성공');
  //         console.log(res.data);
  //       })
  //       .catch((error) => {
  //         console.log('실패실패');
  //         console.log(error);
  //       });
  //   };
  //



    // const postClick = () => {
  //   axios({
  //     method: 'POST',
  //     url: 'http://i8a702.p.ssafy.io:8085/api/producers?name={sendData.name}',
  //     data: sendData,
  //     headers: {
  //       Authorization:
  //         `Bearer${token}`,
  //     },
  //   })
  //     .then((res) => {
  //       alert('성공');
  //       console.log(res.data);
  //     })
  //     .catch((error) => {
  //       console.log('실패실패');
  //       console.log(error);
  //     });
  // };

  // const AxiosPost = () => {
  //   const [data, setData] = useState([
  //     {
  //       title: 'title',
  //       producerId: 9,
  //       payment: '협의 후 결정',
  //       crankPeriod: '6개월',
  //       endDate: '2023-02-27 10:00:00',
  //       description: '공고설명~~~~',
  //       pictureUrl: 'https://file2.nocutnews.co.kr/newsroom/image/2022/10/01/202210011638156548_0.jpg',
  //       castings: [
  //         {
  //           name: '배역명',
  //           description: '배역설명',
  //           headCount: 1,
  //           minHeight: 130,
  //           maxHeight: 150,
  //           minAge: 5,
  //           maxAge: 10,
  //           gender: 'MALE',
  //           styles: [1, 2],
  //         },
  //       ],
  //     },
  //   ]);
  //   const postClick = () => {
  //     axios.post('https://jsonplaceholder.typicode.com/posts', data)
  //       .then();
  //   };
  // };





  return (
    <Layout>
      <h1>공고 작성</h1>
      <Button
        title="뒤로 가기"
        onClick={() => {
          navigate('/announcement');
        }}
      />
      {/*<button onClick={postClick}>Post</button>*/}
      <AnnouncementWriteItem />
    </Layout>
  );
}

export default AnnouncementWrite;