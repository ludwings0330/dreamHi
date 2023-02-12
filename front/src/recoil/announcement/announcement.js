import { atom } from 'recoil';

// export const announcementPictureUrl = atom({
//   key: 'announcementPictureUrl',
//   default: 'https://firebasestorage.googleapis.com/v0/b/dreamhi-17f24.appspot.com/o/logo.png?alt=media&token=a3ff4d49-9210-44d1-94e3-6d9d2dd64f22'
// });

//Announcement



export const announcementTitle = atom({
  key: 'announcementTitle',
  default: ''
});

export const announcementProducerId = atom({
  key: 'announcementProducerId',
  default: ''
});

export const announcementPayment = atom({
  key: 'announcementPayment',
  default: ''
});

export const announcementCrankPeriod = atom({
  key: 'announcementCrankPeriod',
  default: ''
});

export const announcementEndDate = atom({
  key: 'announcementEndDate',
  default: ''
});

export const announcementDescription = atom({
  key: 'announcementDescription',
  default: ''
});


export const announcementPictureUrl = atom({
  key: 'announcementPictureUrl',
  default: 'https://firebasestorage.googleapis.com/v0/b/dreamhi-17f24.appspot.com/o/logo.png?alt=media&token=a3ff4d49-9210-44d1-94e3-6d9d2dd64f22'
});


export const announcementCastingState = atom({
  key: 'announcementCastingState',
  default: [],
});

