import React, { useEffect } from 'react';
import { useRecoilState, useRecoilValueLoadable } from 'recoil';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Radio from '@mui/material/Radio';
import FormControlLabel from '@mui/material/FormControlLabel';
import { booksSelector } from 'recoil/book/bookStore';
import { volunteerUserIdState } from '../../recoil/volunteer/volunteerStore';

export default function MakerTimeTable() {
  const books = useRecoilValueLoadable(booksSelector());
  const [checkVolunteerUserId, setVolunteerCheckUserId] = useRecoilState(volunteerUserIdState);

  const handleCheck = (e) => {
    setVolunteerCheckUserId(e.target.value);
  };

  switch (books.state) {
    case 'hasValue':
      console.log(books);
      return (
        <Box sx={{ mt: 3, mb: 3 }}>
          <Grid container spacing={{ xs: 2 }} columns={{ xs: 8 }}>
            {books.contents.map((book, index) => (
              <Grid item xs={2} sm={4} md={4} key={index}>
                <Box
                  sx={{
                    boxShadow: 3,
                    borderRadius: 5,
                    bgcolor:
                      !book.reserved || book.userId != checkVolunteerUserId ? '#52B2BF' : '#051094',
                    // bgcolor: book.userId === checkVolunteerUserId ? '#051094' : '#52B2BF',
                    opacity:
                      !book.reserved || book.userId === checkVolunteerUserId
                        ? [1, 1, 0.1]
                        : [1, 1, 0.8],
                  }}
                  textAlign="center"
                >
                  <FormControlLabel
                    label={`${book.startDateTime.match(/[0-9]+:[0-9]+/)}`}
                    control={
                      <Radio
                        checked={checkVolunteerUserId == book.userId}
                        value={book.userId}
                        disabled={!book.reserved}
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
    case 'loading':
      return <div>Loading...</div>;
    case 'hasError':
    default:
      return <div>Error...</div>;
  }
}
