import React from "react";
import "bootstrap/scss/bootstrap.scss";


import "./MainFooter.css";

function MainFooter() {

    const thisYear = () => {
        const year = new Date().getFullYear();
        return year;
    };

    return (

        <div id={"main-footer"}>


            <div className="footer-left">
                <img src='/img/elephant.png' alt='elephant-logo' className="footer-logo"/>
            </div>

            <div className="footer-right">
                <p>Made by Africa Elephant</p>
            </div>

        </div>
    );
};

export default MainFooter;
