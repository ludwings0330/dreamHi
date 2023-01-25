import AppActor from "./AppActor";
import AppHeader from '../components/Common/AppHeader';

import 'bootstrap/dist/css/bootstrap.min.css';
import AppFooter from '../components/Common/AppFooter';

function AppMain() {
  return (
    <div className="App">
      <AppHeader />
      <AppActor />
      <img src="img/test.png" />
      <AppFooter />
    </div>
  );
}

export default AppMain;
