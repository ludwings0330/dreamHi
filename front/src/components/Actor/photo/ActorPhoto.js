import React, {useEffect} from 'react';
import './ActorPhoto.css';
import ActorPhotoUpload from './ActorPhotoUpload';
import { actorProfileId, actorPhotoUrl, actorProfile, actorPhotoLists } from '../recoilActorState'
import { useRecoilValue, useRecoilState } from 'recoil'
import axios from 'axios'

const ActorPhoto = () => {
  const actorPhotos = useRecoilValue(actorPhotoLists);
  // const setSelected = (idx) => {
  //   document.querySelector('.actor-photo-main').innerHTML=`<img src=${actorPhotoList[idx].url} alt=${actorPhotoList[idx]}/>`
  // };
  const actorPhotoList = useRecoilValue(actorPhotoLists)

  // console.log(actorPhotos[0].url, '안되냐ㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑ')
  // useEffect(() => {
  //   axios.get(`http://i8a702.p.ssafy.io:8085/api/actors/100001/media`)
  //     .then((res) => {
  //       setActorPhotos(res.data.result.pictures)
  //       console.log(res.data.result.pictures, '잘 찍히나요?')
  //     })
  //     .catch((err) => {
  //       console.log(err)
  //     })
  // }, [setActorPhotos]);

  return (
    <div className="bg-white">

      <div className="mx-auto max-w-2xl py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
        <h1>프로필사진</h1>

        <div className="list-container">
          <div className='actor-photo-main'>
            {/*<img src={actorPhotoList[0].url} alt={actorPhotoList[0].url}/>*/}
          </div>
          {actorPhotos.length > 0 && actorPhotos.map((actorPhoto, idx) => (
            <div className='photo'
                 key={idx}
                 width={"200px"}
                 height={"200px"}>
              <img src={actorPhoto.url}
                   alt='image'
                   object-fit={"contain"}
                   className="object-center"
                   // onClick={() => setSelected(idx)}
              />
            </div>
          ))}

          {/*<div className='photo-list'>*/}
          {/*  {products.map((product, idx) => (*/}
          {/*    idx === 0 ? null :*/}
          {/*      <div className='photo' key={product.id}>*/}
          {/*        <img*/}
          {/*          src={product.imageSrc}*/}
          {/*          alt={product.imageAlt}*/}
          {/*          width={"200px"}*/}
          {/*          height={"200px"}*/}
          {/*          className="object-center"*/}
          {/*          onClick={() => setSelected(idx)}*/}
          {/*        />*/}
          {/*      </div>*/}
          {/*  ))}*/}
          {/*</div>*/}

          <ActorPhotoUpload />
        </div>
      </div>
    </div>

  );
};

export default ActorPhoto;