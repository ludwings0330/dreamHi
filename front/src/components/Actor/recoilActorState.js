import { atom } from 'recoil'

export const actorProfileId = atom({
  key: 'actorProfileId',
  default: 'locker'
})

export const actorProfile = atom({
  key: 'actorProfile',
  default: {}
})

export const actorFilmoUrl = atom({
  key: 'actorFilmoUrl',
  default: null
})
export const actorPhotoUrl = atom({
  key: 'actorPhotoUrl',
  default: null
})

export const actorVideoUrl = atom({
  key: 'actorVideoUrl',
  default: null
})

export const actorPhotoLists = atom({
  key: 'actorPhotoLists',
  default: []
})