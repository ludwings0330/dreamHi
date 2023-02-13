import { atom } from 'recoil';

export const makerProfileId = atom({
  key: 'makerProfileId',
  default: 'locker',
});

export const makerProfile = atom({
  key: 'makerProfile',
  default: {},
});

export const makerPhotoUrl = atom({
  key: 'actorPhotoUrl',
  default: null,
});

export const makerFilmoUrl = atom({
  key: 'makerFilmoUrl',
  default: null,
});

export const makerFilmoLists = atom({
  key: 'makerPhotoLists',
  default: [
    {
      id: '0',
      url: 'https://firebasestorage.googleapis.com/v0/b/dreamhi-17f24.appspot.com/o/images%2Fblank-profile-picture-973460_640.png?alt=media&token=0fd71f6c-3c8f-451c-958a-d321645845bf',
    },
  ],
});

export const googleToken = atom({
  key: 'googleToken',
  default:
    'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAwMDEiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZW1haWwiOiJkZGY5OThAZ21haWwuY29tIiwiZXhwIjoxNjc1OTMwOTc2fQ.nY5hyxEi6xQv_1ZUErustADrwOyfCtpbnRnpaRvw2CHHsNEZPrKjbXsiZRvZ1Whd8HKYN9bj3iJEjqLK1AUtQw',
});
