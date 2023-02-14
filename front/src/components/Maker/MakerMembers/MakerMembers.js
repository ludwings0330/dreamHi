import React, { useState } from 'react';
import { useRecoilState } from 'recoil';
import { makerMemberLists } from '../../../recoil/maker/makerStore';

const MakerMembers = () => {
  const [makerMembers, setMakerMembers] = useRecoilState(makerMemberLists);
  const [selected, setSelected] = useState(null);

  return (
    <div className="bg-white">
      <div className="mx-auto max-w-2xl py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
        <h1>제작진리스트</h1>

        <div className="list-container">
          {/*메인이미지 부분*/}
          {selected ? (
            <div className="photo-main">
              <img src={selected.pictureUrl} alt={selected.name} />
              <p>
                이름 : {selected.name} <br />
                직책 : {selected.position}
              </p>
            </div>
          ) : null}

          <div className="photo-list">
            {makerMembers.map((makerMember, idx) =>
              idx === 0 ? null : (
                <div className="photo" key={idx}>
                  <img
                    src={makerMember.pictureUrl}
                    alt={makerMember.name}
                    width={'200px'}
                    height={'200px'}
                    className="object-center"
                    onClick={() => setSelected(makerMember)}
                  />
                  <p>
                    이름 : {makerMember.name} <br />
                    직책 : {makerMember.position}
                  </p>
                </div>
              ),
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default MakerMembers;
