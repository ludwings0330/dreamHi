import AppActor from "./AppActor";
import AppHeader from '../components/Common/AppHeader';

import 'bootstrap/dist/css/bootstrap.min.css';
import AppFooter from '../components/Common/AppFooter';
import MainPage from '../components/Main/MainPage';

function AppMain() {
  return (
    <div className="App">
      <AppHeader />
      <AppActor />
      <MainPage />
      <AppFooter />
    </div>
  );
}

export default AppMain;
