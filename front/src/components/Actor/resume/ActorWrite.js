import React from 'react';
import ActorIntroduce from './ActorIntroduce';
import AppHeader from '../../Common/MainHeader';
import AppFooter from '../../Common/MainFooter';
import {} from '../../Main/MainView.css'


const ActorWrite = () => {
    return (
        <div>
          <AppHeader />
            <ActorIntroduce />
          <AppFooter />
        </div>
    );
};

export default ActorWrite;