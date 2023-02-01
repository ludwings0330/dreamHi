import React from 'react';
import ActorList from '../components/Actor/ActorList';
import MainHeader from '../components/Common/MainHeader';
import MainFooter from '../components/Common/MainFooter';
import Layout from '../components/Common/Layout';


const ActorPage = () => {

    return (
        <Layout>
          <ActorList />
        </Layout>
    );
};

export default ActorPage;