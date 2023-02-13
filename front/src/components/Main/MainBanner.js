import Slider from "react-slick";
import "slick-carousel/slick/slick-theme.css";
import "slick-carousel/slick/slick.css";
import styled from 'styled-components';

const Wrapper = styled.div`
  display: block;
  margin-top: 30px;
  margin-bottom: 50px;
`;

const Image = styled.img`
  height: 500px;
  width: 100%;
  object-fit: fill;
`;

export default function MainBanner() {

  const settings = {
    // dots: true,
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1
  };
  return (
    <Wrapper>
      {/* ...settings: 위의 객체 내용이 들어옴 */}
      <Slider {...settings}>
        <div>
          <Image src="/img/test.png" alt="banner"></Image>
        </div>
        <div>
          <Image src="/img/test.png" alt="banner"></Image>
        </div>
        <div>
          <Image src="/img/test.png" alt="banner"></Image>
        </div>
        <div>
          <Image src="/img/test.png" alt="banner"></Image>
        </div>
      </Slider>
    </Wrapper>
  );
}




