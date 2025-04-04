import React from "react";
import "../../css/ProfilePopup.css";
import { IoClose } from "react-icons/io5";

const ProfilePopup = ({ user, onClose }) => {
  const handleAddFriend = () => {
    alert(`${user.name}님에게 친구 요청을 보냈습니다!`);
    onClose();
  };

  return (
    <div className="profile-popup-overlay">
      <div className="profile-popup">
        <button className="close-btn" onClick={onClose}>
          <IoClose size={20} />
        </button>
        <img src={user.profileImage} alt={user.name} className="popup-profile-image" />
        <h3>{user.name}</h3>
        <p>자기소개: 아직 등록되지 않음</p>
        <button className="add-friend-btn" onClick={handleAddFriend}>친구 요청 보내기</button>
      </div>
    </div>
  );
};

export default ProfilePopup;
