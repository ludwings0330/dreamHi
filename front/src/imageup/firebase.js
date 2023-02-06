import { initializeApp } from "firebase/app";
import { getStorage } from "firebase/storage";
const firebaseConfig = {

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
