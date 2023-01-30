import React from 'react';
import ActorIntroduce from './ActorIntroduce';
import AppHeader from '../../Common/AppHeader';
import AppFooter from '../../Common/AppFooter';
import {} from '../../Main/Main.css'


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