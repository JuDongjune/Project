import React, { useState } from "react";
import "../../css/Diary.css";
import logo from "../../assets/image/Login/loginlogo.png";
import { IoHeart, IoHeartOutline, IoClose } from "react-icons/io5";

const DiaryViewer = ({ entries, onClose, likedStories, setLikedStories }) => {
  const [currentIndex, setCurrentIndex] = useState(0);
  const entry = entries[currentIndex];
  const isLiked = likedStories.includes(entry.text);

  const handleLike = () => {
    if (!isLiked) {
      setLikedStories((prev) => [...prev, entry.text]);
    }
  };

  const handleNext = () => {
    if (currentIndex + 1 < entries.length) {
      setCurrentIndex(currentIndex + 1);
    } else {
      onClose();
    }
  };

  return (
    <div className="diary-viewer-overlay">
      <div className="diary-viewer-popup">
        <img src={logo} alt="logo" className="diary-logo" />
        {entry.image && <img src={entry.image} className="diary-photo" />}
        <p className="diary-date">MON. JULY 13 / 2018</p>
        <h2 className="diary-title">{entry.title || "오늘은 여행 3일차!"}</h2>
        <p className="diary-text">{entry.text}</p>
        <div className="viewer-buttons">
          <button onClick={handleLike}>
            {isLiked ? <IoHeart size={24} color="red" /> : <IoHeartOutline size={24} />}
          </button>
          <button onClick={handleNext}>다음</button>
          <button onClick={onClose}>
            <IoClose size={22} />
          </button>
        </div>
      </div>
    </div>
  );
};

export default DiaryViewer;
