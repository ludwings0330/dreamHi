import React, { useState } from 'react';


const SearchBar = () => {

  const sex =
    [
      {query: '001', text: '여자' },
      {query: '002', text: '남자' },
    ];

  const height =
    [
      {query: '003', text: '~150cm' },
      {query: '004', text: '150cm~160cm' },
      {query: '005', text: '160cm~170cm' },
      {query: '006', text: '170cm~180cm' },
      {query: '007', text: '180cm~' },
    ];

  const body =
    [
      {query: '008', text: '앙상함' },
      {query: '009', text: '날씬함' },
      {query: '010', text: '보통체격' },
      {query: '011', text: '통통함' },
      {query: '012', text: '뚱뚱함' },
      {query: '013', text: '건장함' },
    ];

  const age =
    [
      {query: '014', text: '~10살' },
      {query: '015', text: '10대~20대' },
      {query: '016', text: '20대~30대' },
      {query: '017', text: '30대~40대' },
      {query: '018', text: '40대~50대' },
      {query: '019', text: '50대~60대' },
      {query: '020', text: '60대~' },
    ];

  // state 사용
  const [selected, setSelected] = React.useState("");


  // 셀렉트 컴포넌트가 변경될때 불려짐
  const changeSelectOptionHandler = (event) => {
    setSelected(event.target.value);
  };

  // 선택된 아이템에 따라 값을 비교하고 데이타를 리턴
  let sexInquiries = sex.filter(data => {
    if (selected === "001") {
      return data;
    } else if (selected === "002") {
      return data;
    }
  })

  let heightInquiries = height.filter(data => {
    if (selected === "003") {
      return data;
    } else if (selected === "004") {
      return data;
    }
    else if (selected === "005") {
      return data;
    }
    else if (selected === "006") {
      return data;
    }
    else if (selected === "007") {
      return data;
    }
  })

  let bodyInquiries = body.filter(data => {
    if (selected === "008") {
      return data;
    } else if (selected === "009") {
      return data;
    }
    else if (selected === "010") {
      return data;
    }
    else if (selected === "011") {
      return data;
    }
  })

  let ageInquiries = age.filter(data => {
    if (selected === "012") {
      return data;
    } else if (selected === "013") {
      return data;
    }
    else if (selected === "014") {
      return data;
    }
    else if (selected === "015") {
      return data;
    }
    else if (selected === "016") {
      return data;
    }
    else if (selected === "017") {
      return data;
    }
    else if (selected === "018") {
      return data;
    }
    else if (selected === "019") {
      return data;
    }
    else if (selected === "020") {
      return data;
    }
  })

  return (
    <div
      style={{
        padding: "40px",
        margin: "40px",
      }}
    >
      <div>
        {/** onChange 될때 changeSelectOptionHandler 함수가 불려지도록 바인드
         */}
        <div className="header">

          <input type="text" className="iptSearch" />
          <button type="button" className="search">
            <span>검색</span>
          </button>
        </div>


        <select onChange={changeSelectOptionHandler}>
          <option value="001">여자</option>
          <option value="002">남자</option>
        </select>

        <select onChange={changeSelectOptionHandler}>
          <option value="014">~10살</option>
          <option value="015">10대~20대</option>
          <option value="016">20대~30대</option>
          <option value="017">30대~40대</option>
          <option value="018">40대~50대</option>
          <option value="019">50대~60대</option>
          <option value="020">60대~</option>
        </select>

        <select onChange={changeSelectOptionHandler}>
          <option value="003">~150cm</option>
          <option value="004">150cm~160cm</option>
          <option value="005">160cm~170cm</option>
          <option value="006">170cm~180cm</option>
          <option value="007">180cm~</option>
        </select>

        <select onChange={changeSelectOptionHandler}>
          <option value="008">앙상함</option>
          <option value="009">날씬함</option>
          <option value="010">보통체격</option>
          <option value="011">통통함</option>
          <option value="012">뚱뚱함</option>
          <option value="013">건장함</option>
        </select>

      </div>
    </div>


  );
}

export default SearchBar;