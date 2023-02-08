import { atom } from 'recoil'

export const actorProfileId = atom({
  key: 'actorProfileId',
  default: 'locker'
})

export const actorProfile = atom({
  key: 'actorProfile',
  default: {}
})

export const ActorFilmoUrl = atom({
  key: 'ActorFilmoUrl',
  default: null
})
export const ActorPhotoUrl = atom({
  key: 'ActorPhotoUrl',
  default: null
})

export const ActorVideoUrl = atom({
  key: 'ActorVideoUrl',
  default: null
})
