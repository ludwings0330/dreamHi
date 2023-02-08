import { atom } from 'recoil';

// export const announcementPictureUrl = atom({
//   key: 'announcementPictureUrl',
//   default: 'https://firebasestorage.googleapis.com/v0/b/dreamhi-17f24.appspot.com/o/logo.png?alt=media&token=a3ff4d49-9210-44d1-94e3-6d9d2dd64f22'
// });

//Announcement



export const announcementTitle = atom({
  key: 'announcementTitle',
  default: null
});

export const announcementProducerId = atom({
  key: 'announcementProducerId',
  default: null
});

export const announcementPayment = atom({
  key: 'announcementPayment',
  default: null
});

export const announcementCrankPeriod = atom({
  key: 'announcementCrankPeriod',
  default: null
});

export const announcementEndDate = atom({
  key: 'announcementEndDate',
  default: null
});

export const announcementDescription = atom({
  key: 'announcementDescription',
  default: null
});


export const announcementPictureUrl = atom({
  key: 'announcementPictureUrl',
  default: 'https://firebasestorage.googleapis.com/v0/b/dreamhi-17f24.appspot.com/o/logo.png?alt=media&token=a3ff4d49-9210-44d1-94e3-6d9d2dd64f22'
});


export const announcementCastingState = atom({
  key: 'announcementCastingState',
  default: [],
});

