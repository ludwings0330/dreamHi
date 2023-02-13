import React, { useEffect, useRef } from 'react';
import { ACCESS_TOKEN } from '../../constants';
import { Navigate, useSearchParams } from '../../../node_modules/react-router-dom/dist/index';
import { useRecoilValue, useRecoilState } from 'recoil';
import { userSimpleInfo, userSimpleState } from 'recoil/user/userStore';
import api from 'util/APIUtils';
import jwtApi from 'util/JwtApi';

function OAuth2RedirectHandler() {
  const redirectUrl = useRef('');
  const [searchParams, setSearchParams] = useSearchParams();
  const [userSimple, setUserSimpleState] = useRecoilState(userSimpleState);

  const getRedirectUrl = async () => {
    const isNew = searchParams.get('isNew');
    const accessToken = searchParams.get(ACCESS_TOKEN);
    const error = searchParams.get('error');

    if (accessToken) {
      localStorage.setItem('accessToken', accessToken);

      if (isNew === 'true') {
        redirectUrl.current = '/actor/write';
      } else {
        redirectUrl.current = '/';
      }
      const data = await jwtApi.get('/api/my').then((response) => {
        console.log(response);
        return response.data.result;
      });
      setUserSimpleState(data);
      console.log(userSimple);
    } else {
      redirectUrl.current = '/login';
    }
  };

  useEffect(() => {
    getRedirectUrl();
  }, []);

  return <Navigate to={redirectUrl.current} replace={true} />;
}

export default OAuth2RedirectHandler;
