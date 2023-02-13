import React, { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './ActorVideo.css';
import ActorVideoUpload from './ActorVideoUpload';
import {
  actorProfileId,
  actorPhotoUrl,
  actorProfile,
  actorVideoLists,
} from 'recoil/recoilActorState';
import { useRecoilValue, useRecoilState } from 'recoil';
import axios from 'axios';

const ActorVideo = () => {
  const { actorProfileId } = useParams();
  console.log(actorProfileId);
  const [actorVideos, setActorVideos] = useRecoilState(actorVideoLists);
  // const actorVideos = useRecoilValue(actorVideoLists);

  const setSelected = (idx) => {
    document.querySelector(
      '.actor-video-main',
    ).innerHTML = `<video src=${actorVideos[idx].url} alt=${actorVideos[idx]} />`;
  };

  console.log(actorVideos, '비디오 리스트');
  // console.log(actorVideos[0].url, '안되냐ㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑ')
  useEffect(() => {
    // axios.get(`http://i8a702.p.ssafy.io:8085/api/actors/100001/media`)
    axios
      .get(`http://i8a702.p.ssafy.io:8085/api/actors/${actorProfileId}/media`)
      .then((res) => {
        setActorVideos(res.data.result.videos);
        console.log(res.data.result.videos, '잘 찍히나요?');
      })
      .catch((err) => {
        console.log(err);
      });
  }, [setActorVideos]);

  return (
    <div className="bg-white">
      <h1>연기영상</h1>

      {/*연기영상 전체 틀*/}
      <div className="actor-video-whole">
        {/*연기영상 메인*/}
        <div className="actor-video-main">
          <video src={actorVideos[0].url} alt={actorVideos[0].url} />
        </div>

        {/*연기영상 list*/}
        {actorVideos.length > 0 &&
          actorVideos.map((actorVideo, idx) => (
            <div className="actor-video-list" key={idx}>
              <video src={actorVideo.url} alt="image" onClick={() => setSelected(idx)} />
            </div>
          ))}

        <ActorVideoUpload />
      </div>
    </div>
  );
};

export default ActorVideo;

// import React from 'react';
// import './ActorVideo.css';

// const products = [
//   {
//     id: 1,
//     name: 'Basic Tee',
//     imageSrc: 'https://cdn.pixabay.com/vimeo/780232342/%EC%86%8C%EB%82%98%EB%AC%B4%20-%20142579.mp4?width=640&hash=cc422ffdf710dae6979b15e61522023b232e3d4d',
//   },
//   {
//     id: 2,
//     name: 'Basic Tee',
//     imageSrc: 'https://cdn.pixabay.com/vimeo/629483574/%EC%98%A4%EB%A1%9C%EB%9D%BC%20%EB%B3%B4%20%EB%A6%AC%20%EC%96%BC%20%EB%A6%AC%EC%8A%A4%20-%2090877.mp4?width=960&hash=ed2df50dc5d55a8fbcad914dab6a4e6214fe2845',
//   },
//   {
//     id: 3,
//     name: 'Basic Tee',
//     imageSrc: 'https://cdn.pixabay.com/vimeo/764361528/%EB%8C%80%EC%96%91%20-%20135658.mp4?width=1280&hash=29d5a82cfe208bfbe486b1faa182cd11fce9efa1',
//   },
//   {
//     id: 4,
//     name: 'Basic Tee',
//     imageSrc: 'https://cdn.pixabay.com/vimeo/694704491/%EB%82%98%EB%AC%B4%20%EB%A7%90%EB%AF%B8%EC%9E%98%20-%20112429.mp4?width=640&hash=cc4b82f4fba0f71bc19b80f1df7a3470bd89f181',
//   },
// ]
// const setSelected = (idx) => {
//   document.querySelector('.video-main').innerHTML=`<video src=${products[idx].imageSrc} alt=${products[idx].imageAlt} controls/>`
// };

// const ActorVideo = () => {
//   return (
//     <div className="bg-white">

//       <div className="mx-auto max-w-2xl py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
//         <h1>연기영상</h1>

//         <div className="list-container">
//           <div className='video-main'>
//             <video src="https://firebasestorage.googleapis.com/v0/b/dreamhi-17f24.appspot.com/o/images%2F00f5b771.mp4?alt=media&token=293dd7f5-a5ea-4a78-8ccc-8b11e4f7d25c" alt={products[0].imageAlt} controls/>
//           </div>

//           <div className='video-list'>
//             {products.map((product, idx) => (
//               idx === 0 ? null :
//                 <div className='video' key={product.id}>
//                   <video
//                     src={product.imageSrc}
//                     alt={product.imageAlt}
//                     width={"200px"}
//                     height={"200px"}
//                     className="object-center"
//                     onClick={() => setSelected(idx)}
//                   />
//                 </div>
//             ))}
//           </div>

//         </div>

//       </div>
//     </div>

//   );
// };

// export default ActorVideo;
