import React from 'react';
import axios from 'axios';
import { API_BASE_URL } from '../../constants';

function AxiosTry(props) {
  const token =
    'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAwMDEiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZW1haWwiOiJkZGY5OThAZ21haWwuY29tIiwiZXhwIjoxNjc4MjU2MjEyfQ.gSBnEPdb7LPDgTMwi5fDDlEdYxgbdJ6hInbddudS9suerZhCPuHDV3P9C6ygWTacOvhfT9tS8i94LP1qSszc0w';

  const sendData = {
    title: 'title',
    producerId: 1001,
    payment: '협의 후 결정',
    crankPeriod: '6개월',
    endDate: '2023-02-27T10:00:00',
    description: '공고설명',
    pictureUrl: 'https://file2.nocutnews.co.kr/newsroom/image/2022/10/01/202210011638156548_0.jpg',
    castings: [
      {
        name: '배역명',
        description: '배역설명',
        headcount: 1,
        minHeight: 130,
        maxHeight: 150,
        minAge: 5,
        maxAge: 10,
        gender: 'MALE',
        styles: [1],
      },
    ],
  };

  const getClick = () => {
    axios
      .get(`${API_BASE_URL}/api/my`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        alert('성공');
        console.log(res.data);
      })
      .catch((error) => {
        console.log('실패실패ㅠㅠ');
        console.log(error);
      });
  };

  const postClick = () => {
    axios
      .post(`${API_BASE_URL}/api/announcements`, sendData, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        alert('성공');
        console.log(res.data);
      })
      .catch((error) => {
        console.log('실패실패ㅠㅠ');
        console.log(sendData);
        console.log(error);
      });
  };

  const postMakerClick = () => {
    axios
      .post(
        `${API_BASE_URL}/api/producers`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
          params: { name: '메가커피' },
        },
      )
      .then((res) => {
        alert('성공');
        console.log(12121212);
        console.log(res.data);
      })
      .catch((error) => {
        console.log('실패실패ㅠㅠ');
        console.log(error);
      });
  };

  return (
    <div>
      <button onClick={getClick}> getgetget </button>
      <button onClick={postClick}> postpostpost </button>
      <button onClick={postMakerClick}> maker </button>
    </div>
  );
}

export default AxiosTry;
