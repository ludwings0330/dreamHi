import React from 'react';
import Layout from '../Common/Layout';
import ActorIntroduce from './info/ActorIntroduce';
import {} from './ActorWrite.css';
import ActorFilmo from './filmo/ActorFilmo';
import ActorPhoto from './photo/ActorPhoto';
import ActorVideo from './video/ActorVideo';




const ActorWrite = () => {
    return (
            <Layout>
              <h1 className={"main-title"}>이력서 작성</h1>
               <ActorIntroduce />
              <ActorFilmo />
              <ActorPhoto />
              <ActorVideo />
            </Layout>
    );
};

export default ActorWrite;