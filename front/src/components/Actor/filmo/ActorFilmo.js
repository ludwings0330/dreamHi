import React from 'react';


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


const ActorFilmo = () => {
  return (
    <div className="bg-white">

      <div className="mx-auto max-w-2xl py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
      <h1>필모그래피</h1>

        <div className="mt-6 grid grid-cols-6 gap-y-10 gap-x-6 sm:grid-cols-2 lg:grid-cols-4 xl:gap-x-8">
          {products.map((product) => (
            <div key={product.id}>
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

  );
};

export default ActorFilmo;