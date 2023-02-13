import { atom, selectorFamily } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist();

export const userSimpleState = atom({
  key: 'userSimpleState',
  default: {
    id: '',
    name: '',
    pictureUrl: '',
  },
  effects_UNSTABLE: [persistAtom],
});

// selector는 파생시킬 때 사용해야한다.
// userSimpleState의 경우 초기 값으로 id가 들어오지 않기 때문에 안되는건가??
// export const userSimpleSelector = selectorFamily({
//     key: "userSimpleSelector",
//     get: () => ({get}) => {
//         return get(userSimpleState);
//     },
//     set: ({set}, data) => {
//         const newData = {
//             id: data.id ? data.id : "",
//             id: data.name ? data.name : "",
//             id: data.pictureUrl ? data.pictureUrl : ""
//         };
//         console.log("userSimpleState 설정 => ", data);
//         set(userSimpleState, newData);
//     }
// })

export const userTypeState = atom({
  key: 'userTypeState',
  default: 'ACTOR',
  // default: "PRODUCER",
  effects_UNSTABLE: [persistAtom],
});
