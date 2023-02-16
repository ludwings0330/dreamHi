import React, { useState } from 'react';
import { booksSelector, checkTimeState } from 'recoil/book/bookStore';
import { useRecoilState, useRecoilValueLoadable } from 'recoil';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Radio from '@mui/material/Radio';
import FormControlLabel from '@mui/material/FormControlLabel';

export default function ActorTimeTable() {
  const books = useRecoilValueLoadable(booksSelector());
  const [checkId, setCheckId] = useState();
  const [checkTime, setCheckTime] = useRecoilState(checkTimeState);
  const handleCheck = (e) => {
    setCheckId(e.id);
    setCheckTime(e);
  };

  switch (books.state) {
    case 'hasValue':
      return (
        <Box sx={{ mt: 3, mb: 3 }}>
          <Grid container spacing={{ xs: 2 }} columns={{ xs: 8 }}>
            {books.contents.map((book, index) => (
              <Grid item xs={2} sm={4} md={4} key={index}>
                <Box
                  sx={{
                    boxShadow: 3,
                    borderRadius: 5,
                    bgcolor: book.id === checkId ? '#051094' : '#52B2BF',
                    opacity: book.reserved ? [1, 1, 0.1] : [1, 1, 0.8],
                  }}
                  textAlign="center"
                >
                  <FormControlLabel
                    label={`${book.startDateTime.match(/[0-9]+:[0-9]+/)}`}
                    control={
                      <Radio
                        checked={checkId === book.id}
                        value={book.id}
                        disabled={book.reserved}
                        onChange={() => handleCheck(book)}
                        sx={{
                          color: '#FFFFFF',
                        }}
                      />
                    }
                    sx={{
                      color: '#FFFFFF',
                      display: 'block',
                    }}
                  ></FormControlLabel>
                </Box>
              </Grid>
            ))}
          </Grid>
        </Box>
      );
    case 'loading':
      return <div>Loading...</div>;
    case 'hasError':
    default:
      return <div>Error...</div>;
  }
}
