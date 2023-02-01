import 'bootstrap/dist/css/bootstrap.min.css';
import AppHeader from '../components/Common/MainHeader';
import AppFooter from '../components/Common/MainFooter';
import MainView from '../components/Main/MainView';
import ActorPage from './ActorPage';
import Login from '../user/login/Login';


function AppMain() {
  return (
    <div>
      {/*<h2>main</h2>*/}
      <AppHeader />
      <MainView />
      <AppFooter />
      {/*<ActorPage />*/}
      {/*<Login />*/}
    </div>
  );
}

export default AppMain;
