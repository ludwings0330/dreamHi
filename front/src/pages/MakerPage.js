import React from 'react';
import MakerList from '../components/Maker/MakerList';
import MainHeader from '../components/Common/MainHeader';
import MainFooter from '../components/Common/MainFooter';
import Layout from '../components/Common/Layout';

const MakerPage = () => {
  return (
    <Layout>
      <MakerList />
    </Layout>
  );
};

export default MakerPage;