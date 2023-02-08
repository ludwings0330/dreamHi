import React, { useRef, useState } from 'react';
import ActorProfile from './ActorProfile';
import ActorInfo from './ActorInfo';
import ActorIntro from './ActorIntro';
import Button from '../../Common/CommonComponent/Button';


const ActorIntroduce = () => {

    return (
        <>
            <ActorProfile />
            <ActorInfo />
            <ActorIntro />
        </>

    );
};

export default ActorIntroduce;