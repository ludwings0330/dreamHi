import { atom } from 'recoil';
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
