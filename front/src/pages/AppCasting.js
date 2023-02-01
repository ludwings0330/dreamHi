import React from 'react';
import CastingTitle from '../components/Casting/CastingTitle';
import CastingRow from '../components/Casting/CastingRow';
import CastingDetail from '../components/Casting/CastingDetail';
import AppHeader from '../components/Common/AppHeader';
import AppFooter from '../components/Common/AppFooter';

const MyComponent = () => {
  return (
    <div>

      <AppHeader />

      <CastingRow />

      <AppFooter />


    </div>
  );
};

export default MyComponent;
