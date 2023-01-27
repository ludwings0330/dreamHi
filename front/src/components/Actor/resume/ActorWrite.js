import React from 'react';
import ActorIntroduce from './ActorIntroduce';
import AppHeader from '../../Common/AppHeader';
import AppFooter from '../../Common/AppFooter';
import ActorFilmo from './ActorFilmo';
import ActorPhoto from './ActorPhoto';
import ActorVideo from './ActorVideo';

const ActorWrite = () => {
    return (
        <div>
          <AppHeader />
            <ActorIntroduce />
            <ActorFilmo />
            <ActorPhoto />
            <ActorVideo />
          <AppFooter />
        </div>
    );
};

export default ActorWrite;