import React from 'react';
import './Layout.css';
import MainFooter from './MainFooter';
import MainHeader from './MainHeader';

const Layout = (props) => {
    return (
        <>
            <header id={"main-header"}>
                <MainHeader/>
            </header>

            <main id={"main-content"}>
                {props.children}
            </main>

            <footer id={"main-footer"}>
                <MainFooter/>
            </footer>
        </>
    );
};

export default Layout;
