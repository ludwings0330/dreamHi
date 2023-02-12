import React from "react";
import Box  from "@mui/material/Box";
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import { useRecoilValue } from "recoil";
import { announcementTitle } from "recoil/announcement";
import { userTypeState } from "recoil/user/userStore";

export default function BasicNotice () {
    const title = useRecoilValue(announcementTitle);
    const userType = useRecoilValue(userTypeState);
    return (
        <Box  sx={{
            minHeight: 600,
            color: "#41424C"
        }} align="center">
                                {/* announcement title 가져오기 */}
            <Typography variant="h5">{title}</Typography>
            <br/>
            <Typography variant="subtitle1">화상 면접 가이드</Typography>
            <br/>
            
            <Grid
            container
            direction="column"
            justifyContent="space-between"
            alignItems="flex-start"
            spacing={10}
            wrap="nowrap"
            >

                <Grid item>
                <Typography xs variant="button" sx={{
                    pl: 1, pr: 2,
                    fontSize: 15
                }}>
                {userType === "producer".toUpperCase() ? 
                "1️⃣ 오디션을 진행할 기간과 진행 가능한 시간대를 설정해주세요." : 
                "1️⃣ 오디션 진행 가능한 시간대를 확인하고 예약해주세요."
                }
                </Typography>
                </Grid>

                <Grid item >
                <Typography variant="button" sx={{
                    pl: 1, pr: 2,
                    fontSize: 15
                }}>
                2️⃣ 면접은 30분 단위로 진행됩니다.
                </Typography>
                </Grid>

                <Grid item>
                <Typography variant="button" sx={{
                    pl: 1, pr: 2,
                    fontSize: 15
                }}>
                {userType === "producer".toUpperCase() ? 
                "3️⃣ 공지사항 및 대본은 하단 버튼을 통해 업로드해주세요." : 
                "3️⃣ 공지사항 및 대본은 하단 버튼을 통해 다운로드해주세요."
                }
                </Typography>
                </Grid>
            </Grid>
        </Box>
    )
}