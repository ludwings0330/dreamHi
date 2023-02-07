import React from 'react';
import ActorIntroduce from './info/ActorIntroduce';
import ActorFilmo from './filmo/ActorFilmo';
import ActorPhoto from './photo/ActorPhoto';
import ActorVideo from './video/ActorVideo';

const ActorDetail = () => {
    return (
        <>
          <ActorIntroduce />
          <ActorFilmo />
          <ActorPhoto />
          <ActorVideo />
        </>
    );
};

export default ActorDetail;