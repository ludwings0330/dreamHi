import React from 'react';
import Layout from '../components/Common/Layout';
import Button from '../components/Common/Button';
import { useNavigate } from 'react-router-dom';

function AuditionPage(props) {
  const navigate = useNavigate()
  return (
    <Layout>
      <Button
        title="면접보기"
        onClick={() => {
          navigate("/audition/meeting")
        }}
      />

    </Layout>
  );
}

export default AuditionPage;