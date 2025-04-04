import React from "react";
import "../../css/DiaryPopup.css";

const DiaryPopup = ({ diary, onClose }) => {
  return (
    <div className="diary-popup-overlay">
      <div className="diary-popup">
        <button className="close-btn" onClick={onClose}>✖</button>
        <img src={diary.image} alt={diary.title} className="popup-img" />
        <h2>{diary.title}</h2>
        <p>{diary.content}</p>
        <div className="mood">기분: {diary.mood}</div>
      </div>
    </div>
  );
};

export default DiaryPopup;