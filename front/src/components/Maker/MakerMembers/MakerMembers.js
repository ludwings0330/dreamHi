import React, { useState } from 'react';
import { useRecoilState } from 'recoil';
import { makerMemberLists } from '../../../recoil/maker/makerStore';
import {} from './MakerMemberList.css';

const MakerMembers = () => {
  const [makerMembers, setMakerMembers] = useRecoilState(makerMemberLists);
  const [selected, setSelected] = useState(null);

  return (
    <>
      <div id={'maker-member-whole'}>
        <h1>제작진리스트</h1>
        <div className="maker-member-list-container">
          {/*메인이미지 부분*/}
          {selected ? (
            <div className="maker-member-main">
              <img src={selected.pictureUrl} alt={selected.name} />
              <p>
                이름 : {selected.name} <br />
                직책 : {selected.position}
              </p>
            </div>
          ) : null}

          <div className="maker-member-photo-list">
            {makerMembers.map((makerMember, idx) =>
              idx === 0 ? null : (
                <div className="maker-member-list-item" key={idx}>
                  <img
                    src={makerMember.pictureUrl}
                    alt={makerMember.name}
                    width={'200px'}
                    height={'200px'}
                    onClick={() => setSelected(makerMember)}
                  />
                  <p>이름 : {makerMember.name}</p>
                  <p>직책 : {makerMember.position}</p>
                </div>
              ),
            )}
          </div>
        </div>
      </div>
    </>
  );
};

export default MakerMembers;
