import React, { useState } from "react";

const ActorPhoto = ({ images }) => {
  const [selectedImage, setSelectedImage] = useState(null);

  const handleClick = image => {
    setSelectedImage(selectedImage === image ? null : image);
  };

  return (
    <div>
      <h1>프로필 사진</h1>
      {images.map(({ id, description, urls }) => {
        const isSelected = selectedImage === urls.regular;

        return (
          <img
            key={id}
            alt={description}
            src={urls.regular}
            style={{
              width: isSelected ? "500px" : "200px",
              cursor: "pointer"
            }}
            onClick={() => handleClick(urls.regular)}
          />
        );
      })}
    </div>
  );
};

export default ActorPhoto;