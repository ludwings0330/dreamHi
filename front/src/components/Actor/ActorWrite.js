import React from 'react';
import ActorIntroduce from './info/ActorIntroduce';
import ActorFilmo from './filmo/ActorFilmo';
import ActorPhoto from './photo/ActorPhoto';
import ActorVideo from './video/ActorVideo';
import {} from './ActorWrite.css';





const ActorWrite = () => {
    return (
            <>
              <h1 className={"main-title"}>이력서 작성</h1>
                <ActorIntroduce />
                <ActorFilmo />
                <ActorPhoto />
                <ActorVideo />
            </>
    );
};

export default ActorWrite;