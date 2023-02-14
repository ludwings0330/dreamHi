import React, { useState } from 'react';
import './Paging.css';
// import Pagination from 'react-js-pagination';
import { Pagination } from '@mui/material';

const Paging = ({ contents, action }) => {
  const [page, setPage] = useState(1);
  const handlePageChange = (page) => {
    setPage(page);
    action((prev) => {
      return {
        ...prev,
        page: page - 1,
      };
    });
  };

  return (
    <Pagination
      count={10} // 전체 페이지
      showFirstButton
      showLastButton
      onChange={(event, value) => handlePageChange(value)}
    />
  );
};

export default Paging;
