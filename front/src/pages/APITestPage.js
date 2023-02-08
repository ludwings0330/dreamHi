import 'bootstrap/dist/css/bootstrap.min.css';
import { updateProfileFile, aaa } from 'service/testService';
import { Button, Container } from '../../node_modules/@material-ui/core/index';

function APITestPage() {
    const updateTest = async () => {
        await updateProfileFile(12,{
            originName: "oridinginsdin",
            savedName: "dqsoboeno1n2o3.ph",
            type: "picture",
            url: "www.dreamhi.p-e.kr"
        },
        (response) => {
            console.log(response);
        },
        () => {
        });
    }

    // const onEventName = async () => {
    //     await aaa(1, {},
    //         (response) => {});
    // }
    const click = () => {
        console.log("ggg");
    }
  return (
      <>
      <Container align="center" mt={5}>
      <Button variant="text" size="large" onClick={updateTest}>Text</Button>
      </Container>
      <Container align="center" mt={5}>
        <h1 >RRRR</h1>
      </Container>
      <Container align="center" mt={5}>
        <h1 >RRRR</h1>
      </Container>
      <Container align="center" mt={5}>
        <h1 >RRRR</h1>
      </Container>
      </>
  );
}

export default APITestPage;
    