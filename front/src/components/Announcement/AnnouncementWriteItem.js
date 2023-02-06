import React from 'react';
import ImageUpload from '../../imageup/ImageUpload';


function AnnouncementWriteItem(props) {
  // const [ pictureUrl, setPictureUrl ] = useState('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdSg7n-rd7p-hE0blFeURASyZ1VyDPE-8XG7uKieLidEMW4ZzwIiQFFOMR2jquRkQs43A&usqp=CAU')
  // console.log(111222333)
  // console.log(pictureUrl)



  return (
    <div>
      <ImageUpload />


      {/*<ImageUpload onChange={value => setPictureUrl({...pictureUrl, ...value})}/>*/}

    </div>
  );
}

export default AnnouncementWriteItem;