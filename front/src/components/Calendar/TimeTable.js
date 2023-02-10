import React, {useEffect, useState} from "react";
import { booksSelector } from "recoil/book/bookStore";
import { useRecoilState } from "recoil";
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import { experimentalStyled as styled } from '@mui/material/styles';
import ToggleButton from '@mui/material/ToggleButton';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';
import Radio from '@mui/material/Radio';

const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(2),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  }));

export default function  TimeTable() {
    const [books, setBooks] = useRecoilState(booksSelector());
    const [checkTime, setCheckTime] = useState("");

    useEffect(() => {
        console.log(books);
    },[])
    const handleCheck = (event) => {
        setCheckTime(event.target.value);
    };
    return(
        <>
            <Grid container spacing={{ xs: 2}} columns={{ xs: 8 }}>
                {books.map((book, index) => (
                    <Grid item xs={2} sm={4} md={4} key={index}>
                    {/* <TimeDetail book={book} /> */}
                    <Radio 
                        checked={checkTime === book.time}
                        value={book.time}
                        disabled={book.isBook}
                        onChange={handleCheck}
                        // label={book.time}
                        // labelPlacement="end"
                    />
                    </Grid>
                ))}
            </Grid>
        </>
    );
}