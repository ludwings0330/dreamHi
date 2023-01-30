import 'bootstrap/dist/css/bootstrap.min.css';
import AppHeader from '../components/Common/AppHeader';
import AppFooter from '../components/Common/AppFooter';
import MainPage from '../components/Main/MainPage';
import AppActor from './AppActor';
import Login from '../user/login/Login';


function AppMain() {
  return (
    <div>
      {/*<h2>main</h2>*/}
      <AppHeader />
      <MainPage />
      <AppFooter />
      {/*<AppActor />*/}
      {/*<Login />*/}
    </div>
  );
}

export default AppMain;
