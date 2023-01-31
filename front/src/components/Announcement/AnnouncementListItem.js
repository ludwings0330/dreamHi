import React from 'react';



function AnnouncementListItem(props) {
  const { announcement } = props;

  return (
    <div>
      <p>{announcement[0].result.list[0].title} </p>



    </div>
  );
}

export default AnnouncementListItem;