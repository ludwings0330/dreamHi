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
  default: [{
    id: '0',
    url: 'https://firebasestorage.googleapis.com/v0/b/dreamhi-17f24.appspot.com/o/images%2Fblank-profile-picture-973460_640.png?alt=media&token=0fd71f6c-3c8f-451c-958a-d321645845bf'
  }]
})

export const actorVideoLists = atom({
  key: 'actorVideoLists',
  default: [{
    id: '0',
    url: 'https://firebasestorage.googleapis.com/v0/b/dreamhi-17f24.appspot.com/o/images%2F00f5b771.mp4?alt=media&token=293dd7f5-a5ea-4a78-8ccc-8b11e4f7d25c'
  }]
})

export const googleToken = atom({
  key: 'googleToken',
  default: 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAwMDEiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZW1haWwiOiJkZGY5OThAZ21haWwuY29tIiwiZXhwIjoxNjc1OTIyODg2fQ.B2j72OmK5UMSwSI-qW0eDvw8nrHoBPWw1llg6LnMZvpKDG72RJqUuCg03lWDmg_P7lPhVS7rVtD0Rg0tpqygPA'
})