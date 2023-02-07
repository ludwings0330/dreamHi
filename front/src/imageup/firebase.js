import { initializeApp } from "firebase/app";
import { getStorage } from "firebase/storage";
const firebaseConfig = {
  apiKey: "AIzaSyAx8M5RHMB5-DZKeGVt7UIhUoP8pq-h9T0",
  authDomain: "dreamhi-17f24.firebaseapp.com",
  projectId: "dreamhi-17f24",
  storageBucket: "dreamhi-17f24.appspot.com",
  messagingSenderId: "959002490680",
  appId: "1:959002490680:web:b4ff78c07407e8da5c33cd",

};

const app = initializeApp(firebaseConfig);
export const storage = getStorage(app);


// import { initializeApp } from "firebase/app";
// import { getFirestore } from 'firebase/firestore';
//
//
// const firebaseConfig = {
//
// };
//
// const app = initializeApp(firebaseConfig);
//
// export const db = getFirestore(app);
