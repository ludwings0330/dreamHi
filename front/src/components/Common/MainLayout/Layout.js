import React from 'react';
import MainFooter from './MainFooter';
import MainHeader from './MainHeader';

const Layout = (props) => {
  return (
    <div>
      <MainHeader />
      <main>
        {props.children}
      </main>

      <MainFooter />
    </div>
  );
};

export default Layout;
