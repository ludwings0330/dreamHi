import React, { useEffect, useState } from 'react';
import { booksSelector, checkTimeState } from 'recoil/book/bookStore';
import { useRecoilState } from 'recoil';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Radio from '@mui/material/Radio';
import RadioGroup, { useRadioGroup } from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';

export default function ActorTimeTable() {
  const [books, setBooks] = useRecoilState(booksSelector());
  const [checkTime, setCheckTime] = useRecoilState(checkTimeState);

  const handleCheck = (e) => {
    setCheckTime(e.target.value);
  };

  useEffect(() => {
    console.log(books);
    console.log(checkTime);
  }, [checkTime]);
  return (
    <Box sx={{ mt: 3, mb: 3 }}>
      <Grid container spacing={{ xs: 2 }} columns={{ xs: 8 }}>
        {books.map((book, index) => (
          <Grid item xs={2} sm={4} md={4} key={index}>
            {/* <TimeDetail book={book} /> */}
            <Box
              sx={{
                // display: book.isBook ? "none" : "block",
                boxShadow: 3,
                borderRadius: 5,
                // fontWeight: 'bold',
                bgcolor: book.isBook || book.id == checkTime ? '#051094' : '#52B2BF',
                // fontSize: 18,
                // color: "#FFFFFF",
                opacity: book.isBook ? [1, 1, 0.1] : [1, 1, 0.8],
              }}
              textAlign="center"
            >
              <FormControlLabel
                label={book.time}
                control={
                  <Radio
                    checked={checkTime === book.id}
                    value={book.id}
                    disabled={book.isBook}
                    onChange={handleCheck}
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
}
