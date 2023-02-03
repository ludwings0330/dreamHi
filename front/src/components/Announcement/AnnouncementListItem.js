import React from 'react';

//css
import './Announcement.css';

function AnnouncementListItem(props) {
  const { announcement, onClick } = props;
  const casts = announcement.result.list[0].castings;
  console.log(casts);
  console.log(announcement.result.list[0].state);

  return (
    <div>
      <div className="announcement-list-item-wrapper" onClick={onClick}>
        <p>
          {' '}
          {announcement.result.list[0].title} - {announcement.result.list[0].producerName}{' '}
        </p>
        <div className="announcement-list-castings">
          {casts.map(function (cast, index) {
            return (
              <span>
                {cast.name}역 {cast.headCount}명 &nbsp;{' '}
              </span>
            );
          })}
        </div>
        <p>{announcement.result.list[0].createDate}</p>
        <p>{announcement.result.list[0].hit}</p>
        <div>{announcement.result.list[0].isFollow === true ? '💙' : '🖤'}</div>

        <div>{announcement.result.list[0].state === '모집중' ? '⭕' : '❌'}</div>
      </div>
    </div>
  );
}

export default AnnouncementListItem;
