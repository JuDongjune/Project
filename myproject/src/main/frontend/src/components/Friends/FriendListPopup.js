import React from "react";
import "../../css/FriendListPopup.css";
import { IoClose } from "react-icons/io5";

const FriendListPopup = ({ friends, onClose }) => {
  return (
    <div className="friend-popup-overlay">
      <div className="friend-popup-box">
        <div className="friend-popup-header">
          <h3>친구 목록</h3>
          <button className="close-btn" onClick={onClose}><IoClose /></button>
        </div>
        <ul className="friend-popup-list">
          {friends.map((f) => (
            <li key={f.id} className="friend-popup-item">
              <img src={f.profileImage} alt={f.name} className="friend-thumb" />
              <span>{f.name}</span>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default FriendListPopup;