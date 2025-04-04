import React from "react";
import "../../css/FriendRequestsPopup.css";
import { IoClose } from "react-icons/io5";

const FriendRequestsPopup = ({ requests, onAccept, onReject, onClose }) => {
  return (
    <div className="popup-overlay">
      <div className="popup-box">
        <div className="popup-header">
          <h3>친구 요청</h3>
          <button className="close-btn" onClick={onClose}><IoClose /></button>
        </div>
        <ul className="popup-request-list">
          {requests.length > 0 ? (
            requests.map((user) => (
              <li key={user.id} className="popup-request-item">
                <img src={user.profileImage} alt={user.name} className="request-thumb" />
                <span>{user.name}</span>
                <div className="popup-actions">
                  <button className="accept-btn" onClick={() => onAccept(user.id)}>수락</button>
                  <button className="reject-btn" onClick={() => onReject(user.id)}>거절</button>
                </div>
              </li>
            ))
          ) : (
            <p className="no-request">요청이 없습니다.</p>
          )}
        </ul>
      </div>
    </div>
  );
};

export default FriendRequestsPopup;