import React from 'react';
import Layout from '../Common/Layout';
import ActorIntroduce from './info/ActorIntroduce';
import ActorFilmo from './filmo/ActorFilmo';
import ActorPhoto from './photo/ActorPhoto';
import ActorVideo from './video/ActorVideo';

const ActorDetail = () => {
    return (
        <Layout>
          <ActorIntroduce />
          <ActorFilmo />
          <ActorPhoto />
          <ActorVideo />
        </Layout>
    );
};

export default ActorDetail;