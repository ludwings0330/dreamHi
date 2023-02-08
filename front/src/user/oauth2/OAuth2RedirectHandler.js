import React, { useEffect, useRef } from 'react';
import { ACCESS_TOKEN } from '../../constants';
import { Navigate, useSearchParams } from '../../../node_modules/react-router-dom/dist/index';

function OAuth2RedirectHandler() {

    const redirectUrl = useRef("");
    const [searchParams, setSearchParams] = useSearchParams();

    const getRedirectUrl = () => {
        const isNew = searchParams.get("isNew");
        const accessToken = searchParams.get(ACCESS_TOKEN);
        const error = searchParams.get("error");

        if(accessToken) {
            localStorage.setItem("accessToken", accessToken);
            if(isNew === "true") {
                redirectUrl.current = "/actor/write";
            } else {
                redirectUrl.current = "/";
            }
        } else {
            redirectUrl.current = "/login";
        }
    }
    
    useEffect(() => {
        getRedirectUrl();
    }, [])

    return (
        <Navigate to={redirectUrl.current} replace={true}/>
    )
}

export default OAuth2RedirectHandler;