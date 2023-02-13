import {useState, useEffect} from 'react';
import {ref, uploadBytes, getDownloadURL, listAll} from 'firebase/storage';
import {useRecoilValue, useRecoilState} from 'recoil';
import axios from 'axios';

import {storage} from '../../../imageup/firebase';
import {v4} from 'uuid';


// import recoil
import {actorProfile, actorFilmoUrl, actorFilmoLists, googleToken} from 'recoil/recoilActorState'

// import css
import '../../../components/Casting/Casting.css';
import './ActorFilmo.css';

function ActorFilmoUpload(props) {
    console.log('test',props)
    //setActorPhotos
    console.log(props.actorFilmos)
    const [ActorFilmoUploaded, setActorFilmoUploaded] = useState(null);
    const ActorFilmoDirectory = useRecoilValue(actorFilmoUrl)
    const actorInfo = useRecoilValue(actorProfile)

    const ActorFilmosListRef = ref(storage, ActorFilmoDirectory);

    const token = useRecoilValue(googleToken)


    const uploadFile = () => {
        if (ActorFilmoUploaded === null) return;
        const imageRef = ref(storage, `${ActorFilmoDirectory}/${ActorFilmoUploaded.name + v4()}`)
        uploadBytes(imageRef, ActorFilmoUploaded).then((snapshot) => {
            getDownloadURL(snapshot.ref)
                .then((url) => {
                    // setActPhotoUrl(url);
                    const content = {
                        originName: `${actorInfo.name}'s picture`,
                        savedName: `picture ${actorInfo.name}`,
                        type: "PICTURE",
                        url: url
                    }
                    console.log("파베",url)
                    props.setActorFilmos(props.actorFilmos.concat({'id':
                        props.actorFilmos.length,
                        'url':url}))
                    // axios.post(`http://i8a702.p.ssafy.io:8085/api/actors/${actorInfo.actorProfileId}/media`,
                    axios.post(`http://localhost:8080/api/actors/100001/media`,
                        content,
                        {
                            headers: {
                                Authorization: `Bearer ${token}`,
                            }
                        })
                        .then((res) => {
                            console.log('post success',res)

                        })
                        .catch((err) => {
                            console.log(err)
                        })
                });
        });
    };


    return (
        <div>
            <div className="photo-list">
                <div className="file-box">
                    <label htmlFor='file-photo'>
                        <img
                            src="/img/plus.png"
                            width={"200px"}
                            height={"200px"}
                            object-fit={"cover"}
                            className="object-center"
                        />
                    </label>
                    <input
                        type="file"
                        id="file-photo"
                        onChange={(e) => {

                            setActorFilmoUploaded(e.target.files[0])
                        }
                        }
                    />
                    <button
                        onClick={uploadFile}
                    >
                        사진 올리기
                    </button>
                </div>
            </div>
        </div>
)
    ;
};

export default ActorFilmoUpload;