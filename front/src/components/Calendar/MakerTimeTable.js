import React, {useEffect, useState} from "react";
import { checkTimeState } from "recoil/book/bookStore";
import { useRecoilState } from "recoil";
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Radio from '@mui/material/Radio';
import RadioGroup, { useRadioGroup } from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import { volunteersSelector, vlounteerUserIdState } from "recoil/volunteer/volunteerStore";


export default function  MakerTimeTable() {
    const [volunteers, setVolunteers] = useRecoilState(volunteersSelector());
    // const [checkTime, setCheckTime] = useRecoilState(checkTimeState);
    const [checkVolunteerUserId, setVolunteerCheckUserId] = useRecoilState(vlounteerUserIdState);
    
    
    const handleCheck = (e) => {
        console.log("Handle Check");
        console.log(e.target.value);
        setVolunteerCheckUserId(e.target.value);
    }

    useEffect( () => {
        console.log("RPODUCER");
        console.log(volunteers);
        console.log(checkVolunteerUserId);
    }, [checkVolunteerUserId])
    return(
        <Box sx={{mt:3, mb: 3}}>
            <Grid container spacing={{ xs: 2}} columns={{ xs: 8 }}>
                {volunteers.map((volunteer, index) => (
                    <Grid item xs={2} sm={4} md={4} key={index}>
                    {/* <TimeDetail book={book} /> */}
                    <Box sx={{
                        // display: book.isBook ? "none" : "block",
                        boxShadow: 3,
                        borderRadius: 5,
                        // fontWeight: 'bold',
                        bgcolor: volunteer.userId == null || volunteer.userId == checkVolunteerUserId ? "#051094" : "#52B2BF",
                        // fontSize: 18,
                        // color: "#FFFFFF",
                        opacity: !volunteer.isBook ? [1, 1, 0.1] : [1, 1, 0.8],
                    }}
                    textAlign="center"
                    >
                    <FormControlLabel label={volunteer.time} control={<Radio
                            checked={checkVolunteerUserId == volunteer.userId}
                            value={volunteer.userId}
                            disabled={!volunteer.isBook}
                            onChange={handleCheck}
                            sx={{
                                color:"#FFFFFF",
                            }}
                    />} sx={{
                        color: "#FFFFFF",
                        display:"block"
                    }}>
                    </FormControlLabel>
                    </Box>
                    </Grid>
                ))}
            </Grid>
        </Box>
    );
}