import React from 'react';



function AnnouncementListItem(props) {
  const { announcement, onClick } = props;
  // console.log(11111111111111111)
  // console.log(announcement.result.list[0].title)

  return (
    <div onClick={onClick}>
      <p>{announcement.result.list[0].title} </p>



    </div>
  );
}

export default AnnouncementListItem;