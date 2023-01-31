import React from 'react';
import AppHeader from '../components/Common/AppHeader';
import AppFooter from '../components/Common/AppFooter';
import CastingRow from '../components/Casting/CastingRow';
import CastingTitle from '../components/Casting/CastingTitle';
import CastingDetail from '../components/Casting/CastingDetail';

function AppCasting() {
  return (
    <div>
      <AppHeader />
      <CastingTitle />
      {/*<CastingRow />*/}
      <CastingDetail />
      <AppFooter />
    </div>
  );
}

export default AppCasting;