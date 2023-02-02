import React from 'react';
import MainHeader from '../components/Common/MainHeader';
import MainFooter from '../components/Common/MainFooter';
import Layout from '../components/Common/Layout';
import CastingRow from '../components/Casting/CastingRow';

const CastingPage = () => {
  return (
    <Layout>
      <CastingRow />
    </Layout>
  );
};

export default CastingPage;