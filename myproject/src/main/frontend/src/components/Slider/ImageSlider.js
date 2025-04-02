import React, { useState, useEffect } from "react";
import "../../css/ImageSlider.css"; // ✅ CSS 적용
import image001 from "../../assets/image/Slide/001.png"; // ✅ import 방식
import image002 from "../../assets/image/Slide/002.png"; // ✅ import 방식
import image003 from "../../assets/image/Slide/003.png"; // ✅ import 방식

const ImageSlider = () => {
  const images = [image001, image002, image003]; // ✅ 올바른 배열 정의
  const [currentIndex, setCurrentIndex] = useState(0);
  const [isAnimating, setIsAnimating] = useState(false); // ✅ 애니메이션 상태 추가

  // ✅ 3초마다 자동 슬라이드 변경
  useEffect(() => {
    const interval = setInterval(() => {
      handleNextSlide();
    }, 3000);

    return () => clearInterval(interval); // ✅ 컴포넌트 언마운트 시 정리
  }, [currentIndex]);

  // ✅ 애니메이션과 함께 다음 슬라이드
  const handleNextSlide = () => {
    setIsAnimating(true);
    setTimeout(() => {
      setCurrentIndex((prevIndex) => (prevIndex + 1) % images.length);
      setIsAnimating(false);
    }, 500); // ✅ 애니메이션 시간(0.5초) 후 상태 변경
  };

  // ✅ 애니메이션과 함께 이전 슬라이드
  const handlePrevSlide = () => {
    setIsAnimating(true);
    setTimeout(() => {
      setCurrentIndex((prevIndex) =>
        prevIndex === 0 ? images.length - 1 : prevIndex - 1
      );
      setIsAnimating(false);
    }, 500);
  };

  return (
    <div className="slider-container">

      <div className={`slider-track ${isAnimating ? "animate" : ""}`} style={{ transform: `translateX(-${currentIndex * 100}%)` }}>
        {images.map((img, index) => (
          <img key={index} src={img} alt={`slide-${index}`} className="slider-image" />
        ))}
      </div>

    </div>
  );
};

export default ImageSlider;
