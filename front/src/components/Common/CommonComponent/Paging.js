import React, { useState } from 'react';
import './Paging.css';
// import Pagination from 'react-js-pagination';
import { Pagination } from '@mui/material';

const Paging = ({ totalPages, action }) => {
  const handlePageChange = (page) => {
    action((prev) => {
      return {
        ...prev,
        page: page - 1,
      };
    });
  };

  return (
    <Pagination
      count={totalPages} // 전체 페이지
      showFirstButton
      showLastButton
      onChange={(event, value) => handlePageChange(value)}
    />
  );
};

export default Paging;
