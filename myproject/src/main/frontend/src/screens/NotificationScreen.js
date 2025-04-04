import React, { useState } from "react";
import "../css/Notification.css";
import { IoHeartSharp, IoPersonAddSharp } from "react-icons/io5";
import Header from "../components/Menu/Header";
import Navbar from "../components/Menu/Navbar";
import FriendRequestsPopup from "../components/Friends/FriendRequestsPopup";

const initialNotifications = [
  { id: 1, type: "like", user: "june", message: "님이 당신의 게시물에 좋아요를 눌렀습니다." },
  { id: 2, type: "friend", user: "jiwon", profileImage: "https://picsum.photos/id/105/100", message: "님이 친구 요청을 보냈습니다." },
  { id: 3, type: "like", user: "enzlese", message: "님이 당신의 다이어리에 좋아요를 눌렀습니다." },
];

const NotificationScreen = () => {
  const [notifications, setNotifications] = useState(initialNotifications);
  const [showPopup, setShowPopup] = useState(false);

  const friendRequests = notifications.filter(note => note.type === "friend");

  const handleAccept = (id) => {
    setNotifications((prev) => prev.filter((note) => note.id !== id));
    alert("친구 요청을 수락했습니다!");
  };

  const handleReject = (id) => {
    setNotifications((prev) => prev.filter((note) => note.id !== id));
    alert("친구 요청을 거절했습니다.");
  };

  return (
    <div className="notification-screen">
      <Header />
      <h2>알림</h2>

      {friendRequests.length > 0 && (
        <button className="show-popup-btn" onClick={() => setShowPopup(true)}>친구 요청 전체 보기</button>
      )}

      <ul className="notification-list">
        {notifications.map((note) => (
          <li key={note.id} className="notification-item">
            {note.type === "like" ? (
              <>
                <IoHeartSharp className="notification-icon like" />
                <span><strong>{note.user}</strong>{note.message}</span>
              </>
            ) : (
              <>
                <IoPersonAddSharp className="notification-icon friend" />
                <span><strong>{note.user}</strong>{note.message}</span>
                <div className="friend-buttons">
                  <button className="accept-btn" onClick={() => handleAccept(note.id)}>수락</button>
                  <button className="reject-btn" onClick={() => handleReject(note.id)}>거절</button>
                </div>
              </>
            )}
          </li>
        ))}
        {notifications.length === 0 && <p>새로운 알림이 없습니다.</p>}
      </ul>

      {showPopup && (
        <FriendRequestsPopup
          requests={friendRequests}
          onAccept={handleAccept}
          onReject={handleReject}
          onClose={() => setShowPopup(false)}
        />
      )}

      <Navbar />
    </div>
  );
};

export default NotificationScreen;