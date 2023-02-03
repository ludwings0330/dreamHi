import React from 'react';
import './ActorVideo.css';

const products = [
  {
    id: 1,
    name: 'Basic Tee',
    imageSrc: 'https://tailwindui.com/img/ecommerce-images/product-page-01-related-product-01.jpg',
  },
  {
    id: 2,
    name: 'Basic Tee',
    imageSrc: 'https://tailwindui.com/img/ecommerce-images/product-page-01-related-product-01.jpg',
  },
  {
    id: 3,
    name: 'Basic Tee',
    imageSrc: 'https://tailwindui.com/img/ecommerce-images/product-page-01-related-product-01.jpg',
  },
  {
    id: 4,
    name: 'Basic Tee',
    imageSrc: 'https://tailwindui.com/img/ecommerce-images/product-page-01-related-product-01.jpg',
  },
]


const ActorVideo = () => {
  return (
    <div className="bg-white">

      <div className="mx-auto max-w-2xl py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
        <h1>연기영상</h1>

        <div className="list-container">
          <div className='photo-main'>
            <img src={products[0].imageSrc} alt={products[0].imageAlt}/>
          </div>

          <div className='photo-list'>
            {products.map((product, idx) => (
              idx === 0 ? null :
                <div className='photo' key={product.id}>
                  <img
                    src={product.imageSrc}
                    alt={product.imageAlt}
                    width={"200px"}
                    height={"200px"}
                    className="object-center"
                  />
                </div>
            ))}
          </div>

        </div>

      </div>
    </div>

  );
};

export default ActorVideo;