import 'bootstrap/dist/css/bootstrap.min.css';
import { updateProfileFile } from 'service/testService';
import { Button, Container } from '../../node_modules/@material-ui/core/index';
import { API_BASE_URL } from '../constants';

function APITestPage() {
  const updateTest = async () => {
    await updateProfileFile(
      12,
      {
        originName: 'oridinginsdin',
        savedName: 'dqsoboeno1n2o3.ph',
        type: 'picture',
        url: API_BASE_URL,
      },
      (response) => {
        console.log(response);
      },
      () => {},
    );
  };

  return (
    <>
      <Container align="center" mt={5}>
        <Button variant="text" size="large" onClick={updateTest}>
          Text
        </Button>
      </Container>
      <Container align="center" mt={5}>
        <h1>RRRR</h1>
      </Container>
      <Container align="center" mt={5}>
        <h1>RRRR</h1>
      </Container>
      <Container align="center" mt={5}>
        <h1>RRRR</h1>
      </Container>
    </>
  );
}

export default APITestPage;
